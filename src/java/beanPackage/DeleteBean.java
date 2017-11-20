/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanPackage;

import javax.inject.Named;
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
@Named(value = "deleteBean")
@RequestScoped
public class DeleteBean {

    private String message,rollNumber;

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
    Session sess;
    StudentDetails s;
    
    public void deleteRecord()
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
            String sq="Delete From StudentDetails where RollNumber=:rollNumber";
            Query q = sess.createQuery(sq);
            q.setParameter("rollNumber", rollNumber);
            int rl=q.executeUpdate();
        
            sess.getTransaction().commit();
            message="Record Deleted SuccessFully";
            setRollNumber(null);
        }
        else
        {
            message="No Student with RollNumber= "+rollNumber+" found";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",message));

        sess.close();
    
    }
    public DeleteBean() {
    }
    
}
