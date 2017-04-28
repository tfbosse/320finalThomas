/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author landr
 */
public class CheckOutForm {
    
    String title, rentalid, startdate, enddate, duedate;

    public CheckOutForm(String title, String rentalid, String rentaldate, String returndate, String duedate){
        this.title=title;
        this.startdate = startdate;
        this.enddate = enddate;
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

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setRentalid(String rentalid) {
        this.rentalid = rentalid;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    
    
       
    
}
