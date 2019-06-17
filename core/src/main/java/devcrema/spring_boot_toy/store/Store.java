package devcrema.spring_boot_toy.store;

import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.user.User;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Builder.Default
    private List<Chef> chefList = new ArrayList<>();

    private LocalTime openingTime;
    private LocalTime closingTime;

    public boolean addChef(Chef chef){
        for(Chef savedChef : chefList){
            if(savedChef.equals(chef)) return false;
        }
        chefList.add(chef);
        return true;
    }

    public boolean chefExist(Chef chef){
        if(chefList == null || chefList.isEmpty()) return false;
        return chefList.contains(chef);
    }

    public boolean validOpenTime(LocalDateTime startTime, LocalDateTime endTime) {
        if(openingTime == null || closingTime == null) return false;

        if(startTime.toLocalTime().isBefore(openingTime)){
            return false;
        } else return !endTime.toLocalTime().isAfter(closingTime);
    }
}
