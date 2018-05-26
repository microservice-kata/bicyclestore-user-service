package me.aikin.bicyclestore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements Serializable {
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();

}
