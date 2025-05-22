/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend;

import com.mycompany.myfarm.backend.animal.Animal;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.mercado.Mercado;
import com.mycompany.myfarm.backend.partida.Partida;
import com.mycompany.myfarm.backend.planta.Planta;
import com.mycompany.myfarm.backend.producto.Producto;
import java.io.Serializable;


/**
 *
 * @author ronaldo
 */
public class Almacenador implements Serializable {
    public static final long serialVersionUID = 74832834;
    private ListaEnlazadaGenerica<Producto> productosDisponibles;
    private Mercado mercadoGeneral;
    private ListaEnlazadaGenerica<Partida> partidasGuardadas;
    private Reporte reporte;

    public Almacenador() {
        productosDisponibles = new ListaEnlazadaGenerica<>();
        mercadoGeneral = new Mercado();
        partidasGuardadas = new ListaEnlazadaGenerica<>();
        reporte = new Reporte();
    }

    public boolean nombreDePartidaExistente(String nombre) {
        for (int i = 0; i < partidasGuardadas.getTamaño(); i++) {
            if (nombre.equals(partidasGuardadas.obtenerContenido(i).getNombre())) {
                return true;
            }
        }
        return false;
    }

    public ResumenPartida devolverResumenPartida(Partida partida) {
        ResumenPartida resumen = new ResumenPartida(partida.getGranjero().getNombre(), partida.getDuracion(),
                partida.getGranja().getOroGenerado(),
                partida.getGranja().getAlimentosGenerados(), partida.getGranjero().getAlimentosConsumidos(),
                partida.getGranja().getCeldasCompradas());
        return resumen;
    }

    public void sobreEscribirPartida(Partida partida) {
        for (int i = 0; i < partidasGuardadas.getTamaño(); i++) {
            if (partida.getNombre().equals(partidasGuardadas.obtenerContenido(i).getNombre())) {
                partidasGuardadas.obtenerNodo(i).setContenido(partida);
            }
        }
    }

    public ListaEnlazadaGenerica<ResumenAnimal> devolverReporteAnimales() {
        ListaEnlazadaGenerica<ResumenAnimal> resumenes = new ListaEnlazadaGenerica<>();
        for (int i = 0; i < mercadoGeneral.getAnimalesDisponibles().getTamaño(); i++) {
            Animal animal = mercadoGeneral.getAnimalesDisponibles().obtenerContenido(i);
            ResumenAnimal resumen = devolverUnResumenAnimal(animal);
            resumenes.agregarElemento(resumen);
        }
        return resumenes;
    }

    private ResumenAnimal devolverUnResumenAnimal(Animal animal) {
        ResumenAnimal resumen = new ResumenAnimal(animal.getNombre(), animal.getCantidadDeCrias(), animal.getCantidadDeVecesDestazado());
        return resumen;
    }

    public ListaEnlazadaGenerica<ResumenPlanta> devolverReportePlanta() {
        ListaEnlazadaGenerica<ResumenPlanta> resumenes = new ListaEnlazadaGenerica<>();
        for (int i = 0; i < mercadoGeneral.getSemillasDisponibles().getTamaño(); i++) {
            Planta planta = mercadoGeneral.getSemillasDisponibles().obtenerContenido(i).getPlanta();
            ResumenPlanta resumen = devolverUnResumenPlanta(planta);
            resumenes.agregarElemento(resumen);
        }
        return resumenes;
    }

    private ResumenPlanta devolverUnResumenPlanta(Planta planta) {
        ResumenPlanta resumen = new ResumenPlanta(planta.getNombre(), planta.getCantidadDeSemillasVendidas(), planta.getCeldasSembradas());
        return resumen;
    }

    public ListaEnlazadaGenerica<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(ListaEnlazadaGenerica<Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }

    public Mercado getMercadoGeneral() {
        return mercadoGeneral;
    }

    public void setMercadoGeneral(Mercado mercadoGeneral) {
        this.mercadoGeneral = mercadoGeneral;
    }

    public ListaEnlazadaGenerica<Partida> getPartidasGuardadas() {
        return partidasGuardadas;
    }

    public void setPartidasGuardadas(ListaEnlazadaGenerica<Partida> partidasGuardadas) {
        this.partidasGuardadas = partidasGuardadas;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

}
