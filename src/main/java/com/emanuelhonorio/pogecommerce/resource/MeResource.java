package com.emanuelhonorio.pogecommerce.resource;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emanuelhonorio.pogecommerce.model.Compra;
import com.emanuelhonorio.pogecommerce.model.Usuario;
import com.emanuelhonorio.pogecommerce.repository.CompraRepository;
import com.emanuelhonorio.pogecommerce.repository.UsuarioRepository;

@RestController
@RequestMapping("/me")
public class MeResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CompraRepository compraRepository;

	@GetMapping
	public Usuario getMe(Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return usuarioOpt.get();
	}

	@GetMapping("/compras")
	public List<Compra> listar(Principal principal) {
		Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailIgnoreCase(principal.getName());
		return compraRepository.findByUsuario(usuarioOpt.get());
	}

}
