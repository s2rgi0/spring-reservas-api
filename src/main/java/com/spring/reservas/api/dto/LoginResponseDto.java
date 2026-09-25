package com.spring.reservas.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private String mensaje;
    private String token;

}
