package com.example.wordy.security;


import com.example.wordy.model.PersonalDateModel;
import com.example.wordy.service.PersonalDateService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PeopleDetailsService implements UserDetailsService {

    private final PersonalDateService personalDateService;

    public PeopleDetailsService(PersonalDateService personalDateService) {
        this.personalDateService = personalDateService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PersonalDateModel> person =  personalDateService.findByLogin(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(person.get());
    }

    public String checkCurrentUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated())
        {
            Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    Collection<? extends GrantedAuthority > roles = ((UserDetails) principal).getAuthorities();

                    if (!roles.isEmpty()) {
                        return roles.iterator().next().getAuthority();
                    }
                }
        }
        return null;

    }


    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof PersonDetails) {
                return ((PersonDetails) principal).getPersonId();
            }

        }
        return null;


    }
}
