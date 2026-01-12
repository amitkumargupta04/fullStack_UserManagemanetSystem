package amit.com.User_Management.mapper;

import amit.com.User_Management.dto.request.AuthRequestDto;
import amit.com.User_Management.dto.response.AuthResponseDto;
import amit.com.User_Management.entity.User;

public class AuthMapper {
    public static User toEntity(AuthRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(
                dto.getRole() != null && !dto.getRole().isBlank()
                        ? dto.getRole().toUpperCase()
                        : "USER"
        );
        //user.setFullname(dto.getFullname() != null ? dto.getFullname() : "");
        user.setFullname("");
        return user;
    }

    public static AuthResponseDto toResponse(User user) {
        AuthResponseDto response = new AuthResponseDto();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}
