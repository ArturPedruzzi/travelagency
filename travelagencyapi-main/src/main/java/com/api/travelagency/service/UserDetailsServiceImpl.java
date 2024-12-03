package com.api.travelagency.service;

import com.api.travelagency.model.Usuario;
import com.api.travelagency.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo nome de usuário no banco de dados
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Converte o usuário em um objeto UserDetails
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Senha já deve estar criptografada
                .roles("USER") // Defina o papel do usuário, ajuste conforme necessário
                .build();
    }
}
