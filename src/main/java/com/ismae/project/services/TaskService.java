package com.ismae.project.services;

import java.util.Optional;

import com.ismae.project.repositories.TaskRepository;
import com.ismae.project.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TaskService 
{
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserService userService;

	public Task findById(Long id)
	{
		Optional<Task> task = this.taskRepository.findById(id);
		return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
	}

	@Transactional
	public Task createTask(Task obj)
	{
		User user = userService.findById(obj.getUser().getId());
		obj.setId(null);
		obj.setUser(user);
		return this.taskRepository.save(obj);
	}

	@Transactional
	public Task UpdateTask(Task obj)
	{
		Task newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return this.taskRepository.save(newObj);
	}

	@Transactional
	public void delete(Long id)
	{
		findById(id);
		try
		{
			this.taskRepository.deleteById(id);
		}
		catch(Exception e)
		{
			throw new RuntimeException("Task nao deletado por pertencer a uma relacao");
		}
	}

}
