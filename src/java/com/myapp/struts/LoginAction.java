/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Thomas
 */
public class LoginAction extends org.apache.struts.action.Action {

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
        
        managerDAO mdao = new managerDAO();
        customerDAO cdao = new customerDAO();
        
        LoginForm signUpForm = (LoginForm) form;
        
        if (cdao.searchCustomer(signUpForm.getUsername(), signUpForm.getPassword()) 
                || mdao.searchManager(signUpForm.getUsername(), signUpForm.getPassword())) {
            HttpSession ses = request.getSession();
            ses.setAttribute("sessID", signUpForm.getUsername());
            signUpForm.setUsername("");
            signUpForm.setPassword("");
            return mapping.findForward(SUCCESS);
        } else {
            errors.add("username", new ActionMessage("errors.invalid", "Username or password"));
            this.saveErrors(request, errors);
            return mapping.findForward(FAILURE);
        }
    }
}
