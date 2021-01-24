package com.emanuelhonorio.pogecommerce.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.dto.UsuarioDTO;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.service.UsuarioService;

@RestController
@RequestMapping("/register")
public class RegisterResource {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public Usuario register(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioService.register(usuarioDTO);
		return usuario;
	}

}
