/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.listaEnlazada;

import java.io.Serializable;


/**
 *
 * @author ronaldo
 */
public class NodoGenerico<T> implements Serializable{
    public static final long serialVersionUID = 576545353;
    private T contenido;
    private NodoGenerico siguiente;

    public NodoGenerico(T elemento) {
        this.contenido = elemento;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T elemento) {
        this.contenido = elemento;
    }

    public NodoGenerico getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGenerico elementoSiguiente) {
        this.siguiente = elementoSiguiente;
    }
    
    

}
