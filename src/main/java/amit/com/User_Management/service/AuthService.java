package amit.com.User_Management.service;

import amit.com.User_Management.dto.request.LoginRequestDto;
import amit.com.User_Management.dto.request.AuthRequestDto;
import amit.com.User_Management.dto.response.LoginResponseDto;
import amit.com.User_Management.dto.response.AuthResponseDto;
import amit.com.User_Management.entity.User;
import amit.com.User_Management.mapper.AuthMapper;
import amit.com.User_Management.repository.UserRepository;
import amit.com.User_Management.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponseDto signup(AuthRequestDto dto){
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("User Already registered");
        }
        User user = AuthMapper.toEntity(dto);
        // 🔑 Hash password before saving
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = userRepository.save(user);
        return AuthMapper.toResponse(savedUser);
    }
//    public UserResponseDto login(LoginRequestDto dto){
//
//        User user = userRepository.findByEmail(dto.getEmail())
//                .orElseThrow(() -> new RuntimeException("Email not registered"));
//        if(!user.getPassword().equals(dto.getPassword())){
//            throw new RuntimeException("Password incorrect");
//        }
        // 🔑 Check hashed password
//        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
//            throw new RuntimeException("Password incorrect");
////        }
//        UserResponseDto response = new UserResponseDto();
//        response.setId(user.getId());
//        response.setEmail(user.getEmail());
//        response.setRole(user.getRole());
//        //response.setActive(user.isActive());
//
//        return response;
    //}

    public LoginResponseDto login(LoginRequestDto dto){
       User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Email not registered"));

       if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
           throw new RuntimeException("Password incorrect");
       }
       String token = jwtService.generateToken(user);
       return new LoginResponseDto(user.getId(), user.getEmail(), user.getRole(), token);
    }
}
