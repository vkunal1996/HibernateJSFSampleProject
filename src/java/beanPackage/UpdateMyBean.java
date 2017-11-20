/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vkunal1996
 */
@Named(value = "updateMyBean")
@SessionScoped
public class UpdateMyBean implements Serializable {

    private String rollNumber,studentName,fatherName,motherName,sex,address,contact,email;
    
    Session sess;
    StudentDetails s;
    
    private String newRoll;
    
    public void myActionListener(ActionEvent event)
    {
        newRoll=(String)event.getComponent().getAttributes().get("rollNumber");
    }
    
    public void updateDetails() throws IOException
    {
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
            message="Student Already Exists";
            FacesContext.getCurrentInstance().getExternalContext().dispatch("UpdateForm.xhtml");
            
        }
        else
        {
            message="Student Don't Exists";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",message));

        }
        sess.close();
    }
    
    public void updateRecord()
    {
        sess=HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        s=new StudentDetails();
        Criteria criteria=sess.createCriteria(StudentDetails.class);
        criteria.add(Restrictions.eq("rollNumber",rollNumber));
        criteria.setProjection(Projections.rowCount());
        long count=(Long)criteria.uniqueResult();
      
        String sql="Update StudentDetails set StudentName=:studentName , FatherName=:fatherName , MotherName=:motherName , Email=:email , Contact=:contact , Sex=:sex , Address=:address where RollNumber=:rollNumber";
        Query q = sess.createQuery(sql);
        q.setParameter("rollNumber",rollNumber);
        q.setParameter("studentName",studentName);
        q.setParameter("fatherName",fatherName);
        q.setParameter("motherName",motherName);
        q.setParameter("email",email);
        q.setParameter("contact",contact);
        q.setParameter("sex",sex);
        q.setParameter("address",address);
        int rl=q.executeUpdate();
        sess.getTransaction().commit();
        message="Record for "+rollNumber+" is Updated SuccessFully";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",message));
        setStudentName(null);
        setRollNumber(null);
        setFatherName(null);
        setMotherName(null);
        setContact(null);
        setSex(null);
        setAddress(null);
        setEmail(null);
      
        sess.close();
        
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String message;
    public UpdateMyBean() {
    }
    
}
