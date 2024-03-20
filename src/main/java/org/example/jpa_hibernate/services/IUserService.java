package org.example.jpa_hibernate.services;

import org.example.jpa_hibernate.entities.Role;
import org.example.jpa_hibernate.entities.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    User findUserByUserName(String username);

    List<User> findUserAll();

    Role saveRole(Role role);

    Role findRoleByRoleName(String rolename);

    List<Role> findRoleAll();

    void addRoletoUser(String username, String roleName);

    User authenticate(String username, String password);
}
