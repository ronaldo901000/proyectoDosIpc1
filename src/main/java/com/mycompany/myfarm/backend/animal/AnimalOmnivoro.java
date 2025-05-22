/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.animal;


/**
 *
 * @author ronaldo
 */
public class AnimalOmnivoro extends Animal {

    public AnimalOmnivoro(String nombre, int edadMaxima, boolean esDestasable, int precioDeCompra, int precioDeVentaCria, double espacioEnParcela) {
        super(nombre, edadMaxima, esDestasable, precioDeCompra, precioDeVentaCria, espacioEnParcela);
    }
    @Override
    public void run() {
        int contador = 0;
        System.out.println("inicio hilo");
        while (estaVivo) {
            aumentarEdad();  
            pedirComida();
            generarProductosSinDestace();
        }
    }

    
    
}
