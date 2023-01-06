package com.blogapplication.Exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

	 String resourceName;
	
	 String fields;
	
	 long fieldValue;

	public ResourceNotFoundExceptions(String resourceName, String fields, long fieldValue) {
		super(String.format("%s not found with %s : %s",resourceName,fields,fieldValue));
		this.resourceName = resourceName;
		this.fields = fields;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundExceptions(String message) {
		super(message);
		
	}
	
	
}
