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
 * @author Thomas
 */
public class AddFilm extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    
    public boolean yearVal(String year) {
        if (year.length() != 4) {
            return false;
        }
        if (year.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean lengthVal(String length) {
        if (length.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean actorVal(String acts) {
        String[] actors = acts.split(", ");
        for (String a : actors) {
            String[] full = a.split(" ");
            if (full.length <= 1) {
                return false;
            }
            for (String f : full) {
                System.out.println(f);
                if (!f.matches("[a-zA-Z]+")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean stockVal(String stock) {
        int st = -1;
        try {
            st = Integer.parseInt(stock);
        } catch (Exception e) {
            return false;
        }
        if (st < 0 || st > 10) {
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
        
        FilmForm ff = (FilmForm) form;
        ActionErrors errors = new ActionErrors();
        
        String title = ff.getTitle();
        String desc = ff.getDescription();
        String releaseyear = ff.getReleaseYear();
        String length = ff.getLength();
        String actor = ff.getActor();
        String rating = "";
        String genre = "";
        String instock = ff.getInstock();
        
        switch (ff.getRating()) {
            case "default":
                rating = "default";
                break;
            case "g":
                rating = "G";
                break;
            case "pg":
                rating = "PG";
                break;
            case "pg13":
                rating = "PG-13";
                break;
            case "r":
                rating = "R";
                break;
            case "nc17":
                rating = "NC-17";
        }
        
        switch(ff.getGenre()) {
            case "default":
                genre = "default";
                break;
            case "action":
                genre = "Action";
                break;
            case "anime":
                genre = "Animation";
                break;
            case "children":
                genre = "Children";
                break;
            case "classics":
                genre = "Classics";
                break;
            case "comedy":
                genre = "Comedy";
                break;
            case "doc":
                genre = "Documentary";
                break;
            case "drama":
                genre = "Drama";
                break;
            case "family":
                genre = "Family";
                break;
            case "foreign":
                genre = "Foreign";
                break;
            case "games":
                genre = "Games";
                break;
            case "horror":
                genre = "Horror";
                break;
            case "music":
                genre = "Music";
                break;
            case "new":
                genre = "New";
                break;
            case "scifi":
                genre = "Sci-Fi";
                break;
            case "travel":
                genre = "Travel";
                break;
        }
        
        if (title.isEmpty()) {
            errors.add("title", new ActionMessage("errors.required", "A title"));
        } else if (rating == "default") {
            errors.add("rating", new ActionMessage("errors.required", "A rating"));
        } else if (desc.isEmpty()) {
            errors.add("description", new ActionMessage("errors.required", "A description"));
        } else if (releaseyear.isEmpty()) {
            errors.add("releaseYear", new ActionMessage("errors.required", "A release year"));
        } else if (length.isEmpty()) {
            errors.add("length", new ActionMessage("errors.required", "Length"));
        } else if (actor.isEmpty()) {
            errors.add("actor", new ActionMessage("errors.required", "At least one actor"));
        } else if (genre == "default") {
            errors.add("genre", new ActionMessage("errors.required", "A genre"));
        } else if (yearVal(releaseyear) == false) {
            errors.add("releaseYear", new ActionMessage("errors.invalid", "Release year"));
        } else if (lengthVal(length) == false) {
            errors.add("length", new ActionMessage("errors.invalid", "Length"));
        } else if (actorVal(actor) == false) {
            errors.add("actor", new ActionMessage("errors.invalid", "List of actors"));
        } else if (instock.isEmpty()) {
            errors.add("instock", new ActionMessage("errors.required", "Number in stock"));
        } else if (stockVal(instock) == false) {
            errors.add("instock", new ActionMessage("errors.invalid", "Number in stock"));
        }
        
        this.saveErrors(request, errors);
        
        if (getErrors(request).isEmpty()) {
            
            InventoryDAO idao = new InventoryDAO();
            idao.insertFilm(title, rating, desc, releaseyear, length, actor, genre, instock);
            
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(FAILURE);
        }
    }
}
