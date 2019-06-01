package devcrema.spring_boot_toy.advice;

import devcrema.spring_boot_toy.ErrorResponse;
import devcrema.spring_boot_toy.user.DuplicatedEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandlingAdvice {

    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedEmailException(DuplicatedEmailException exception){
        log.error(exception.getMessage());
        return ErrorResponse.ErrorType.DUPLICATED_EMAIL.toResponse(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.error(exception.getMessage());
        String errorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ErrorResponse.ErrorType.BAD_REQUEST.toResponse(errorMessage);
    }



}
