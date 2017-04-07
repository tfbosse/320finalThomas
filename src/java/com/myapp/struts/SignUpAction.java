/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//figuring this shit out
package com.myapp.struts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        if (firstname.isEmpty()) {
            errors.add("firstname", new ActionMessage("errors.required", "A first name"));
        } else if (firstname.length() > 50) {
            errors.add("firstname", new ActionMessage("errors.maxlength", "First name", "50"));
        } else if (lastname.isEmpty()) {
            errors.add("lastname", new ActionMessage("errors.required", "A last name"));
        } else if (lastname.length() > 50) {
            errors.add("lastname", new ActionMessage("errors.maxlength", "Last name", "50"));
        } else if (address.isEmpty()) {
            errors.add("address", new ActionMessage("errors.required", "An address"));
        } else if (address.length() > 150) {
            errors.add("address", new ActionMessage("errors.maxlength", "Address", "150"));
        } else if (address2.length() > 150) { 
            errors.add("address2", new ActionMessage("errors.maxlength", "Address 2", "150"));
        } else if (city.isEmpty()) {
            errors.add("city", new ActionMessage("errors.required", "A city"));
        } else if (city.length() > 100 || city.matches(".*\\d+.*")) {
            errors.add("city", new ActionMessage("errors.invalid", "City"));
        } else if (state.isEmpty()) {
            errors.add("state", new ActionMessage("errors.required", "A state"));
        } else if (zip.isEmpty()) {
            errors.add("zip", new ActionMessage("errors.required", "A zip code"));
        } else if (email.isEmpty()) {
            errors.add("email", new ActionMessage("errors.required", "An email address"));
        } else if (username.isEmpty()) {
            errors.add("username", new ActionMessage("errors.required", "A username"));
        } else if (password.isEmpty()) {
            errors.add("password", new ActionMessage("errors.required", "A password"));
        } else if (creditcard.isEmpty()) {
            errors.add("creditcard", new ActionMessage("errors.required", "A credit card number"));
        } else if (expdate.isEmpty()) {
            errors.add("expdate", new ActionMessage("errors.required", "An expiration date"));
        } else if (code.isEmpty()) {
            errors.add("code", new ActionMessage("errors.required", "A security code"));
        }
        
        this.saveErrors(request, errors);
        
        if (getErrors(request).isEmpty()) {
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
