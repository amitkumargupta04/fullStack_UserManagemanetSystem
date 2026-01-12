package amit.com.User_Management.mapper;

import amit.com.User_Management.dto.request.UserUpdateRequest;
import amit.com.User_Management.dto.response.AdminUserResponseDto;
import amit.com.User_Management.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class AdminMapper {

    //Single User entity ko AdminUserResponseDto me convert karna
    public static AdminUserResponseDto toAdminUserDto(User user) {
        AdminUserResponseDto dto = new AdminUserResponseDto();
        dto.setId(user.getId());
        dto.setFullname(user.getFullname());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setDob(user.getDob());
        dto.setGender(user.getGender());
        dto.setPicture(user.getPicture());
        dto.setAddress(user.getAddress());
        return dto;
    }

    //List of Users ko List of AdminUserResponseDto me convert karna
    public static List<AdminUserResponseDto> toAdminUserDtoList(List<User> users) {
        return users.stream()
                .map(AdminMapper::toAdminUserDto)
                .collect(Collectors.toList());
    }

    //Update ke liye AdminUserResponseDto se Entity me map karna
    public static void updateUserFromDto(AdminUserResponseDto dto, User user) {
        if (dto.getFullname() != null) user.setFullname(dto.getFullname());
        if (dto.getDob() != null) user.setDob(dto.getDob());
        if (dto.getGender() != null) user.setGender(dto.getGender());
        if (dto.getPicture() != null) user.setPicture(dto.getPicture());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        //if (dto.getRole() != null) user.setRole(dto.getRole());
        // Email aur id generally update nahi karte admin se
    }
    public static void updateUserFromRequest(UserUpdateRequest dto, User user) {
        if (dto.getFullname() != null) user.setFullname(dto.getFullname());
        if (dto.getDob() != null) user.setDob(dto.getDob());
        if (dto.getGender() != null) user.setGender(dto.getGender());
        if (dto.getPicture() != null) user.setPicture(dto.getPicture());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
    }
}
