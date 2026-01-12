package amit.com.User_Management.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private Long id;
    private String email;
    private String fullname;
    private String role;
    private LocalDate dob;
    private String gender;
    private String picture;
    private String address;
    private boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
