package amit.com.User_Management.controller;

import amit.com.User_Management.dto.common.ApiResponseDto;
import amit.com.User_Management.dto.request.LoginRequestDto;
import amit.com.User_Management.dto.request.AuthRequestDto;
import amit.com.User_Management.dto.response.LoginResponseDto;
import amit.com.User_Management.dto.response.AuthResponseDto;
import amit.com.User_Management.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> signup(@Valid @RequestBody AuthRequestDto dto){
        AuthResponseDto userResponseDto = userService.signup(dto);
        ApiResponseDto<AuthResponseDto> response = new ApiResponseDto<>(
                true,
                "User Registered",
                1,
                userResponseDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponseDto<UserResponseDto>> login(@RequestBody LoginRequestDto dto){
//        UserResponseDto userResponseDto = userService.login(dto);
//        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(
//                true, "Login Successfully", 1, userResponseDto, LocalDateTime.now()
//        );
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@RequestBody LoginRequestDto dto){
        LoginResponseDto loginResponseDto = userService.login(dto);
        ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>(
                true, "Login Successfully",1, loginResponseDto, LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
