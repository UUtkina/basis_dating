package de.ait.shop.dto;

import de.ait.shop.models.User;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder

public class UserRequestDTO {
    private Long id;
    private String email;
    private String password;

    public static User toUser(UserRequestDTO userRequestDTO){
        return  new User(null, userRequestDTO.getEmail(),userRequestDTO.getPassword());
    }

    public static List<User> toUser(List<UserRequestDTO>userRequestDTOS){
        return userRequestDTOS.stream().map(UserRequestDTO::toUser)
                .toList();
    }
}
