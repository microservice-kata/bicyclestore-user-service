package me.aikin.bicyclestore.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Role {
    private Long id;

    private RoleName name;
}
