package devcrema.spring_boot_toy;

public class Api {
    private static final String ROOT = "/api";

    public static class User {
        public static final String USERS = ROOT + "/users";
        public static final String WITHDRAW = USERS + "/withdraw";
    }

    public static class Chef {
        public static final String CHEFS = ROOT + "/chefs";
    }

    public static class Store {
        public static final String STORES = ROOT + "/stores";
    }


}
