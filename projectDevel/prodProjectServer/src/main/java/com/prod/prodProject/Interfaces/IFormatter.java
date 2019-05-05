/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prod.prodProject.Interfaces;

/**
 *
 * @author shubham
 */
public interface IFormatter<U, T> {

    public U getFormattedData(T data);

}
