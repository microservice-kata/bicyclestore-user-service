package me.aikin.bicyclestore.user.api.user.playload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserSummary {
    private Long id;
    private String username;
    private String name;
}
