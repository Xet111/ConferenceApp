package ua.xet.ConferenceApp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "conference",uniqueConstraints = @UniqueConstraint(columnNames = {"name","user_id"}))
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "conf_id",nullable = false)
    private long confId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id",nullable = false)
    private User userId;
    @Column(nullable = false)
    private String name;
  //  @Column(name = "amount_users")
   // private int amountUsers;
    @Column(name = "active",nullable = false)
    private boolean active;
}
