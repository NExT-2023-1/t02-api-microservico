package com.example.APImicroservicomodulopratico.DTO;

import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Enum.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    
    @NotNull
    private Role role;

    @NotBlank
    private String name;

    private Boolean isActive;

    @NotBlank
    private String birthDate;

    public User toEntityUpdate(User user){
        return User.builder()
                .id(user.getId())
                .role(this.role)
                .name(this.name)
                .isActive(this.isActive)
                .birthDate(this.birthDate)
                .build();
    }
}
