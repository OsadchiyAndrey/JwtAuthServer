package com.osadchiy.rentservice.security;

import com.osadchiy.rentservice.dao.entity.account.Account;
import com.osadchiy.rentservice.dao.entity.account.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class SecurityAccount implements UserDetails {


    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromAccount(Account account) {
        return new User(account.getEmail(),
                account.getPassword(),
                account.getStatus().equals(Status.ACTIVE),
                account.getStatus().equals(Status.ACTIVE),
                account.getStatus().equals(Status.ACTIVE),
                account.getStatus().equals(Status.ACTIVE),
                Collections.emptyList());
    }
}
