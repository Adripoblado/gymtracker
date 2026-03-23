package com.adripoblado.gymtracker.gymtracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.adripoblado.gymtracker.gymtracker.dto.UpdateUserDTO;
import com.adripoblado.gymtracker.gymtracker.dto.UserResponseDTO;
import com.adripoblado.gymtracker.gymtracker.model.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUserFromDTO(UpdateUserDTO dto, @MappingTarget User user);

    UpdateUserDTO toUpdateDTO(User user);
    UserResponseDTO toResponseDTO(User user);
}
