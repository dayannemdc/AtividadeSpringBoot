package com.minhaLojaDeGames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.minhaLojaDeGames.model.Usuario;
import com.minhaLojaDeGames.model.Utility.UsuarioDTO;
import com.minhaLojaDeGames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repository;
	
	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repository.findByEmail(novoUsuario.getEmail()).map(usuarioExistente ->{
			return Optional.empty();
		}).orElseGet(()->{
			BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
			String result = enconder.encode(novoUsuario.getSenha());
			novoUsuario.setSenha(result);
			return Optional.ofNullable(repository.save(novoUsuario));
		});
	}
	
	public Optional<Object> atualizarUsuario (Usuario usuarioParaAtualizar) {
		return repository.findByEmail(usuarioParaAtualizar.getEmail()).map(usuarioExistente ->{
			return Optional.empty();
		}).orElseGet(()->{
			return Optional.ofNullable(repository.save(usuarioParaAtualizar));
		});
	}
	
	public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar){
		return repository.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {
				
				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha(); 
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64); 

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getIdUsuario());
				usuarioParaAutenticar.setNome(usuarioExistente.getNome());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				return Optional.ofNullable(usuarioParaAutenticar); 
			} else {
				return Optional.empty();
			}
			
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
}
