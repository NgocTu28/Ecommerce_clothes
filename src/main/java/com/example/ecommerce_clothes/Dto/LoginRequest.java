package com.example.ecommerce_clothes.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank(message = "UserName không được đẻ trống")
    String username;
    @NotBlank(message = "Password không được đẻ trống")
    String password;
    Boolean save;
}
