/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Thomas
 */
public class SignUpAction extends org.apache.struts.action.Action {

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
        
        SignUpForm signUpForm = (SignUpForm) form;
        String firstname = signUpForm.getFirstname();
        String lastname = signUpForm.getLastname();
        String address = signUpForm.getAddress();
        String address2 = signUpForm.getAddress2();
        String city = signUpForm.getCity();
        String state = signUpForm.getState();
        String zip = signUpForm.getZip();
        String email = signUpForm.getEmail();
        String username = signUpForm.getUsername();
        String password = signUpForm.getPassword();
        String creditcard = signUpForm.getCreditcard();
        String expdate = signUpForm.getExpdate();
        String code = signUpForm.getCode();
        String cardname = signUpForm.getCardname();
        
        if (firstname.isEmpty() || firstname.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (lastname.isEmpty() || lastname.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (address.isEmpty() || address.length() > 150) {
            return mapping.findForward(FAILURE);
        } else if (address2.length() > 150) {
            return mapping.findForward(FAILURE);
        }else if (city.isEmpty() || city.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (state.isEmpty() || state.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (zip.isEmpty() || zip.length() > 5) {
            return mapping.findForward(FAILURE);
        } else if (email.isEmpty() || email.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (username.isEmpty() || username.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (password.isEmpty() || password.length() > 50) {
            return mapping.findForward(FAILURE);
        } else if (creditcard.isEmpty() || creditcard.length() > 16) {
            return mapping.findForward(FAILURE);
        } else if (expdate.isEmpty() || expdate.length() > 7) {
            return mapping.findForward(FAILURE);
        } else if (code.isEmpty() || code.length() > 3) {
            return mapping.findForward(FAILURE);
        } else if (cardname.length() > 100) {
            return mapping.findForward(FAILURE);
        }
        
        //
        
        return mapping.findForward(SUCCESS);
    }
}
