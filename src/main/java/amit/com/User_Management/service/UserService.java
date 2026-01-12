package amit.com.User_Management.service;

import amit.com.User_Management.dto.request.UserUpdateRequest;
import amit.com.User_Management.dto.response.UserUpdateResponse;
import amit.com.User_Management.entity.User;
import amit.com.User_Management.mapper.UserMapper;
import amit.com.User_Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserUpdateResponse updateProfile(UserUpdateRequest dto, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not find with is this id"));
        // DTO se fields update
        UserMapper.updateUserFromDto(dto, user);

        User updatedUser = userRepository.save(user);
        return UserMapper.toUpdateResponse(updatedUser);

    }
    public UserUpdateResponse getOwnProfile(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id"));
        return UserMapper.toUpdateResponse(user);
    }
}
