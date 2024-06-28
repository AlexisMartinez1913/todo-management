package jagarcia.todo_management.security;

import jagarcia.todo_management.entity.User;
import jagarcia.todo_management.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    //se llama este metodo cuando el usuario intenta autenticarse


    private IUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //busca un usuario por correo o username
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                //si no se encuentra el user se lanza la excepcion
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));
        //se obtienen los roles del user
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        //se crea una instancia de userdetails con el user, password y autoridades
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
