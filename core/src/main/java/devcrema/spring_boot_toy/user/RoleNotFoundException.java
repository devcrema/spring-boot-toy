package devcrema.spring_boot_toy.user;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message){
        super(message);
    }
}
