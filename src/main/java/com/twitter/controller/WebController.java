package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.model.Usuario;
import com.twitter.repository.UsuarioRepository;
import com.twitter.service.WebService;

import twitter4j.TwitterException;

@RestController
@CrossOrigin
@RequestMapping("/web")
public class WebController {

	@Autowired
	WebService webService;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping("/inserir/{nickname}")
	public void inserirUsuarios(@PathVariable String nickname,@RequestParam String consumerKey,@RequestParam String consumerSecret,@RequestParam String acessToken,@RequestParam String acessSecret) throws TwitterException {
		webService.coletaDeDados(nickname,consumerKey,consumerSecret,acessToken,acessSecret);
	}

	@GetMapping("/listar")
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public Usuario listarUsuarioPorId(@PathVariable long id) {
		return usuarioRepository.findOne(id); 
	}
	
	@GetMapping("/listar/seguidores")
	public List<Usuario> listarSeguidores(){
		return usuarioRepository.getSeguidores();
	}
	
	@GetMapping("/listar/seguindo")
	public List<Usuario> listarUsuariosSeguidos(){
		return usuarioRepository.getSeguindo();
	}
	
	@GetMapping("/listar/idTwitter/{idTwitter}")
	public List<Usuario> listarUsuariosByIdTwitter(@PathVariable long idTwitter){
		return usuarioRepository.getUsuarioByIdTwitter(idTwitter);
	}
	
}
