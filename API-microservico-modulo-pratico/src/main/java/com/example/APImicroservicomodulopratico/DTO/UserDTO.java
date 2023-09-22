package com.example.APImicroservicomodulopratico.DTO;

import java.time.LocalDate;

import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Enum.Role;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

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
