package com.learn.springBoot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//with this config (@entity and @id), JPA knows to convert a topic instance to a DB row and vice versa.

@Entity //(with this annotation JPA knows that it is a class which interacts with database and creates table/columns in the database)
public class Topic {

@Id	//(@Id this denotes that the member variable "id" below is mapped to a column which is a primary key in Database. If you mark @Id for "name" member variable, then that denotes that, the column which is mapped to "name" vaiable in database is a primary ke )
private String id;
private String name;
private String description;

public Topic() {
	}

public Topic(String id, String name, String description) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}


	
}
