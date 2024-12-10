package com.univerity.hallmanagement.security;

import com.univerity.hallmanagement.repository.UniversityUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UniversityUserDetailsService implements UserDetailsService {

    private final UniversityUserRepository universityUserRepository;

    public UniversityUserDetailsService(UniversityUserRepository universityUserRepository) {
        this.universityUserRepository = universityUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return universityUserRepository.findByUsername(username)
                .map(UniversityUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
