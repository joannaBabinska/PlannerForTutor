package com.babinska.plannerfortutor.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(
            Set.of(
                    Permission.USER_READ,
                    Permission.USER_UPDATE
            )
    ),
    ADMIN(
            Set.of(
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_READ,
                    Permission.ADMIN_DELETE
            )
    );

    private final Set<Permission> permission;

    public List<SimpleGrantedAuthority> getAuthorities() {
       var authorities =  getPermission().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermissions()))
                .collect(Collectors.toList());
       authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
       return authorities;
    }
}
