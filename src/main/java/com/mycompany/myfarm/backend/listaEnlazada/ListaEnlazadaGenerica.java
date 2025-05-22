/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.listaEnlazada;

import com.mycompany.myfarm.backend.cola.exception.ListaEnlazadaException;
import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class ListaEnlazadaGenerica<T> implements Serializable {

    public static final long serialVersionUID = 789732745;
    private NodoGenerico<T> inicio;
    private NodoGenerico<T> fin;
    private int tamaño;

    public ListaEnlazadaGenerica() {

    }

    public void agregarElemento(T contenido) {
        NodoGenerico<T> nuevo = new NodoGenerico(contenido);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        tamaño++;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public T obtenerContenido(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        NodoGenerico<T> nodoBuscado = obtenerNodo(indice);
        return nodoBuscado.getContenido();
    }

    public NodoGenerico<T> obtenerNodo(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        NodoGenerico<T> actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public void eliminar(int indice) throws ListaEnlazadaException {
        if (indice < 0 || indice >= tamaño) {
            throw new ListaEnlazadaException("El inidice esta fuera de rango");
        }
        if (indice == 0) {
            inicio = inicio.getSiguiente();

            if (inicio == null) {
                fin = null;
            }
        } else {
            NodoGenerico<T> anterior = obtenerNodo(indice - 1);
            NodoGenerico<T> nodoAEliminar = anterior.getSiguiente();
            anterior.setSiguiente(nodoAEliminar.getSiguiente());

            if (nodoAEliminar == fin) {
                fin = anterior;
            }
        }
        tamaño--;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public NodoGenerico<T> getInicio() {
        return inicio;
    }

    public void setInicio(NodoGenerico<T> inicio) {
        this.inicio = inicio;
    }

}
