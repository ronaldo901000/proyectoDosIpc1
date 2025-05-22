/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.creadores;

import com.mycompany.myfarm.backend.MotorJuego;
import com.mycompany.myfarm.backend.animal.Animal;
import com.mycompany.myfarm.backend.animal.AnimalHervivoro;
import com.mycompany.myfarm.backend.animal.AnimalOmnivoro;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.frontend.FramePrograma;
import com.mycompany.myfarm.frontend.paneles.PanelCreacionDeAnimal;

/**
 *
 * @author ronaldo
 */
public class CreadorDeAnimales {

    private PanelCreacionDeAnimal panel;

    public CreadorDeAnimales() {
        panel = new PanelCreacionDeAnimal(this);
    }

    public void iniciarCreacion() {
        FramePrograma.ponerPanelEnFramePrincipal(panel);
    }

    /**
     *
     * @param productos
     * @return
     */
    public Animal[] crearAnimalesIniciales(Producto[] productos) {
        Animal[] animalesIniciales = new Animal[2];
        //cuero,carne,leche, huevos
        Producto[] productosDeLaVaca = new Producto[3];
        productosDeLaVaca[0] = productos[0];
        productosDeLaVaca[0].setPorcetajeDeLaProduccion(25);
        productosDeLaVaca[1] = productos[1];
        productosDeLaVaca[1].setPorcetajeDeLaProduccion(75);
        productosDeLaVaca[2] = productos[2];
        productosDeLaVaca[2].setPorcetajeDeLaProduccion(100);

        animalesIniciales[0] = new AnimalHervivoro("Vaca", 12, true, 20, 10, 2);
        animalesIniciales[0].agregarUnProducto(productosDeLaVaca[0]);
        animalesIniciales[0].agregarUnProducto(productosDeLaVaca[1]);
        animalesIniciales[0].agregarUnProducto(productosDeLaVaca[2]);
        Producto[] productosDeLaGallina = new Producto[2];
        productosDeLaGallina[0] = productos[1];
        productosDeLaGallina[0].setPorcetajeDeLaProduccion(100);
        productosDeLaGallina[1] = productos[3];
        productosDeLaGallina[1].setPorcetajeDeLaProduccion(100);

        animalesIniciales[1] = new AnimalOmnivoro("Gallina", 6, true, 4, 2, 0.5);
        animalesIniciales[1].agregarUnProducto(productosDeLaGallina[0]);
        animalesIniciales[1].agregarUnProducto(productosDeLaGallina[1]);
        return animalesIniciales;
    }

    public Animal crearAnimal(String nombre, int edadMaxima, int precioDeUnaCria, int precioDeCompra, boolean esDestasable, double espacio, int tipoDeAnimal) {
        Animal nuevoAnimal = null;
        if (tipoDeAnimal == 0) {
            nuevoAnimal = new AnimalOmnivoro(nombre, edadMaxima, esDestasable, precioDeCompra, precioDeUnaCria, espacio);
            return nuevoAnimal;
        }
        nuevoAnimal = new AnimalHervivoro(nombre, edadMaxima, esDestasable, precioDeCompra, precioDeUnaCria, espacio);
        return nuevoAnimal;
    }

    // metodo que se encarga de clonar animales para que cada una implemente unhilo diferente
    public Animal clonarAnimal(Animal animal) {
        if (animal instanceof AnimalHervivoro) {
            Animal animalClon = new AnimalHervivoro(animal.getNombre(), animal.getEdadMaxima(),
                    animal.isEsDestasable(), animal.getPrecioDeCompra(), animal.getPrecioDeVentaCria(), animal.getEspacioEnParcela());
            return animalClon;
        }
        if (animal instanceof AnimalOmnivoro) {
            Animal animalClon = new AnimalOmnivoro(animal.getNombre(), animal.getEdadMaxima(),
                    animal.isEsDestasable(), animal.getPrecioDeCompra(), animal.getPrecioDeVentaCria(), animal.getEspacioEnParcela());
            return animalClon;
        }
        return null;
    }

    public void buscarYAgregarProductoAAnimal(String nombreDelProducto, int porcentajeDeProduccion) {
        int indiceDelProductoSeleccionado = CreadorDeProductos.buscarIndiceDeUnProducto(nombreDelProducto);
        //Producto producto = MotorJuego.devolverProductosDisponibles()[indiceDelProductoSeleccionado];
        Producto producto = MotorJuego.getAlmacenador().getProductosDisponibles().obtenerContenido(indiceDelProductoSeleccionado);
        producto.setPorcetajeDeLaProduccion(porcentajeDeProduccion);
        int ultimoAnimalCreado = MotorJuego.getAlmacenador().getMercadoGeneral().getAnimalesDisponibles().getTamaño() - 1;

        MotorJuego.getAlmacenador().getMercadoGeneral().getAnimalesDisponibles().obtenerContenido(ultimoAnimalCreado).agregarUnProducto(producto);
    }

    public boolean verificarSiElAnimalEsDestasable() {
        int ultimoAnimalCreado = MotorJuego.getAlmacenador().getMercadoGeneral().getAnimalesDisponibles().getTamaño() - 1;
        return MotorJuego.getAlmacenador().getMercadoGeneral().getAnimalesDisponibles().obtenerContenido(ultimoAnimalCreado).isEsDestasable();
    }

    public Producto darleElProductoSeleccionadoAlPanel(String nombreDelProducto) {
        int indiceDelProductoSeleccionado = CreadorDeProductos.buscarIndiceDeUnProducto(nombreDelProducto);
        Producto producto = MotorJuego.getAlmacenador().getProductosDisponibles().obtenerContenido(indiceDelProductoSeleccionado);
        return producto;
    }
}
