package amit.com.User_Management.mapper;

import amit.com.User_Management.dto.request.UserUpdateRequest;
import amit.com.User_Management.dto.response.UserUpdateResponse;
import amit.com.User_Management.entity.User;

public class UserMapper {
    // UPDATE existing user
    public static void updateUserFromDto(UserUpdateRequest dto, User user) {

        if (dto.getFullname() != null)
            user.setFullname(dto.getFullname());

        if (dto.getDob() != null)
            user.setDob(dto.getDob());

        if (dto.getGender() != null)
            user.setGender(dto.getGender());

        if (dto.getPicture() != null)
            user.setPicture(dto.getPicture());

        if (dto.getAddress() != null)
            user.setAddress(dto.getAddress());
    }

    // ENTITY → RESPONSE
    public static UserUpdateResponse toUpdateResponse(User user) {
        return new UserUpdateResponse(
                user.getId(),
                user.getFullname(),
                user.getDob(),
                user.getGender(),
                user.getPicture(),
                user.getAddress()
        );
    }
}
