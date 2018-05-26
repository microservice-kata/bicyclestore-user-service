package me.aikin.bicyclestore.user.domain;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class User implements Serializable {
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();

}
