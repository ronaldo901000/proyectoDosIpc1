/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.cola;

import com.mycompany.myfarm.backend.cola.exception.ColaException;

/**
 *
 * @author ronaldo
 */
public class ColaGenerica<T> {
    private T[] elementos;
    private int ultimaPosicion;
    
    public ColaGenerica(int tamaño) {
        elementos = (T[]) new Object[tamaño];
        ultimaPosicion = -1;
    }
    
    
    public void agregarACola(T elemento) throws ColaException {
        if (estaLlena()) {
            throw new ColaException("La cola ya esta llena");
        }
        elementos[ultimaPosicion + 1] = elemento;
        ultimaPosicion = ultimaPosicion + 1;
    }
    
    public T sacarDeCola() throws ColaException {
        if (estaVacia()) {
            throw new ColaException("La cola esta vacia");
        }
        T elemento = elementos[0];
        
        for (int i = 1; i <= ultimaPosicion; i++) {
            T elementoDetraz = elementos[i];
            elementos[i - 1] = elementoDetraz;
        }
        ultimaPosicion = ultimaPosicion - 1;
        return elemento;
    }
    
    public boolean estaLlena() {
        return ultimaPosicion == elementos.length - 1;
    }
    
    public boolean estaVacia() {
        return ultimaPosicion == -1;
    }

    public T[] getElementos() {
        return elementos;
    }

    public void setElementos(T[] elementos) {
        this.elementos = elementos;
    }

    public int getUltimaPosicion() {
        return ultimaPosicion;
    }

    public void setUltimaPosicion(int ultimaPosicion) {
        this.ultimaPosicion = ultimaPosicion;
    }
    
    
}