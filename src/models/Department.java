package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -865983026437689719L;
	private String ID;
	private String name;
	private Professor headOfDepartment;
	private List<Professor> professors;
	
	public Department(String iD, String name, Professor headOfDepartment, List<Professor> professors) {
		super();
		ID = iD;
		this.name = name;
		this.headOfDepartment = headOfDepartment;
		this.professors = professors;
	}
	public Department(String iD, String name, Professor headOfDepartment) {
		super();
		ID = iD;
		this.name = name;
		this.headOfDepartment = headOfDepartment;
		this.professors = new ArrayList<Professor>();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Professor getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(Professor headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
	
	
}
