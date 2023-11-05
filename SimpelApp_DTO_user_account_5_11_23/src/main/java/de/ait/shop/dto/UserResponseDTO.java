package de.ait.shop.dto;

import de.ait.shop.models.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String password;

    public static UserResponseDTO from(User user){
        return new UserResponseDTO(user.getId(),
                user.getEmail(),
                user.getPassword());
    }

    public static List<UserResponseDTO>from(List<User>users){
        return users.stream()
                .map(UserResponseDTO::from)
                .toList();
    }
}
