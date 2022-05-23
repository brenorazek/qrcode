package br.com.razek.qrcode.security;

import br.com.razek.qrcode.dto.UserDTO;
import br.com.razek.qrcode.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthorizationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    private UserDetailsServiceImpl userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            UserDTO creds = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        String username = ((UserSS) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Expose-Headers", "Location");

        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);


        //String responseData = ((UserSS) authentication.getPrincipal()).getEmail();
        //String authority = authentication.getAuthorities().toString();


        //JsonObject item = new JsonObject();
        //item.addProperty("authority", authority);
        //item.addProperty("email", responseData);

       // response.setContentType("application/json");
       // response.setCharacterEncoding("UTF-8");
       // PrintWriter writer = response.getWriter();
       // writer.write(String.valueOf(item));
       // writer.close();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());
    }

    private CharSequence json() {
        long date = new Date().getTime();
        return "{"
                + "\"timestamp\": " + date + " , "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\" + \"/login\"}";

    }
}
