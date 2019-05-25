package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<User> chefList;
}
