package com.sd.rest.login;

import com.sd.beans.rol.RolB;
import com.sd.beans.usuario.UsuarioB;
import com.sd.service.usuario.IUsuarioService;
import grails.plugin.springsecurity.userdetails.GrailsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private IUsuarioService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

       UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());
        UsuarioB user = getUser(username);

        if(user != null){
            System.out.println(password);
            System.out.println(user);
        if(passwordEncoder.matches(password,user.getPassword())){
            System.out.println(password);

     List<GrantedAuthority> authorities = getUserRoles(user);
     if(authorities !=null){
         GrailsUser userDetails = new GrailsUser(username,password,true,true,true,true,authorities,user.getId());
         UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
         token.setDetails(authentication.getDetails());
         return token;
     }else{
         throw new BadCredentialsException("El usuario no tiene ningun rol");
     }
 }else{
         throw new BadCredentialsException("Contraseña incorrecta");
     }

 }
return auth;
}

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getUserRoles(UsuarioB user) {
       List<GrantedAuthority> list = new ArrayList<>();
       final List<RolB> roles = user.getRoles();
       if(roles != null){
           for(RolB roleB : roles){
               System.out.println("name " + roleB.getNombre());
               list.add(new SimpleGrantedAuthority(roleB.getDescripcion()));
           }
       }

       return list;

    }

    private UsuarioB getUser(String username) {
        UsuarioB userB = userService.getUsuarioByUsername(username);
        return userB;
    }


}