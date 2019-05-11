package devcrema.spring_boot_toy.user;

public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException(String message){
        super(message);
    }
}
