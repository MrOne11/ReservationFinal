package icc.be.metier;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import icc.be.dao.UserRepository;
import icc.be.entites.AppUser;
import icc.be.entites.AppUser.AppRole;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository ;


	

	@Override
	public AppUser findUserByUsername(String username) {
		// TODO Auto-generated method stub
				return userRepository.findByUsername(username);
	}
	public AppUser saveUser(AppUser user,AppRole role) {
		// recupere le mot de passe saisi par l user puis je l'encode
				String hashPw=bCryptPasswordEncoder.encode(user.getPassword());
				user.setPassword(hashPw);
				role.getDisplayName();
				return userRepository.save(user);
	}
	@Override
	public AppUser saveUser(AppUser user) {
		// recupere le mot de passe saisi par l user puis je l'encode
		String hashPw=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPw);
		return userRepository.save(user);
	}
	
	


	
	
	
	
	
	
}
