package com.burak.service;

import com.burak.dto.request.GetProfileByTokenRequest;
import com.burak.dto.request.UserCreateRequest;
import com.burak.dto.request.UserUpdateRequest;
import com.burak.dto.response.UserResponse;
import com.burak.entity.User;
import com.burak.exception.ErrorType;
import com.burak.exception.ResourceNotFoundException;
import com.burak.exception.UserAlreadyExistsException;
import com.burak.exception.UserException;
import com.burak.mapper.UserMapper;
import com.burak.repository.UserRepository;
import com.burak.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenManager jwtTokenManager;
    private final CacheManager cacheManager;

    @Transactional
    public void createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Account with username " + request.getUsername() + " already exists");
        }
        var result = userRepository.save(userMapper.toCreateEntity(request));
        Objects.requireNonNull(cacheManager.getCache("userprofilefindall")).clear();
//        manager.save(userMapper.toResponse(result));

    }

    public UserResponse getUser(Long userId) {
        return this.userRepository.findById(userId)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    public UserResponse getProfileByToken(GetProfileByTokenRequest dto) {

        Optional<Long> authId = jwtTokenManager.getIdByToken(dto.getToken());
        if(authId.isEmpty())
            throw new UserException(ErrorType.INVALID_TOKEN);
        Optional<User> user = userRepository.findOptionalByAuthId(authId.get());
        if(user.isEmpty())
            throw new UserException(ErrorType.USER_NOT_FOUND);
        return userMapper.toResponse(user.get());
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
//                .stream()
//                .map(userMapper::toResponse)
//                .collect(Collectors.toList());
    }

    @Transactional
    public boolean updateUser(UserUpdateRequest request) {
        Optional<Long> authId = jwtTokenManager.getIdByToken(request.getToken());
        if(authId.isEmpty())
            throw new UserException(ErrorType.INVALID_TOKEN);
        Optional<User> user = userRepository.findOptionalByAuthId(authId.get());
        if(user.isEmpty())
            throw new UserException(ErrorType.USER_NOT_FOUND);
        User updatedUser = user.get();
        updatedUser.setEmail(request.getEmail());
        updatedUser.setFirstname(request.getFirstname());
        updatedUser.setLastname(request.getLastname());
        updatedUser.setPhone(request.getPhone());
        userRepository.save(updatedUser);
        Objects.requireNonNull(cacheManager.getCache("userprofilefindall")).clear();
//        manager.update(UserProfileMapper.INSTANCE.toUserProfileRequestDto(updateProfile));
        return true;

    }

    @Transactional
    public boolean deleteUser(Long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    public Page<User> findAllPageable(int page, int size, String sortParameter, String sortDirection) {
        Pageable pageable;
        if (sortParameter != null && !sortParameter.isEmpty()) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection == null ? "ASC" : sortDirection), sortParameter);
            pageable = PageRequest.of(page, size, sort);

        } else
            pageable = Pageable.ofSize(size).withPage(page);

        return userRepository.findAll(pageable);
    }
}
