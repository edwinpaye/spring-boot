package com.rest.project.controller;

import com.rest.project.dto.JwtDTO;
import com.rest.project.dto.LoginUsuario;
import com.rest.project.entity.Rol;
import com.rest.project.entity.Usuario;
import com.rest.project.enums.RolNombre;
import com.rest.project.repository.RolRepository;
import com.rest.project.repository.UsuarioRepository;
import com.rest.project.security.JWT.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody Usuario nuevoUsuario, BindingResult bindingResult){
//        nuevoUsuarioValidator.validate(nuevoUsuario, bindingResult, usuarioService);
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
        for (Rol rol : rolesStr) {
            switch (rol.getRolNombre()) {
                case ROLE_ADMIN:
                    Rol rolAdmin = rolRepository.findByRolNombre(RolNombre.ROLE_ADMIN).get();
                    roles.add(rolAdmin);
                    break;
                default:
                    Rol rolUser = rolRepository.findByRolNombre(RolNombre.ROLE_USER).get();
                    roles.add(rolUser);
            }
        }
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
        return new ResponseEntity("usuario guardado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtDTO);    }
}
