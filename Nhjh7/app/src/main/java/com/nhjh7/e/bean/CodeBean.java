package com.nhjh7.e.bean;

/**
 * Created by yline on 2017/2/6.
 */
public class CodeBean
{
	private int id;

	private String name;

	private String email;

	private String message;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "CodeBean{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
