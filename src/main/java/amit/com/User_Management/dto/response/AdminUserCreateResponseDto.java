package amit.com.User_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserCreateResponseDto {
    private Long id;
    private String fullname;
    private String email;
    private String role;       // default: USER
    private LocalDate dob;
    private String gender;
    private String picture;
    private String address;
}
