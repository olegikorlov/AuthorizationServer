package com.softserve.clinic.authorizationserver.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author <a href="mailto:info@olegorlov.com">Oleg Orlov</a>
 */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @CreatedDate
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updated = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;

}
