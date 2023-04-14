package com.portfolio.pruebajwt.security.service;

import com.portfolio.pruebajwt.dto.Mensaje;
import com.portfolio.pruebajwt.security.dto.JwtDto;
import com.portfolio.pruebajwt.security.dto.LoginUsuario;
import com.portfolio.pruebajwt.security.dto.NuevoUsuario;
import com.portfolio.pruebajwt.security.repository.UsuarioRepository;
import com.portfolio.pruebajwt.security.entity.Usuario;
import com.portfolio.pruebajwt.security.entity.Rol;
import com.portfolio.pruebajwt.security.enums.RolNombre;
import com.portfolio.pruebajwt.security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    public Mensaje save(NuevoUsuario nuevoUsuario){
        if(usuarioRepository.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())){
            return new Mensaje("ese usuario ya existe");
        }
        Usuario usuario = new Usuario(nuevoUsuario.getNombreUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return new Mensaje(usuario.getNombreUsuario() + " ha sido creado");
    }
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public JwtDto login(LoginUsuario loginUsuario){
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), 
                        loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }
}
