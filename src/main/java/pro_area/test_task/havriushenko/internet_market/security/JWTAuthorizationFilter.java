package pro_area.test_task.havriushenko.internet_market.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static pro_area.test_task.havriushenko.internet_market.util.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        if(StringUtils.isEmpty(header) || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(req,res);
            return;
        }
        UsernamePasswordAuthenticationToken authentification = getAuthentification(req,res);
        SecurityContextHolder.getContext().setAuthentication(authentification);
        chain.doFilter(req,res);
    }

    private UsernamePasswordAuthenticationToken getAuthentification(HttpServletRequest req, HttpServletResponse res) {
        String token = req.getHeader(HEADER_STRING);
        if(StringUtils.isNotEmpty(token)){
            String user = JWT.require(Algorithm.HMAC512(SECURITY_SECRET_WORD.getBytes()))
                             .build()
                             .verify(token.replace(TOKEN_PREFIX,""))
                             .getSubject();
            if(StringUtils.isNotEmpty(user)){
                return new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
            }
        }
        return null;
    }
}
