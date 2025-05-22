/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.creadores;

import com.mycompany.myfarm.backend.MotorJuego;
import com.mycompany.myfarm.backend.planta.Planta;
import com.mycompany.myfarm.backend.planta.PlantaFruto;
import com.mycompany.myfarm.backend.planta.PlantaGrano;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.backend.semilla.Semilla;
import com.mycompany.myfarm.frontend.FramePrograma;
import com.mycompany.myfarm.frontend.paneles.PanelCreacionDePlantas;

/**
 *
 * @author ronaldo
 */
public class CreadorDePlantas {
    private PanelCreacionDePlantas panel;

    public CreadorDePlantas() {
       
    }

    public void iniciarCreacion() {
        panel = new PanelCreacionDePlantas(this);
        FramePrograma.ponerPanelEnFramePrincipal(panel);
    }

    public Planta crearUnaPlanta(String nombre, int cantidadDeSemillas, int tipo) {
        Planta nuevaPlanta = null;
        switch (tipo) {
            case 1:
                nuevaPlanta = new PlantaGrano(nombre, cantidadDeSemillas);
                break;
            case 2:
                nuevaPlanta = new PlantaFruto(nombre, cantidadDeSemillas);
                break;
            default:
        }

        return nuevaPlanta;
    }
    
    public Planta crearPlantaParSuelo(Semilla semilla){
        Planta planta=null;
        if(semilla.getPlanta() instanceof PlantaGrano){
            planta =copiarPlantaParaSuelo(semilla.getPlanta().getNombre(),semilla.getPlanta().getCantidadDeSemillas(),semilla.getPlanta().getProducto() ,1);
        }
        else{
            planta=copiarPlantaParaSuelo(semilla.getPlanta().getNombre(),semilla.getPlanta().getCantidadDeSemillas(),semilla.getPlanta().getProducto(), 2);
        }
        return planta;
    }
    
    
    public void buscarYAgregarProductoAPlanta(String nombreDelProducto) {
        int indiceDelProductoSeleccionado = CreadorDeProductos.buscarIndiceDeUnProducto(nombreDelProducto);
        //Producto producto = MotorJuego.devolverProductosDisponibles()[indiceDelProductoSeleccionado];
        Producto producto = MotorJuego.getAlmacenador().getProductosDisponibles().obtenerContenido(indiceDelProductoSeleccionado);
        int ultimaPlantaCreada = MotorJuego.getAlmacenador().getMercadoGeneral().getSemillasDisponibles().getTamaño()-1;
        MotorJuego.getAlmacenador().getMercadoGeneral().getSemillasDisponibles().obtenerContenido(ultimaPlantaCreada).getPlanta().setProducto(producto);
    }
    
    public Planta copiarPlantaParaSuelo(String nombre, int cantidadDeSemillas,Producto producto, int tipo) {
        Planta nuevaPlanta = null;
        switch (tipo) {
            case 1:
                nuevaPlanta = new PlantaGrano(nombre, cantidadDeSemillas,producto);
                break;
            case 2:
                nuevaPlanta = new PlantaFruto(nombre, cantidadDeSemillas,producto);
                break;
            default:
        }

        return nuevaPlanta;
    }

    public PanelCreacionDePlantas getPanel() {
        return panel;
    }

    public void setPanel(PanelCreacionDePlantas panel) {
        this.panel = panel;
    }
    

}
