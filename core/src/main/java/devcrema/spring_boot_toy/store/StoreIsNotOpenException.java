package devcrema.spring_boot_toy.store;

public class StoreIsNotOpenException extends RuntimeException {
    public StoreIsNotOpenException() {
        super("음식점 영업시간이 아닙니다.");
    }
}
