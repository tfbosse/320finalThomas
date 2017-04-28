/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfbosse.comparators;

import com.myapp.struts.FilmForm;
import java.util.Comparator;

/**
 *
 * @author Thomas
 */
public class FilmDescComp implements Comparator<FilmForm> {
    
    @Override
    public int compare(FilmForm o1, FilmForm o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
