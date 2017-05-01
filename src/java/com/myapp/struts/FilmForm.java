/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author jakeotey
 */
public class FilmForm extends org.apache.struts.action.ActionForm {
    
    private String title, actor, genre, releaseYear, rating, description, length, shownTitle;
    private String field, instock;
    private boolean whatever = true;


    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public boolean isWhatever() {
        return whatever;
    }

    public void setWhatever(boolean whatever) {
        this.whatever = whatever;
    }
    
    
    
    public FilmForm(){
        super();
    }
    
    public FilmForm(String title){
        this.title = title;
    }
    

    public FilmForm(String title,String rating,String desc){
        this.title = title;
        this.rating = rating;
        this.description = desc;
    }


    public FilmForm(String title, String actor, String genre, String releaseYear, String rating, String description, 
            String length, String instock, boolean whatever) {
        this.title = title;
        this.actor = actor;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.description = description;
        this.length = length;
        this.instock = instock;
        this.whatever = true;

    }
   
    public FilmForm(String title, String actor, String genre, String releaseYear, String rating, String description, String length){
        this.title = title;
        this.actor= actor;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.description = description;
        this.length = length;
        
    }
    
    public FilmForm(String title, String actor, String genre, String releaseYear, String rating, String description, String length, String shownTitle){
        this.title = title;
        this.actor= actor;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.description = description;
        this.length = length;
        this.shownTitle = shownTitle;
        
    }
    
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getShownTitle() {
        return shownTitle;
    }

    public void setShownTitle(String shownTitle) {
        this.shownTitle = shownTitle;
    }
    

    }

