/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import java.io.IOException;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vkunal1996
 */
@Named(value = "showRecords")
@RequestScoped
public class ShowRecords implements java.io.Serializable{

    private String rollNumber,studentName,fatherName,motherName,contact,email,sex,address;
    private String message;
    private int srno;

    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    
   
    private List<StudentDetails> listOfStudents;
    Session sess;
    public List<StudentDetails> getListOfStudents() {
        sess=HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        Query q = sess.createQuery ("from StudentDetails"); 
        listOfStudents= (List<StudentDetails>) q.list();
        return listOfStudents;
    }

    public void setListOfStudents(List<StudentDetails> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

   private List<StudentDetails> studentRecord;
   public void findRecord() throws IOException
   {
        sess=HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        StudentDetails s;
        s=new StudentDetails();
        Criteria criteria=sess.createCriteria(StudentDetails.class);
        criteria.add(Restrictions.eq("rollNumber",rollNumber));
        criteria.setProjection(Projections.rowCount());
        long count=(Long)criteria.uniqueResult();
        //System.out.println(count);
        if(count!=0)
        {
            message="Record Exists!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",message));
            FacesContext.getCurrentInstance().getExternalContext().dispatch("SearchResult.xhtml");

            getStudentRecord();
        }
        else
        {
          message="No Record found !";
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",message));
        }
   }
    public List<StudentDetails> getStudentRecord() {
        sess=HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        Query q = sess.createQuery ("from StudentDetails where RollNumber = :rollNumber"); 
        q.setParameter("rollNumber",rollNumber);
        studentRecord= (List<StudentDetails>) q.list();
        
        return studentRecord;
    
    }

    public void setStudentRecord(List<StudentDetails> studentRecord) {
        this.studentRecord = studentRecord;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ShowRecords() {
    }
    
}
