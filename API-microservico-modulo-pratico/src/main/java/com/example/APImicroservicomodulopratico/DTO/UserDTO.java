package com.example.APImicroservicomodulopratico.DTO;

import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Enum.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
    @NotNull
    private Role role;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3, max = 6)
    private String registrationNumber;

    //@AssertTrue só funciona se quiser trazer que este campo PRECISA ser verdadeiro
    private Boolean isActive;

    @NotBlank
    private String birthDate;

    public User toEntity(){
        return User.builder()
                .role(this.role)
                .name(this.name)
                .registrationNumber(this.registrationNumber)
                .isActive(this.isActive)
                .birthDate(this.birthDate)
                .build();
    }
}
