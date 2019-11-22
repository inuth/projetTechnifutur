package be.spring.bootProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import be.spring.bootProject.entities.User;
import be.spring.bootProject.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("ICI");
		User user = userRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		System.out.println("ET LA");
		return user;
	}
}

