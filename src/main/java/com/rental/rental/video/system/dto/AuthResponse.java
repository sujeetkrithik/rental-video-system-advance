package com.rental.rental.video.system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;

    // Optional: You can also return role & email if needed
    private String email;
    private String role;
}