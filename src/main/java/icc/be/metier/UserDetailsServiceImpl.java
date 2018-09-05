package icc.be.metier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import icc.be.entites.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private AccountService accoutService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user=accoutService.findUserByUsername(username);
		if(user==null) throw new UsernameNotFoundException(username);
		
		
	
	return  user;
}
}
