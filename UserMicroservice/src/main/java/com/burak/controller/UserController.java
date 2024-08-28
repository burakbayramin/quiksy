package com.burak.controller;

import com.burak.constants.MessageConstants;
import com.burak.dto.request.GetProfileByTokenRequest;
import com.burak.dto.request.UserCreateRequest;
import com.burak.dto.request.UserUpdateRequest;
import com.burak.dto.response.StatusResponse;
import com.burak.dto.response.UserResponse;
import com.burak.entity.User;
import com.burak.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.burak.constants.PathConstants.USER;


@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateRequest request) {
        userService.createUser(request);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(new StatusResponse(MessageConstants.STATUS_201, MessageConstants.MESSAGE_201));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> responses = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


    @PutMapping
    public ResponseEntity<StatusResponse> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        boolean isUpdated = userService.updateUser(request);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StatusResponse(MessageConstants.STATUS_200, MessageConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new StatusResponse(MessageConstants.STATUS_417, MessageConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<StatusResponse> deleteUser(@PathVariable Long userId) {
        boolean isDeleted = userService.deleteUser(userId);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StatusResponse(MessageConstants.STATUS_200, MessageConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new StatusResponse(MessageConstants.STATUS_417, MessageConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("/find-all-page")
    public ResponseEntity<Page<User>> findAllPage(int page, int size, String sortParameter, String sortDirection){
        return ResponseEntity.ok(userService.findAllPageable(page,size,sortParameter,sortDirection));
    }

    @PostMapping("/get-by-token")
    public ResponseEntity<UserResponse> getProfileByToken(@RequestBody @Valid GetProfileByTokenRequest dto){
        return ResponseEntity.ok(userService.getProfileByToken(dto));
    }
}
