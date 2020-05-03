package pro_area.test_task.havriushenko.internet_market.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pro_area.test_task.havriushenko.internet_market.dto.UserDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static pro_area.test_task.havriushenko.internet_market.util.SecurityConstants.*;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try{
            UserDto user = new ObjectMapper().readValue(req.getInputStream(),UserDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail()
                                                                                             ,user.getPassword()
                                                                                             , new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String token = JWT.create()
                          .withSubject(((User)auth.getPrincipal()).getUsername())
                          .withExpiresAt(new Date(System.currentTimeMillis() + EXPERATION_TIME))
                          .sign(Algorithm.HMAC512(SECURITY_SECRET_WORD.getBytes()));
        res.addHeader(HEADER_STRING,TOKEN_PREFIX + token);
    }
}
