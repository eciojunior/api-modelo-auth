package br.com.model.auth.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.auth.security.AuthUser;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));
		return new AuthUser(user, getAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getAuthorities(UserEntity user) {
		List<String> authorities = new ArrayList<>();
		authorities.add(user.getAuthority().toUpperCase());
		return authorities.stream()
				.map(p -> new SimpleGrantedAuthority(p))
				.collect(Collectors.toSet());
	}

}
