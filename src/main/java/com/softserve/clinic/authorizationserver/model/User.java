package com.softserve.clinic.authorizationserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "users_username_key", columnNames = "username")
        }
)
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_role_user_id_fkey")
            ),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_role_role_id_fkey")
            )
    )
    private List<Role> roles = new ArrayList<>();
}
