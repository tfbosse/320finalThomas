/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author Aidan White
 */
public class RevenueTitleForm {
    
    private String total;
    private String title;
    
    public RevenueTitleForm(){
        super();
    }
    
    public RevenueTitleForm(String total, String title){
        this.total = total;
        this.title = title;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
     
}
