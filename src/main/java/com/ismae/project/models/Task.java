package com.ismae.project.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task 
{
	public static final String TABLE_NAME = "tasks";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;
		
	@Column(name = "description", length = 255, nullable = false)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 255)
	private String description;

	public Task(){}

	public Task(Long id_, User user_, String description_)
	{
		this.id = id_;
		this.user = user_;
		this.description = description_;
	}

	public Long getId()
	{
		return this.id;
	}

	public User getUser()
	{
		return this.user;
	}

	public String getDescription()
	{
		return this.description ;
	}

	public void setId(Long id_)
	{
		this.id = id_;
	}

	public void setUser(User user_)
	{
		this.user = user_;
	}

	public void setDescription(String description_)
	{
		this.description = description_;
	}

	@Override 
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof User))
			return false;
		Task task = (Task) o;
		return Objects.equals(id, task.id) && 
				Objects.equals(user, task.user) && 
				Objects.equals(description, task.description);
	}

	@Override 
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null)? 0 : this.id.hashCode());
		return result;
	}
}
