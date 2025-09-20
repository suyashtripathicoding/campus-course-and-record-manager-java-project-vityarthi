package edu.ccrm.domain;

import java.time.LocalDate;
public abstract class Person {
private String id;
private String fullName;
private String email;
private LocalDate dateJoined ;
public Person(String id, String fullName , String email) {
	this.id = id;
	this.fullName = fullName;
	this.email =  email;
	this.dateJoined = LocalDate.now();
}
//abstract method to be implemented by subclass
public abstract String getRole();
//get and set methods 
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getFullName() {
	return fullName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public LocalDate getDateJoined() {
	return dateJoined ;
}

}





