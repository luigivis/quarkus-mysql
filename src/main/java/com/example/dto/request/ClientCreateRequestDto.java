package com.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientCreateRequestDto {

  @NotNull(message = "Name can't be null")
  @NotBlank(message = "Name can't be empty")
  private String name;

  @NotNull(message = "Last name can't be null")
  @NotBlank(message = "Last name can't be Empty")
  private String lastName;

  @Email(message = "Email not valid")
  private String email;
}
