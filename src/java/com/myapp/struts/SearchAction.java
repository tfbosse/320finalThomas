/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

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
 * @author jakeotey
 */
public class SearchAction extends org.apache.struts.action.Action {

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
        SearchForm search = (SearchForm) form;
        
        int error = 0;
        
        if(getErrors(request).isEmpty()){
            error = 1;
        }
        if (getErrors(request).isEmpty()) {
            FilmDAO film = new FilmDAO();

            ArrayList<FilmForm> films = new ArrayList<FilmForm>();
            films = film.getSearch(search);
            request.setAttribute("listfilms", films);
            return mapping.findForward(SUCCESS);
        } 
        else {
            if(error == 1){
                errors.add("title", new ActionMessage("errors.emptyBox"));
            }
            this.saveErrors(request, errors);
            return mapping.findForward(FAILURE);
        }
    }
}
