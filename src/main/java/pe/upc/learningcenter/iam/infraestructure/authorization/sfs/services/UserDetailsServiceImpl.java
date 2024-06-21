package pe.upc.learningcenter.iam.infraestructure.authorization.sfs.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.upc.learningcenter.iam.infraestructure.authorization.sfs.model.UserDetailsImpl;
import pe.upc.learningcenter.iam.infraestructure.persistence.jpa.repositories.UserRepository;

@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return UserDetailsImpl.build(user);
    }
}
