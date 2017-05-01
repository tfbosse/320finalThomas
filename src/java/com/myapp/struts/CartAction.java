/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

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
 * @author jakeotey
 */
public class CartAction extends org.apache.struts.action.Action {

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
        
        FilmDAO dao = new FilmDAO();
        FilmForm info = (FilmForm) form;
        boolean fiveCheck,sameCheck,emptyBox;
        int error = 0;
        String f, u;
        
        HttpSession ses = request.getSession();
        ses.setAttribute("title", info.getTitle());       
        f = (String) ses.getAttribute("title");      
        
        u = (String) ses.getAttribute("sessID");    
        sameCheck = dao.sameFilmCheck(f);
        fiveCheck = dao.fiveFilmCheck(u);
        emptyBox = dao.textBoxEmptyCheck(f);
        if(sameCheck){
            error = 1;
        }
        if(fiveCheck){
            error = 2;
        }
        System.out.println(emptyBox + "++++++++++++");
        if (emptyBox) {
            error = 4;
        }
        
        if(!fiveCheck && !sameCheck && !emptyBox){
   
        dao.insertIntoCart(f,u);
        return mapping.findForward(SUCCESS);
            
        }
        else{
            if (error == 4) {
                errors.add("title", new ActionMessage("errors.emptyBox")); 
            }
            if(error==2){
            errors.add("title", new ActionMessage("errors.cartfull"));
            }
            if(error==1){
                errors.add("title", new ActionMessage("errors.sameFilm"));
            }
            this.saveErrors(request, errors);
        return mapping.findForward(FAILURE);
        }
    }
}

