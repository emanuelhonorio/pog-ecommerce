package com.emanuelhonorio.pogecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emanuelhonorio.pogecommerce.dto.UsuarioDTO;
import com.emanuelhonorio.pogecommerce.error.exceptions.ResourceAlreadyExistsException;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.repository.UsuarioRepository;

@Transactional
@Service
public class UsuarioService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario register(UsuarioDTO usuarioDTO) {
		
		if ( usuarioRepository.findByEmailIgnoreCase(usuarioDTO.getEmail()).isPresent() ) {
			throw new ResourceAlreadyExistsException("email already exist");
		}
		
		Usuario novoUsuario = new Usuario();
		String encryptedPassword = passwordEncoder.encode(usuarioDTO.getSenha());
		
		novoUsuario.setNome(usuarioDTO.getNome());
		novoUsuario.setEmail(usuarioDTO.getEmail());
		novoUsuario.setSenha(encryptedPassword);
		
		novoUsuario = usuarioRepository.save(novoUsuario);
		
		return novoUsuario;
	}

}
