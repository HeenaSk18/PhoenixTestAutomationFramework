package com.api.response.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // this imp
@AllArgsConstructor
@Data

public class CreateJobResponseModel {
private	String message;
private	CreateJobDataModel data;
	
	
}
