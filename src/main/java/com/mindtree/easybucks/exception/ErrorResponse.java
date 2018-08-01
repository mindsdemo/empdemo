package com.mindtree.easybucks.exception;

import java.util.List;

public class ErrorResponse
{

	private String status;
    private Object object;    
    private String message;
    private List<String> details;

    public ErrorResponse(String message, String status, Object obj) {
        super();
        this.message = message;
        this.object = obj;
        this.status = status;
    }    

    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}
