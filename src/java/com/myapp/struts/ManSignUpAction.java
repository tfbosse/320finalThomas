/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

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
 * @author Aidan White
 */
public class ManSignUpAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
        
        ManagerForm mansignUpForm = (ManagerForm) form;
        
        String firstname = mansignUpForm.getFirstname();
        String lastname = mansignUpForm.getLastname();
        String email = mansignUpForm.getEmail();
        String username = mansignUpForm.getUsername();
        String password = mansignUpForm.getPassword();
        
        if (firstname.isEmpty()) {
            errors.add("firstname", new ActionMessage("errors.required", "A first name"));
        } else if (firstname.length() > 50) {
            errors.add("firstname", new ActionMessage("errors.maxlength", "First name", "50"));
        } else if (firstVal(firstname) == false) {
            errors.add("firstname", new ActionMessage("errors.invalid", "First name"));
        } else if (lastname.isEmpty()) {
            errors.add("lastname", new ActionMessage("errors.required", "A last name"));
        } else if (lastname.length() > 50) {
            errors.add("lastname", new ActionMessage("errors.maxlength", "Last name", "50"));
        } else if (lastVal(lastname) == false) {
            errors.add("lastname", new ActionMessage("errors.invalid", "Last name"));
        } else if (email.isEmpty()) {
            errors.add("email", new ActionMessage("errors.required", "An email address"));
        } else if (email.length() > 100) {
            errors.add("email", new ActionMessage("errors.maxlength", "Email address", "100"));
        } else if (emailVal(email) == false) {
            errors.add("email", new ActionMessage("errors.invalid", "Email address"));
        } else if (username.isEmpty()) {
            errors.add("username", new ActionMessage("errors.required", "A username"));
        } else if (username.length() > 100) {
            errors.add("username", new ActionMessage("errors.maxlength", "Username", "100"));
        } else if (password.isEmpty()) {
            errors.add("password", new ActionMessage("errors.required", "A password"));
        } else if (passVal(password) == false) {
            errors.add("password", new ActionMessage("errors.invalid", "Password"));
        }
        
        this.saveErrors(request, errors);
        
        if (getErrors(request).isEmpty()) {
            managerDAO mdao = new managerDAO();
            mdao.insertManager(mansignUpForm);
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}

