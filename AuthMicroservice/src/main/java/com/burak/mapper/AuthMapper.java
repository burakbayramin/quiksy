package com.burak.mapper;

import com.burak.dto.request.RegisterRequest;
import com.burak.dto.request.UserCreateRequest;
import com.burak.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromDto(final RegisterRequest dto);

    @Mapping(source = "id",target = "authId")
    UserCreateRequest fromAuth(final Auth auth);
}
