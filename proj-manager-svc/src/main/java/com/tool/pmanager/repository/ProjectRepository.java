package com.tool.pmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tool.pmanager.component.ProjectComp;


@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<ProjectComp, Integer> {

	@Query("from ProjectComp proj where proj.projectId=(:projectId)")
    ProjectComp findByProjectId(@Param("projectId") int projectId);
	
	@Query("from ProjectComp proj where proj.userEnt.userId=(:userId)")
    ProjectComp findByUserId(@Param("userId") int userId);
	
	@Modifying
	@Query(value = "DELETE FROM PROJECT where user_id=(:userId)", nativeQuery = true)
    void deleteByUserId(@Param("userId") int userId);
}
