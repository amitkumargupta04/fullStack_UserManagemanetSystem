package amit.com.User_Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {
    private Long id;
    private String fullname;
    private LocalDate dob;
    private String gender;
    private String picture;
    private String address;
}
