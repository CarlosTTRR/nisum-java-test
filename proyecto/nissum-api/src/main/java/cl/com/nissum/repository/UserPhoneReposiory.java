package cl.com.nissum.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.com.nissum.entities.Userphone;

public interface UserPhoneReposiory extends JpaRepository<Userphone, UUID> {

}
