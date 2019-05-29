package com.prod.prodServer.Interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 */
public interface IFormatter<U, V> {

    public U formatData(V data);
}
