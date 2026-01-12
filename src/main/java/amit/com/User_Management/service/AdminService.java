package amit.com.User_Management.service;

import amit.com.User_Management.dto.request.UserUpdateRequest;
import amit.com.User_Management.dto.response.AdminUserResponseDto;
import amit.com.User_Management.entity.User;
import amit.com.User_Management.mapper.AdminMapper;
import amit.com.User_Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    // get All Users
    public List<AdminUserResponseDto> getAllUsers(){
        List<User> users = userRepository.findAll();

        // Map each user to AdminUserResponseDto
        return users.stream()
                .map(AdminMapper::toAdminUserDto)
                .collect(Collectors.toList());
    }

    public  AdminUserResponseDto getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not find with is id"));

        return AdminMapper.toAdminUserDto(user);
    }

    public void deleteUsers(Long id){
        User user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not found with This id"));

        userRepository.deleteById(user.getId());
    }

    public AdminUserResponseDto updateUser(UserUpdateRequest dto, Long id){
        User user = userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not found with This id"));

        AdminMapper.updateUserFromRequest(dto, user);
        User updatedUser = userRepository.save(user);
        return AdminMapper.toAdminUserDto(updatedUser);
    }

}
