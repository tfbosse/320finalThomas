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
 * @author Thomas
 */
public class ManUpdateAction extends org.apache.struts.action.Action {
    
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

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

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
        
        UpdateForm updateForm = (UpdateForm) form;
        String firstname = updateForm.getFirstname();
        String lastname = updateForm.getLastname();
        String email = updateForm.getEmail();
        String password = updateForm.getPassword();
        
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
        } else if (password.isEmpty()) {
            errors.add("password", new ActionMessage("errors.required", "A password"));
        } else if (password.length() > 100) {
          errors.add("password", new ActionMessage("errors.maxlength", "Password", "100"));  
        } else if (passVal(password) == false) {
            errors.add("password", new ActionMessage("errors.invalid", "Password"));
        }
        
        this.saveErrors(request, errors);
        
        if (getErrors(request).isEmpty()) {
            managerDAO mdao = new managerDAO();
            mdao.editManager(updateForm);
            
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
