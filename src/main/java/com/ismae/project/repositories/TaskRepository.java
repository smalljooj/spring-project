package com.ismae.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.ismae.project.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{

	/*
	List<Task> findByUser_Id(Long id);

	@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
	List<Task> findByUser_Id(@Param("id") Long id);

	@Query(value = "SELECT * FROM tasks t WHERE t.user_id = :id", nativeQuery = true)
	List<Task> findByUser_Id(@Param("id") Long id);
	*/
}
