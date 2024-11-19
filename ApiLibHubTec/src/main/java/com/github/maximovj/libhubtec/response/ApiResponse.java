package com.github.maximovj.libhubtec.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse 
{
	
	String ctx_title;
	String ctx_content;
	String uri;
	String type;
	int code; 
	String status;
	boolean success;

}