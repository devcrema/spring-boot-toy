package devcrema.spring_boot_toy;

public class Api {
    private static final String ROOT = "/api";

    public static class User {
        public static final String RESOURCE = ROOT + "/users";

        public static final String WITHDRAW = "/withdraw";
    }

    public static class CHEF {
        public static final String CHEFS = ROOT + "/chefs";
    }


}
