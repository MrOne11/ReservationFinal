package icc.be.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import icc.be.entites.AppUser;


public interface UserRepository extends JpaRepository<AppUser, Long>{
	public AppUser findByUsername(String username);
}
