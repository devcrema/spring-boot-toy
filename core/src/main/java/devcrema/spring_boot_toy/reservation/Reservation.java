package devcrema.spring_boot_toy.reservation;

import devcrema.spring_boot_toy.BaseAuditingEntity;
import devcrema.spring_boot_toy.chef.Chef;
import devcrema.spring_boot_toy.store.Store;
import devcrema.spring_boot_toy.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservation extends BaseAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Store store;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Chef chef;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
