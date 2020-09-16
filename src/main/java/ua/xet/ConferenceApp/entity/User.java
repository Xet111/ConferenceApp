package ua.xet.ConferenceApp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="users",uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id",nullable = false)
    private long id;
    @Column(nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Column(name = "account_non_locked",nullable = false)
    private boolean accountNonLocked;
    @Column(name = "account_non_expired",nullable = false)
    private boolean accountNonExpired;
    @Column(name = "credentials_non_expired",nullable = false)
    private boolean credentialsNonExpired;
    @Column(name = "enabled",nullable = false)
    private boolean enabled;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private Set<Conference> conferences;
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "statistics",
            joinColumns = @JoinColumn(name ="users_id"),
            inverseJoinColumns = @JoinColumn(name = "conf_id")
    )
    private Set<Conference> conferencesStats;
}
