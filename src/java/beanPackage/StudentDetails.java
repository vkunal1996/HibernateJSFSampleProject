/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vkunal1996
 */
@Entity
@Table(name = "myDatabase")
public class StudentDetails implements Serializable{
    
    @Id
    @GeneratedValue
    private int srno;
    private String rollNumber;
    private String studentName;
    private String fatherName;
    private String motherName;
    private String contact;
    private String sex;
    private String address;
    private String email;
    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    
    public StudentDetails(){
    
    }
    public StudentDetails(String rollNumber){
        this.rollNumber=rollNumber;
    }
    public StudentDetails(Integer srno,String rollNumber,String studentName,String fatherName,String motherName,String contact,String sex,String address,String email){
    
            this.srno=srno;
            this.rollNumber=rollNumber;
            this.studentName=studentName;
            this.fatherName=fatherName;
            this.motherName=motherName;
            this.contact=contact;
            this.sex=sex;
            this.address=address;
            this.email=email;
    }
}
