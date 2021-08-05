package cl.com.nissum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import cl.com.nissum.entities.Usersnisum;

@Component
public interface UserRegisterRepository extends JpaRepository<Usersnisum, UUID>{
	
	@Query(nativeQuery = true, value="SELECT COUNT (*) FROM USERSNISUM WHERE email=(:email)")
	Integer checkEmail(@Param("email") String email);
	
	@Query(nativeQuery = true, value="SELECT * FROM USERSNISUM WHERE email=(:email)")
	Usersnisum findByEmail(@Param("email") String email);
}
