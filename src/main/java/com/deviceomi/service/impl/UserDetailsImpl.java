package com.deviceomi.service.impl;

import com.deviceomi.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Transactional
public class UserDetailsImpl implements UserDetails {
    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserEntity userEntity){
        List<GrantedAuthority> authorities = userEntity.getRoles()
                                                .stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                                                .collect(Collectors.toList());
        return new UserDetailsImpl(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword(),authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) o;
        return Objects.equals(id, userDetails.id);
    }
    public UserEntity getUser() {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(id);
        userEntity.setUserName(username);
        userEntity.setPassword(password);
        return userEntity;
    }
}
