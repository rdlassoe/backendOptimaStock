package com.hrks.OptimaStock.user.service;

import com.hrks.OptimaStock.user.model.User;
import com.hrks.OptimaStock.user.repository.UserRepository;
import com.hrks.OptimaStock.person.model.Person;
import com.hrks.OptimaStock.person.repository.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PersonRepository personRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        // Fetch and validate Person
        if (user.getPerson() != null && user.getPerson().getId() != null) {
            Person person = personRepository.findById(user.getPerson().getId())
                    .orElseThrow(() -> new RuntimeException("Person with id " +
                            user.getPerson().getId() + " not found"));
            user.setPerson(person);
        }

        // Encriptar password si es nuevo usuario o si se está actualizando el password
        if (user.getId() == null) {
            // Usuario nuevo - encriptar password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // Usuario existente - solo encriptar si el password cambió
            Optional<User> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent()) {
                String existingPassword = existingUser.get().getPassword();
                // Si el password es diferente al almacenado (no es un hash BCrypt)
                if (!user.getPassword().equals(existingPassword)) {
                    // Si no empieza con $2a$ significa que no está encriptado
                    if (!user.getPassword().startsWith("$2a$")) {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                }
            }
        }

        // Asegurar que el rol tenga el prefijo ROLE_
        if (user.getRole() != null && !user.getRole().isEmpty()) {
            if (!user.getRole().startsWith("ROLE_")) {
                user.setRole("ROLE_" + user.getRole());
            }
        }

        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
