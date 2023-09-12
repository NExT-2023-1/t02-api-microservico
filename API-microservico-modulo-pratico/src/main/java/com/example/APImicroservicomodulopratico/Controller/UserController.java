package com.example.APImicroservicomodulopratico.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APImicroservicomodulopratico.DTO.UserDTO;
import com.example.APImicroservicomodulopratico.DTO.UserUpdateDTO;
import com.example.APImicroservicomodulopratico.Entities.User;
import com.example.APImicroservicomodulopratico.Services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    //fazer valida;ão nos campos para ver o que acontece se salva usuarios com mesmos dados
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO UserDTO){
        User user = userService.create(UserDTO);
        return new ResponseEntity<> (user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO userUpdateDTO){
        User user = userService.update(id, userUpdateDTO);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<User>> listAll(){
        List<User> users = this.userService.listAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id){
        User user = this.userService.getById(id);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (userService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
