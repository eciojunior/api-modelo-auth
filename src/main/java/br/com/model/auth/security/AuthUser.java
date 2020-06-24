package br.com.model.auth.security;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.model.auth.entity.UserEntity;
import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String fullName;
	
	public AuthUser(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		
		this.userId = user.getId();
		this.fullName = user.getName();
	}
	
}