package amit.com.User_Management.controller;

import amit.com.User_Management.dto.common.ApiResponseDto;
import amit.com.User_Management.dto.request.UserUpdateRequest;
import amit.com.User_Management.dto.response.UserUpdateResponse;
import amit.com.User_Management.entity.User;
import amit.com.User_Management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<ApiResponseDto<UserUpdateResponse>> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestBody UserUpdateRequest dto
    ){
        UserUpdateResponse userUpdateResponse = userService.updateProfile(dto, user.getId());
        ApiResponseDto<UserUpdateResponse> response = new ApiResponseDto<>(
                true,
                "Profile updated successfully",
                1,
                userUpdateResponse,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/own/profile")
    public ResponseEntity<ApiResponseDto<UserUpdateResponse>> getOwnProfile(@AuthenticationPrincipal User user){
        UserUpdateResponse userUpdateResponse = userService.getOwnProfile(user.getId());
        ApiResponseDto<UserUpdateResponse> response = new ApiResponseDto<>
                (true, "Fetched profile", 1, userUpdateResponse, LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}
