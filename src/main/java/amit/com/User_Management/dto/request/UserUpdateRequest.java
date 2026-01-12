package amit.com.User_Management.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String fullname;
    private LocalDate dob;
    private String gender;
    private String picture;
    private String address;
}
