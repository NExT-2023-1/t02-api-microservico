package com.example.APImicroservicomodulopratico.Services;

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

    public User create(UserDTO userDTO){
        User user = userDTO.toEntity();
        return this.userRepository.save(user);
    }

    public List<User> listAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .collect(Collectors.toList());
    }

    public User getById(UUID id){
        return this.userRepository.findById(id).orElse(null);
    }

    public User update(UUID id, @Valid UserUpdateDTO userUpdateDTO){
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (userUpdateDTO.getRole() != null) {
                user.setRole(userUpdateDTO.getRole());
            }

            if (userUpdateDTO.getName() != null) {
                user.setName(userUpdateDTO.getName());
            }

            if (userUpdateDTO.getIsActive() != null) {
                user.setIsActive(userUpdateDTO.getIsActive());
            }

            if (userUpdateDTO.getBirthDate() != null) {
                user.setBirthDate(userUpdateDTO.getBirthDate());
            }
            return this.userRepository.save(user);
        }else{
            throw new EntityNotFoundException("User not found.");
        }
    }

    public boolean delete(UUID id){
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        }

        return false;
    }
    
}
