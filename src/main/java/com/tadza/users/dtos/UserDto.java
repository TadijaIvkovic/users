package com.tadza.users.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String email;


//    private LocalDateTime createdAt;

//    @JsonIgnore

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private String phoneNumber;
}
