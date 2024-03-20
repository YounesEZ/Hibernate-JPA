package org.example.jpa_hibernate.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.example.jpa_hibernate.entities.Role;
import org.example.jpa_hibernate.entities.User;
import org.example.jpa_hibernate.repository.*;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> findUserAll() {
        return userRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public List<Role> findRoleAll() {
        return roleRepository.findAll();
    }

    @Override
    public void addRoletoUser(String username, String roleName) {
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        if(user.getRoles() != null){
            System.out.println("role to user");
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

        userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = findUserByUserName(username);
        if(user == null){
            throw new RuntimeException("Bad Credentials");
        }
        if(user.getPassword().equals(password)){
            System.out.println("user authenticated");
            return user;
        }
        throw new RuntimeException("Bad Credentials");
    }
}
