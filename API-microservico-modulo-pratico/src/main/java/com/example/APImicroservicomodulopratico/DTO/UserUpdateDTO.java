package com.example.APImicroservicomodulopratico.DTO;

import java.time.LocalDate;

import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Enum.Role;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    
    private Role role;

    private String registrationNumber;

    private String name;

    private Boolean isActive;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public User toEntityUpdate(User user){
        return User.builder()
                .id(user.getId())
                .role(this.role)
                .registrationNumber(this.registrationNumber)
                .name(this.name)
                .isActive(this.isActive)
                .birthDate(this.birthDate)
                .build();
    }
}
