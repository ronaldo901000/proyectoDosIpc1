/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.mercado;

import com.mycompany.myfarm.backend.animal.Animal;
import com.mycompany.myfarm.backend.creadores.CreadorDeAnimales;
import com.mycompany.myfarm.backend.fertilizante.Fertilizante;
import com.mycompany.myfarm.backend.granja.Parcela;
import com.mycompany.myfarm.backend.granjero.Granjero;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.semilla.Semilla;
import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class Mercado implements Serializable {

    public static final long serialVersionUID = 77756568;
    private ListaEnlazadaGenerica<Semilla> semillasDisponibles;
    private ListaEnlazadaGenerica<Animal> animalesDisponibles;
    private Fertilizante[] fertilizantes;
    private Alimento[] alimentosParaAnimales;

    public Mercado() {
        semillasDisponibles = new ListaEnlazadaGenerica<>();
    }

    public void agregarTodosLosAnimalesIniciles(Animal[] animal) {
        animalesDisponibles = new ListaEnlazadaGenerica();
        for (int i = 0; i < animal.length; i++) {
            animalesDisponibles.agregarElemento(animal[i]);
        }
    }

    //metodo que se encarga de darle los animales comprados al granjero
    /**
     * @param parcela
     * @param indiceDelAnimal
     * @param cantidadDeAnimales
     */
    public void darleAnimalesAGranjero(Parcela parcela, int indiceDelAnimal, int cantidadDeAnimales) {
        Animal animal = animalesDisponibles.obtenerContenido(indiceDelAnimal);
        CreadorDeAnimales creador = new CreadorDeAnimales();
        parcela.actualizarIconoParcela(5);
        for (int i = 0; i < cantidadDeAnimales; i++) {
            Animal animalClonado = creador.clonarAnimal(animal);
            animalClonado.setParcela(parcela);
            animalClonado.setProductos(animal.getProductos());
            parcela.getAnimales().agregarElemento(animalClonado);
            Thread hilo = new Thread(parcela.getAnimales().obtenerContenido(i));
            hilo.start();
        }
    }

    public void devolverAlimentos(ListaEnlazadaGenerica<Alimento> alimentos, int cantidadDeAlimentos, Alimento alimento) {
        for (int i = 0; i < cantidadDeAlimentos; i++) {
            alimentos.agregarElemento(alimento);
        }
    }

    public void agregarTodasLasSemillasIniciales(Semilla[] semillas) {
        semillasDisponibles = new ListaEnlazadaGenerica();
        for (int i = 0; i < semillas.length; i++) {
            semillasDisponibles.agregarElemento(semillas[i]);
        }
    }

    public String obtenerListaDeProductosDeUnAnimal(Animal animal) {
        String lista = "";
        for (int i = 0; i < animal.getProductos().getTamaño(); i++) {
            lista = lista + ", " + animal.getProductos().obtenerContenido(i).getNombre();
        }
        return lista;
    }

    /**
     *
     * @param granjero
     * @param semilla
     * @param cantidadDeSemillas
     */
    public void darleLasSemillasAlGranjero(Granjero granjero, Semilla semilla, int cantidadDeSemillas) {
        for (int i = 0; i < cantidadDeSemillas; i++) {
            granjero.getSemillas().agregarElemento(semilla);
        }
    }

    public Fertilizante[] getFertilizantes() {
        return fertilizantes;
    }

    public void setFertilizantes(Fertilizante[] fertilizantes) {
        this.fertilizantes = fertilizantes;
    }

    public Alimento[] getAlimentosParaAnimales() {
        return alimentosParaAnimales;
    }

    public void setAlimentosParaAnimales(Alimento[] alimentosParaAnimales) {
        this.alimentosParaAnimales = alimentosParaAnimales;
    }

    public ListaEnlazadaGenerica<Semilla> getSemillasDisponibles() {
        return semillasDisponibles;
    }

    public ListaEnlazadaGenerica<Animal> getAnimalesDisponibles() {
        return animalesDisponibles;
    }

    public void setAnimalesDisponibles(ListaEnlazadaGenerica<Animal> animalesDisponibles) {
        this.animalesDisponibles = animalesDisponibles;
    }

    public void setSemillasDisponibles(ListaEnlazadaGenerica<Semilla> semillasDisponibles) {
        this.semillasDisponibles = semillasDisponibles;
    }

}
