package com.net.blog.repositories.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -1299613059597956844L;
	
	public ResourceNotFoundException(Object id)
	{
		super("Resource not found. Id " + id);
	}
}