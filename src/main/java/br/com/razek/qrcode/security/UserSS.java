package br.com.razek.qrcode.security;

import br.com.razek.qrcode.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
public class UserSS implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserSS(Long id, String email, String password
                 ) {
        this.id = id;
        this.email = email;
        this.password = password;
       // this.authorities = authorities;
    }

    public static UserSS build(User user) {
       // List<GrantedAuthority> authorities = user.getRoles().stream()
       //         .map(role -> new SimpleGrantedAuthority(role.getDescricao()))
       //         .collect(Collectors.toList());

        return new UserSS(
                user.getId(),
                user.getEmail(),
                user.getPassword()
                );
    }



    public Long getId(){
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
