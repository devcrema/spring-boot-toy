package devcrema.spring_boot_toy.store;

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

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Store store;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Chef chef;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private User user;

    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

}
