package amit.com.User_Management.controller;

import amit.com.User_Management.dto.common.ApiResponseDto;
import amit.com.User_Management.dto.request.UserUpdateRequest;
import amit.com.User_Management.dto.response.AdminUserResponseDto;
import amit.com.User_Management.entity.User;
import amit.com.User_Management.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponseDto<List<AdminUserResponseDto>>> getAllUsers(){
        List<AdminUserResponseDto> dto = adminService.getAllUsers();
        ApiResponseDto<List<AdminUserResponseDto>> response = new ApiResponseDto<>(
                true,
                "Fetched users",
                dto.size(),
                dto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<AdminUserResponseDto>> getUsersById(@PathVariable  Long id){
        AdminUserResponseDto dto = adminService.getUserById(id);
        ApiResponseDto<AdminUserResponseDto> response = new ApiResponseDto<>(
                true, "User fetched by id",1, dto, LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<Void>>  deleteUser(@PathVariable Long id){
        adminService.deleteUsers(id);
        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true, "User Deleted", 0, null, LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<AdminUserResponseDto>> updateUser(@RequestBody UserUpdateRequest dto,
                                                                           @PathVariable Long id){
        AdminUserResponseDto adminUserResponseDto = adminService.updateUser(dto, id);
        ApiResponseDto<AdminUserResponseDto> response = new ApiResponseDto<>(
                true, "Updated sucessfull", 1, adminUserResponseDto, LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
