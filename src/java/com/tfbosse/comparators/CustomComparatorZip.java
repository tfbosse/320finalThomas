/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfbosse.comparators;

import com.myapp.struts.SignUpForm;
import java.util.Comparator;

/**
 *
 * @author Thomas
 */
public class CustomComparatorZip implements Comparator<SignUpForm> {
    
    @Override
    public int compare(SignUpForm o1, SignUpForm o2) {
        return o1.getZip().compareTo(o2.getZip());
    }
}
