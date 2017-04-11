/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//figuring this shit out
package com.myapp.struts;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Thomas
 */
public class SignUpAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    
    public boolean emailVal(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean passVal(String password) {
        if (password.length() < 8) {
            return false;
        }
        if (password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addressVal(String address) {
        String[] split = address.split(" ");
        if (split.length < 3) {
            return false;
        }
        if (split[0].matches(".*[a-z][A-Z].*")) {
            return false;
        }
        return true;
    }
    
    public boolean cityVal(String city) {
        if (city.matches("^[ A-Za-z]+$")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean stateVal(String state) {
        ArrayList<String> states = new ArrayList();
        Collections.addAll(states, "alabama", "alaska", "arizona", "arkansas", "california", "colorado", "connecticut", 
                "delaware", "florida", "georgia", "hawaii", "hawai'i", "idaho", "illinois", "indiana", "iowa", "kansas", 
                "kentucky", "louisiana", "maine", "maryland", "massachusetts", "michigan", "minnesota", "mississippi", 
                "missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey", "new mexico", "new york", 
                "north carolina", "north dakota", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", 
                "south carolina", "south dakota", "tennessee", "texas", "utah", "vermont", "virginia", "washington", 
                "west virginia", "wisconsin", "wyoming", "d. c.", "dc", "d c", "d.c.", "district of columbia");
        state = state.toLowerCase();
        if (states.contains(state)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean zipVal(String zip) {
        if (zip.length() != 5) {
            return false;
        }
        if (zip.matches(".*[a-z][A-Z].*")) {
            return false;
        }
        return true;
    }
    
    public boolean firstVal(String firstname) {
        if (firstname.matches("^[ A-Za-z]+$")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean lastVal(String lastname) {
        if (lastname.matches("^[ A-Za-z]+$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionErrors errors = new ActionErrors();
        
        SignUpForm signUpForm = (SignUpForm) form;
        String firstname = signUpForm.getFirstname();
        String lastname = signUpForm.getLastname();
        String email = signUpForm.getEmail();
        String username = signUpForm.getUsername();
        String password = signUpForm.getPassword();
        String address = signUpForm.getAddress();
        String city = signUpForm.getCity();
        String state = signUpForm.getState();
        String zip = signUpForm.getZip();
        
        if (firstname.isEmpty()) {
            errors.add("firstname", new ActionMessage("errors.required", "A first name"));
        } else if (firstname.length() > 45) {
            errors.add("firstname", new ActionMessage("errors.maxlength", "First name", "45"));
        } else if (firstVal(firstname) == false) {
            errors.add("firstname", new ActionMessage("errors.invalid", "First name"));
        } else if (lastname.isEmpty()) {
            errors.add("lastname", new ActionMessage("errors.required", "A last name"));
        } else if (lastname.length() > 45) {
            errors.add("lastname", new ActionMessage("errors.maxlength", "Last name", "45"));
        } else if (lastVal(lastname) == false) {
            errors.add("lastname", new ActionMessage("errors.invalid", "Last name"));
        } else if (email.isEmpty()) {
            errors.add("email", new ActionMessage("errors.required", "An email"));
        } else if (email.length() > 50) {
            errors.add("email", new ActionMessage("errors.maxlength", "Email", "50"));
        } else if (emailVal(email) == false) {
            errors.add("email", new ActionMessage("errors.invalid", "Email"));
        } else if (username.isEmpty()) {
            errors.add("username", new ActionMessage("errors.required", "A username"));
        } else if (username.length() > 100) {
            errors.add("username", new ActionMessage("errors.maxlength", "Username", "100"));
        } else if (password.isEmpty()) {
            errors.add("password", new ActionMessage("errors.required", "A password"));
        } else if (password.length() > 100) {
          errors.add("password", new ActionMessage("errors.maxlength", "Password", "100"));  
        } else if (passVal(password) == false) {
            errors.add("password", new ActionMessage("errors.invalid", "Password"));
        } else if (address.isEmpty()) {
            errors.add("address", new ActionMessage("errors.required", "An address"));
        } else if (address.length() > 50) {
            errors.add("address", new ActionMessage("errors.maxlength", "Address", "50"));
        } else if (addressVal(address) == false) {
           errors.add("address", new ActionMessage("errors.invalid", "Address")); 
        }else if (city.isEmpty()) {
            errors.add("city", new ActionMessage("errors.required", "A city"));
        } else if (city.length() > 50) {
            errors.add("city", new ActionMessage("errors.maxlength", "City", "50"));
        } else if (cityVal(city) == false) {
            errors.add("city", new ActionMessage("errors.invalid", "City"));
        } else if (state.isEmpty()) {
            errors.add("state", new ActionMessage("errors.required", "A state"));
        } else if (state.length() > 20) {
            errors.add("state", new ActionMessage("errors.maxlength", "State", "20"));
        } else if (stateVal(state) == false) {
            errors.add("state", new ActionMessage("errors.invalid", "State"));
        } else if (zip.isEmpty()) {
            errors.add("zip", new ActionMessage("errors.required", "A zip code"));
        } else if (zip.length() > 10) {
            errors.add("zip", new ActionMessage("errors.maxlength", "Zip code", "10"));
        } else if (zipVal(zip) == false) {
            errors.add("zip", new ActionMessage("errors.invalid", "Zip code"));
        }
        
        this.saveErrors(request, errors);
        
        if (getErrors(request).isEmpty()) {
            customerDAO cust = new customerDAO();
            cust.insertCustomer(signUpForm);
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
