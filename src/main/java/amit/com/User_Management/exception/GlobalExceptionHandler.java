package amit.com.User_Management.exception;

import amit.com.User_Management.dto.common.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleResourceNotFound(
            ResourceNotFoundException ex) {

        ApiResponseDto<Object> response = new ApiResponseDto<>(
                false,
                ex.getMessage(),
                0,
                null,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 400 - Bad Request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleBadRequest(
            BadRequestException ex) {

        ApiResponseDto<Object> response = new ApiResponseDto<>(
                false,
                ex.getMessage(),
                0,
                null,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //  500 - Generic Exception (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Object>> handleGenericException(
            Exception ex) {

        ApiResponseDto<Object> response = new ApiResponseDto<>(
                false,
                "Internal Server Error",
                0,
                null,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
