/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.planta;

import com.mycompany.myfarm.backend.producto.Producto;

/**
 *
 * @author ronaldo
 */
public class PlantaGrano extends Planta {
    private static final int TIEMPO_MINIMO_COSECHAR = 40000;
    private static final int TIEMPO_MAXIMO_COSECHA = 80000;
    private static final int TIEMPO_MAXIMO_DESCOMPOSICION = 90000;
    private static final int TIEMPO_MINIMO_DESCOMPOSICION = 50000;

    public PlantaGrano(String nombre, int cantidadDeSemillas) {
        super(nombre, cantidadDeSemillas);
        tiempoParaCosechar = generarValorAleatorio(TIEMPO_MAXIMO_COSECHA, TIEMPO_MINIMO_COSECHAR);
       
        tiempoParaPudrirse = generarValorAleatorio(TIEMPO_MAXIMO_DESCOMPOSICION, TIEMPO_MINIMO_DESCOMPOSICION);
    }
    public PlantaGrano(String nombre, int cantidadDeSemillas,Producto producto) {
        super(nombre, cantidadDeSemillas);
        tiempoParaCosechar=2000;
        //tiempoParaCosechar = generarValorAleatorio(TIEMPO_MAXIMO_COSECHA, TIEMPO_MINIMO_COSECHAR);
        //tiempoParaPudrirse = generarValorAleatorio(TIEMPO_MAXIMO_DESCOMPOSICION, TIEMPO_MINIMO_DESCOMPOSICION);
        tiempoParaPudrirse=2000;
        this.producto=producto;
    }

    @Override
    public void run() {
        crecimientoDeLaPlanta();
        iniciarDescomposicion();
    }

}
