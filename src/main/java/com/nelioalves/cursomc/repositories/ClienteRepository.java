package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelioalves.cursomc.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ClienteRepository extends JpaRepository<User, Integer> {

	@Transactional(readOnly=true)
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u  ORDER BY CASE WHEN u.id = :userId THEN 0 ELSE 1 END, u.nome ASC")
	Page<User> findOrderedByName(@Param("userId") Integer userId, Pageable pageable);
}
