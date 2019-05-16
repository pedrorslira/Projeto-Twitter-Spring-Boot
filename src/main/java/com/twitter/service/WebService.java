package com.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.model.TipoDeUsuario;
import com.twitter.model.Usuario;
import com.twitter.repository.UsuarioRepository;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class WebService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public void coletaDeDados(String nickname,String consumerKey,String consumerSecret,String acessToken,String acessSecret) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(acessToken)
				.setOAuthAccessTokenSecret(acessSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		long cursor = -1;
		IDs idsSeguidores, idsSeguindo;
		User user;
		Usuario usuario;
		do {
			idsSeguidores = twitter.getFollowersIDs(nickname, cursor);
			for (long id : idsSeguidores.getIDs()) {
				user = twitter.showUser(id);
				usuario = new Usuario();
				usuario.setIdTwitter(id);
				usuario.setNome(user.getName());
				usuario.setNick(user.getScreenName());
				usuario.setTipo(TipoDeUsuario.SEGUIDOR);
				usuarioRepository.save(usuario);
			}
		} while ((cursor = idsSeguidores.getNextCursor()) != 0);
		cursor = -1;
		do {
			idsSeguindo = twitter.getFriendsIDs(nickname, cursor);
			for (long id : idsSeguindo.getIDs()) {
				user = twitter.showUser(id);
				usuario = new Usuario();
				usuario.setIdTwitter(id);
				usuario.setNome(user.getName());
				usuario.setNick(user.getScreenName());
				usuario.setTipo(TipoDeUsuario.SEGUIDO);
				usuarioRepository.save(usuario);
			}
		} while ((cursor = idsSeguindo.getNextCursor()) != 0);
	}
}
