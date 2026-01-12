package amit.com.User_Management.dto.common;

import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApiResponseDto<T> {

    private boolean success;
    private String message;
    private Integer total;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponseDto(boolean success, String message, Integer total, T data, LocalDateTime timestamp) {
        this.success = success;
        this.message = message;
        this.total = total;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
