package devcrema.spring_boot_toy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int code;
    private String message;

    @AllArgsConstructor
    @Getter
    public enum ErrorType {

        BAD_REQUEST(1),
        DUPLICATED_EMAIL(2);

        private int code;

        public ErrorResponse toResponse(String message){
            return new ErrorResponse(code, message);
        }
    }

}
