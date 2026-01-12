package amit.com.User_Management.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String role;
}
