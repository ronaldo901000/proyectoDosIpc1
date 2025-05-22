
package com.mycompany.myfarm.backend.cola;

import com.mycompany.myfarm.backend.cola.exception.ColaException;
import com.mycompany.myfarm.backend.granja.Granja;
import com.mycompany.myfarm.backend.planta.Planta;
import com.mycompany.myfarm.backend.suelo.SueloGrama;
import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class ColaCosecha implements Serializable{
    public static final long serialVersionUID = 1290842342;
    private Planta [] plantas;
    private int ultimaPosicion;
    private Granja granja;
    
    public ColaCosecha(Granja granja){
        plantas= new Planta[2];
        ultimaPosicion = -1;
        this.granja=granja;
    }

    // metodo que se encarga de sacar de la cola a los productos de plantas que ya se pudrieron
    public void actualizarCola() {
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloGrama) {
                    SueloGrama suelo = (SueloGrama) granja.getSuelos()[i][j];
                    if (suelo.getPlanta() != null) {
                        if (!suelo.getPlanta().isFueCosechado() && !suelo.getPlanta().isProductoFresco()) {
                            int[] posicion = {i, j};
                            eliminarUnaPlantaDeLaCola(posicion);
                        }

                    }
                }
            }
        }
    }


    public void agregarACola(Planta planta) {
        plantas[ultimaPosicion + 1] = planta;
        ultimaPosicion = ultimaPosicion + 1;
        aumentarArreglo();
    }

    
    public Planta sacarDeCola() throws ColaException {
        if (estaVacia()) {
            throw new ColaException("La cola esta vacia");
        }
        Planta planta = plantas[0];
        for (int i = 1; i <= ultimaPosicion; i++) {
            Planta plantaDetras = plantas[i];
            plantas[i - 1] = plantaDetras;
        }
        ultimaPosicion = ultimaPosicion - 1;
        return planta;
    }

    public void aumentarArreglo(){
    Planta[] nuevoArreglo = new Planta[plantas.length + 1];
        for (int i = 0; i < plantas.length; i++) {
            nuevoArreglo[i]=plantas[i];
        }
        plantas=nuevoArreglo;
    }

    public void eliminarUnaPlantaDeLaCola(int[] posicion) {
        int[] posicionEnLista = new int[2];
        for (int i = 0; i < plantas.length; i++) {
            if (plantas[i] != null) {
                posicionEnLista[0] = plantas[i].getSuelo().getPosicion()[0];
                posicionEnLista[1] = plantas[i].getSuelo().getPosicion()[1];
                if (posicionEnLista[0] == posicion[0] && posicionEnLista[1] == posicion[1]) {
                    if (ultimaPosicion >= 0) {
                        correrArreglo(i);
                    }

                }
            }
        }
    }

    public void correrArreglo(int indice) {
        for (int i = indice; i <= ultimaPosicion; i++) {//
            plantas[i] = plantas[i + 1];
        }
        ultimaPosicion--;
    }

    public boolean estaLlena() {
        return ultimaPosicion == plantas.length - 1;
    }

    public boolean estaVacia() {
        return ultimaPosicion == -1;
    }

    public Planta[] getPlantas() {
        return plantas;
    }

    public void setPlantas(Planta[] plantas) {
        this.plantas = plantas;
    }

    public int getUltimaPosicion() {
        return ultimaPosicion;
    }

    public void setUltimaPosicion(int ultimaPosicion) {
        this.ultimaPosicion = ultimaPosicion;
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }

    



}
