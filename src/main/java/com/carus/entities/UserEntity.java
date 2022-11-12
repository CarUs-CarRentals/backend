package com.carus.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user")
@Data
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 11, unique = true)
    private String cpf;

    @Column(length = 8, unique = true)
    private String rg;

    @Column(length = 20)
    private String phone;

    @Column(length = 60)
    private String gender;

    @Column
    private String about;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE", nullable = false, updatable = false)
    private LocalDate memberSince;

    @Column
    private String profileImageUrl;

    @Column
    private String refreshToken;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime refreshTokenExpiration;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }
}
