package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Professor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5577095404530277414L;
	private String surname;
	private String name;
	private Date birthDate;
	private Adress adress;
	private String phone;
	private String email;
	private Adress officeAdress;
	private int idNumber;
	private String title;
	private int serviceYears;
	private List<Subject> subjects;
	public Professor(String surname, String name, Date birthDate, Adress adress, String phone, String email,
			Adress officeAdress, int idNumber, String title, int serviceYears, List<Subject> subjects) {
		super();
		this.surname = surname;
		this.name = name;
		this.birthDate = birthDate;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.officeAdress = officeAdress;
		this.idNumber = idNumber;
		this.title = title;
		this.serviceYears = serviceYears;
		this.subjects = subjects;
	}
	
	
	
	
	
	public Professor(String surname, String name, Date birthDate, Adress adress, String phone, String email,
			Adress officeAdress, int idNumber, String title, int serviceYears) {
		super();
		this.surname = surname;
		this.name = name;
		this.birthDate = birthDate;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.officeAdress = officeAdress;
		this.idNumber = idNumber;
		this.title = title;
		this.serviceYears = serviceYears;
		this.subjects = new ArrayList<Subject>();
	}






	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Adress getOfficeAdress() {
		return officeAdress;
	}
	public void setOfficeAdress(Adress officeAdress) {
		this.officeAdress = officeAdress;
	}
	public int getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getServiceYears() {
		return serviceYears;
	}
	public void setServiceYears(int serviceYears) {
		this.serviceYears = serviceYears;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	

}
