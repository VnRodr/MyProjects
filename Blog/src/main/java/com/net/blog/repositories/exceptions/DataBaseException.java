package com.net.blog.repositories.exceptions;

public class DataBaseException extends RuntimeException 
{
	private static final long serialVersionUID = 8847718499912058689L;

	public DataBaseException(String msg)
	{
		super(msg);
	}
}
