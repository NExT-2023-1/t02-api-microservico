package com.example.APImicroservicomodulopratico.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APImicroservicomodulopratico.DTO.UserDTO;
import com.example.APImicroservicomodulopratico.DTO.UserUpdateDTO;
import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public User create(UserDTO userDTO) throws Exception {
        Optional<User> optionalUser = this.userRepository.findByRegistrationNumber(userDTO.getRegistrationNumber());
        if (optionalUser.isPresent()) {
            throw new Exception("Registration Number already exists");
        } else {
            if (userDTO.getBirthDate().isAfter(LocalDate.now()) || userDTO.getBirthDate().isEqual(LocalDate.now())) {
                throw new Exception("Birth Date can't be less or equal to current date.");
            }
            User user = userDTO.toEntity();
            return this.userRepository.save(user);
        }
    }

    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .collect(Collectors.toList());
    }

    public User getById(UUID id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User update(UUID id, @Valid UserUpdateDTO userUpdateDTO) throws Exception {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (userUpdateDTO.getRole() != null) {
                user.setRole(userUpdateDTO.getRole());
            }

            if (userUpdateDTO.getName() != null) {
                if (!userUpdateDTO.getName().isBlank()) {
                    user.setName(userUpdateDTO.getName());

                } else {
                    throw new Exception("Name can't be empty.");
                }
            }

            if (userUpdateDTO.getIsActive() != null) {
                user.setIsActive(userUpdateDTO.getIsActive());
            }

            if (userUpdateDTO.getBirthDate() != null) {
                if (userUpdateDTO.getBirthDate().isAfter(LocalDate.now()) || userUpdateDTO.getBirthDate().isEqual(LocalDate.now())) {
                    throw new Exception("Birth Date can't be less or equal to current date.");
                } else {
                    user.setBirthDate(userUpdateDTO.getBirthDate());
                }
            }

            if (userUpdateDTO.getRegistrationNumber() != null) {
                throw new Exception("Registration Number can't be update");
            }
            return this.userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    public boolean delete(UUID id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        }

        return false;
    }

}
