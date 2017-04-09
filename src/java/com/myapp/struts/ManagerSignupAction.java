/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

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
public class ManagerSignupAction extends org.apache.struts.action.Action {

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
        
        ManagerForm mansignUpForm = (ManagerForm) form;
        
        String firstname = mansignUpForm.getFirst_name();
        String lastname = mansignUpForm.getLast_name();
        String email = mansignUpForm.getEmail();
        String username = mansignUpForm.getUsername();
        String password = mansignUpForm.getPassword();
        
        
        if (firstname.isEmpty()) {
            errors.add("firstname", new ActionMessage("errors.required", "A first name"));
        } else if (firstname.length() > 50) {
            errors.add("firstname", new ActionMessage("errors.maxlength", "First name", "50"));
        } else if (lastname.isEmpty()) {
            errors.add("lastname", new ActionMessage("errors.required", "A last name"));
        } else if (lastname.length() > 50) {
            errors.add("lastname", new ActionMessage("errors.maxlength", "Last name", "50"));
        } else if (email.isEmpty()) {
            errors.add("email", new ActionMessage("errors.required", "An email address"));
        } else if (username.isEmpty()) {
            errors.add("username", new ActionMessage("errors.required", "A username"));
        } else if (password.isEmpty()) {
            errors.add("password", new ActionMessage("errors.required", "A password"));
        } 
        
        this.saveErrors(request, errors);
        
        if (getErrors(request).isEmpty()) {
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
  
    }
    }

