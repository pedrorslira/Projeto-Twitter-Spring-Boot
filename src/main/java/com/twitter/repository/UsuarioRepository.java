package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.twitter.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(value = "select * from tb_usuario where tipo = '0'",nativeQuery = true)
	public List<Usuario> getSeguidores();
	
	@Query(value = "select * from tb_usuario where tipo = '1'",nativeQuery = true)
	public List<Usuario> getSeguindo();
	
	@Query(value = "select * from tb_usuario where id_twitter like :idTwitter",nativeQuery = true)
	public List<Usuario> getUsuarioByIdTwitter(@Param("idTwitter")long idTwitter);
}
