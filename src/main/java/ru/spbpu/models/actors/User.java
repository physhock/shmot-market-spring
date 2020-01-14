package ru.spbpu.models.actors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.spbpu.models.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class User extends AbstractDomain {
    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_status")
    private UserStatus userStatus;

    public enum UserStatus {
        ONLINE,
        OFFLINE
    }
}
