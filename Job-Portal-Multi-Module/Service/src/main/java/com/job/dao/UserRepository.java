package com.job.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.job.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<User> findById(Long id);
	
	@Transactional
	@Modifying
	@Query("update User set password=?2 where id=?1")
	void updatePassword(Long id, String password);
	
	
	
	
	
	
}