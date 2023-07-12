package com.ismae.project.services;

import com.ismae.project.repositories.UserRepository;

import java.util.Optional;

import com.ismae.project.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository;

	public User findById(Long id)
	{
		Optional<User> user = this.userRepository.findById(id);
		return user.orElseThrow(() -> new RuntimeException("Usuario nao encontrado: id i " + id + "."));
	}

	@Transactional 
	public User createUser(User obj)
	{
		obj.setId(null);
		obj = this.userRepository.save(obj);
		return obj;
	}

	@Transactional 
	public User updateUser(User obj)
	{
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return this.userRepository.save(newObj);
	}

	@Transactional
	public void delete(Long id)
	{
		findById(id);
		try 
		{
			this.userRepository.deleteById(id);	
		}
		catch(Exception e) 
		{
			throw new RuntimeException("Erro ao deletar o usuario, pois ha entidades relacionadas");
		}
	}


}
