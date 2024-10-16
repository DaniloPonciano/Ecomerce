package br.com.senai.ecommerce.controller;

import br.com.senai.ecommerce.entities.User;
import br.com.senai.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //suporta apenas dados
@RequestMapping("/user") // localhost:8080/user
public class UserController {
    //injeção de dependência, criar um objeto
    @Autowired
    private UserRepository userRepository;

    //metodo para criar um usuario
    @PostMapping
    public User createUsuario(@RequestBody User user){
        return userRepository.save(user);
    }
    // listar todos os usuarios do banco de dados
    @GetMapping
    public List<User> getAllUsers(){
        // SELECT * FROM user
        return userRepository.findAll();
    }
    //pegar um usuario pelo seu id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }
    // deleta um usuario pelo seu id
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
    //atualiza informações do usuario pelo seu id
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
            @RequestBody User user){
        User usuario = userRepository.findById(id).orElse(null);
        if(usuario != null){
            usuario.setName(user.getName());
            usuario.setEmail(user.getEmail());
            usuario.setPassword(user.getPassword());
            return userRepository.save(usuario);
        }
        return null;
    }
}
