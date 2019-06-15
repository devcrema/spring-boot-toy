package devcrema.spring_boot_toy.store;

public class ChefNotExistException extends RuntimeException {

    public ChefNotExistException() {
        super("요리사가 존재하지 않습니다.");
    }
}
