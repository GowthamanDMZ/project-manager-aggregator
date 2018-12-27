package com.tool.pmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tool.pmanager.component.ParentTaskComp;


@Repository
@Transactional
public interface ParentTaskRepository extends JpaRepository<ParentTaskComp, Integer> {
	
	@Query("from ParentTaskComp pTask where pTask.parentId=(:parentId)")
	ParentTaskComp findByParentTaskId(@Param("parentId") int parentId);
	
	@Modifying
	@Query(value = "DELETE FROM PARENT_TASK where project_id=(:projectId)", nativeQuery = true)
    void deleteByProjectId(@Param("projectId") int projectId);
}
