package ua.xet.ConferenceApp.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User userId;
    @Column(nullable = false)
    private String name;
  //  @Column(name = "amount_users")
   // private int amountUsers;
    @Column(name = "active",nullable = false)
    private boolean active;
    @Column(name = "date_created",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_active",nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dateActive;
}
