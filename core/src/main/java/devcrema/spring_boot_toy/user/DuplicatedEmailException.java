package devcrema.spring_boot_toy.user;

import devcrema.spring_boot_toy.ErrorResponse;

public class DuplicatedEmailException extends RuntimeException {

    private static final String defaultMessage = "중복된 이메일이 존재합니다.";

    public DuplicatedEmailException(){
        super(defaultMessage);
    }

    public DuplicatedEmailException(String message){
        super(message);
    }

}
