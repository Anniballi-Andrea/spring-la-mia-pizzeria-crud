package org.lessons.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.lessons.spring_la_mia_pizzeria_crud.model.User;
import org.lessons.spring_la_mia_pizzeria_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = repo.findByUsername(username);

        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("non ci sono utenti con l'username " + username);
        }

        return new DatabaseUserDetail(userAttempt.get());

    }

}
