package com.tool.pmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tool.pmanager.component.UserComp;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserComp, Integer> {
	
	@Query("from UserComp usr where usr.userId=(:userId)")
	UserComp findByUserId(@Param("userId") int userId);
}
