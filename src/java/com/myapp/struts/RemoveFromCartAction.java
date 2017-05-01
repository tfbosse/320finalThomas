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
public class RemoveFromCartAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success",FAILURE="failure";

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
        boolean emptyBox,inCartCheck;
        int error = 0;
        String f, u;
        
        HttpSession ses = request.getSession();
        ses.setAttribute("title", info.getTitle());       
        f = (String) ses.getAttribute("title");      
        
        u = (String) ses.getAttribute("sessID");
        
        emptyBox = dao.textBoxEmptyCheck(f);
        inCartCheck = dao.isFilmInCart(f, u);
        if(emptyBox){
            error = 4;
        }
        if(inCartCheck){
            error = 3;
        }
        
        if(!emptyBox && !inCartCheck){
        dao.removeFromCart(f, u);
        return mapping.findForward(SUCCESS);
        }
        else{
            if(error == 4){
            errors.add("title", new ActionMessage("errors.emptyBox"));
            }
            if(error == 3){
               errors.add("title", new ActionMessage("errors.filmNotInCart")); 
            }
            this.saveErrors(request, errors);
            return mapping.findForward(FAILURE);
        }
    }
}
