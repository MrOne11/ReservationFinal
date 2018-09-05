package icc.be.metier;

import icc.be.entites.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser userE);
	public AppUser findUserByUsername(String username);

}
