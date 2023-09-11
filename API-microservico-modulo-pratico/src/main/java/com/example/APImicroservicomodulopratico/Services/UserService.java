package com.example.APImicroservicomodulopratico.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APImicroservicomodulopratico.DTO.UserDTO;
import com.example.APImicroservicomodulopratico.DTO.UserUpdateDTO;
import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Repository.UserRepository;

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
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null){
            User updateUser = userUpdateDTO.toEntityUpdate(user);
            return this.userRepository.save(updateUser);
        }
        return null;

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
