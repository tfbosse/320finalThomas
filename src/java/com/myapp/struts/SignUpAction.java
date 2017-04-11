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
        return true;
    }
    
    public boolean passVal(String password) {
        return true;
    }
    
    public boolean addressVal(String address) {
        return true;
    }
    
    public boolean cityVal(String city) {
        return true;
    }
    
    public boolean stateVal(String state) {
        return true;
    }
    
    public boolean zipVal(String zip) {
        if (zip.length() != 5) {
            return false;
        }
        if (Pattern.matches("[a-z][A-Z]+", zip)) {
            return false;
        }
        return true;
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
        } else if (lastname.isEmpty()) {
            errors.add("lastname", new ActionMessage("errors.required", "A last name"));
        } else if (lastname.length() > 45) {
            errors.add("lastname", new ActionMessage("errors.maxlength", "Last name", "45"));
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
