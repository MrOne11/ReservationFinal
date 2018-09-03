package icc.be.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import icc.be.entites.Type;

public interface TypeRepository extends JpaRepository<Type, Long>{
	public Type findByIntitule(String intitule);
	
	@Query("select t from Type  t where t.intitule like :x")
public Page<Type> chercherIntitule(@Param("x") String motcle, Pageable pageable);
}
