/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Date;

/**
 *
 * @author Aidan White
 */
public class RentalForm {
    
    int customer_id, film_id, line_total, penalty;
    Date rental_date, last_update, due_date, return_date;
    
    
    public RentalForm(){
        super();
    } 
    
    public RentalForm(int customer_id, Date rental_date, Date last_update, int film_id, 
            Date due_date, Date return_date, int line_total, int penalty){
        this.customer_id = customer_id;
        this.rental_date = rental_date;
        this.last_update = last_update;
        this.film_id = film_id;
        this.due_date = due_date;
        this.return_date = return_date;
        this.line_total = line_total;
        this.penalty = penalty;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getLine_total() {
        return line_total;
    }

    public void setLine_total(int line_total) {
        this.line_total = line_total;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
        
    
    
    
}
