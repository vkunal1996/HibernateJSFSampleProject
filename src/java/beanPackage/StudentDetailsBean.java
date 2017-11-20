/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;

/**
 *
 * @author vkunal1996
 */
@Named(value = "studentDetailsBean")
@RequestScoped
public class StudentDetailsBean implements Serializable {

   private String rollNumber,studentName,fatherName,motherName,address,contact,sex,email;
   private String message;
   StudentDetails s;
   Session sess;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void saveRecord(){
        sess=HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        s=new StudentDetails();
        Criteria criteria=sess.createCriteria(StudentDetails.class);
        criteria.add(Restrictions.eq("rollNumber",rollNumber));
        criteria.setProjection(Projections.rowCount());
        long count=(Long)criteria.uniqueResult();
        //System.out.println(count);
        if(count!=0)
        {
            message="User Already Exists";
        }
        else
        {
        s.setAddress(address);
        s.setContact(contact);
        s.setEmail(email);
        s.setFatherName(fatherName);
        s.setMotherName(motherName);
        s.setRollNumber(rollNumber);
        s.setSex(sex);
        s.setStudentName(studentName);
        
        sess.save(s);
        sess.getTransaction().commit();
        message="Record Successfully Saved!";
        setStudentName(null);
        setRollNumber(null);
        setFatherName(null);
        setMotherName(null);
        setContact(null);
        setSex(null);
        setAddress(null);
        setEmail(null);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",message));
 
        sess.close();
    
    }
    public StudentDetailsBean() {
    }
    
}
