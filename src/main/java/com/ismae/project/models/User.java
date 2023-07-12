package com.ismae.project.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User
{
	public interface CreateUser{}
	public interface UpdateUser{}

	public static final String TABLE_NAME = "users";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "username", length = 100, nullable = false, unique = true)
	@NotNull(groups = CreateUser.class)
	@NotEmpty(groups = CreateUser.class)
	@Size(groups = CreateUser.class, min = 2, max = 100)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password", length = 60, nullable = false)
	@NotNull(groups = {CreateUser.class, UpdateUser.class})
	@NotEmpty(groups = {CreateUser.class, UpdateUser.class})
	@Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Task> tasks = new ArrayList<Task>();
	
	public User(){}
	
	public User(Long id_, String username_, String password_)
	{
		this.id = id_;
		this.username = username_;
		this.password = password_;
	}

	public Long getId()
	{
		return this.id;
	}

	public String getUsername()
	{
		return this.username;
	}

	public String getPassword()
	{
		return this.password;
	}

	@JsonIgnore
	public List<Task> getTasks()
	{
		return this.tasks;
	}

	public void setId(Long id_)
	{
		this.id = id_;
	}

	public void setUsername(String username_)
	{
		this.username = username_;
	}

	public void setPassword(String password_)
	{
		this.password = password_;
	}

	public void setTask(List<Task> tasks_)
	{
		this.tasks = tasks_;
	}

	@Override 
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		if(!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && 
				Objects.equals(username, user.username) && 
				Objects.equals(password, user.password);
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


