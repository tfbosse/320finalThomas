/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Date;

/**
 *
 * @author landr
 */
public class CheckOutForm {
    
    String title, rentalid; 
    Date rentaldate, enddate, duedate;

    public CheckOutForm(String title, String rentalid, Date rentaldate, Date returndate, Date duedate){
        this.title=title;
        this.rentalid=rentalid;
        this.rentaldate = rentaldate;
        this.enddate = returndate;
        this.duedate = duedate;
    }
       public CheckOutForm (){
       super();
   }

    public String getTitle() {
        return title;
    }

    public String getRentalid() {
        return rentalid;
    }

    public Date getRentaldate() {
        return rentaldate;
    }

    public Date getReturndate() {
        return enddate;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setRentalid(String rentalid) {
        this.rentalid = rentalid;
    }

    public void setRentaldate(String startdate) {
        this.rentaldate = rentaldate;
    }

    public void setReturndate(Date returndate) {
        this.enddate = enddate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
    
    
       
    
}
