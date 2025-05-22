
package com.mycompany.myfarm.backend;

import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.frontend.FramePrograma;
import com.mycompany.myfarm.frontend.paneles.PanelReportes;
import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class Reporte implements Serializable{
    
    public static final long serialVersionUID = 3404731;
    private ListaEnlazadaGenerica<ResumenPartida> resumenes;
    private ListaEnlazadaGenerica<ResumenAnimal> resumenesAnimales;
    private ListaEnlazadaGenerica<ResumenPlanta> resumenesPlantas;
    
    public Reporte() {
        resumenes=new ListaEnlazadaGenerica<>();
        resumenesAnimales=new ListaEnlazadaGenerica<>();
        resumenesPlantas=new ListaEnlazadaGenerica<>();
    }
    
    public boolean existeElResumen(String nombre) {
        for (int i = 0; i < resumenes.getTamaño(); i++) {
            if (nombre.equals(resumenes.obtenerContenido(i).getNombrePartida())) {
                return true;
            }
        }
        return false;
    }
    
    public void guardarArchivo(int opcion, String nombreArchivo){
        Archivador archivador= new Archivador();
        if(opcion==0){    
            archivador.escribirTablaReportesPartidasHtml(resumenes, nombreArchivo);
        }
        else if(opcion==1){
            archivador.escribirTablaReportesAnimalesHtml(resumenesAnimales, nombreArchivo);
        }
        else if(opcion==2){
            archivador.escribirTablaRepotePlantasHtml(resumenesPlantas, nombreArchivo);
        }
    }
    
    public int indiceDondeSeAlmacenaResumen(String nombre) {
        for (int i = 0; i < resumenes.getTamaño(); i++) {
            if (nombre.equals(resumenes.obtenerContenido(i).getNombrePartida())) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarRepotes() {
        FramePrograma.ponerPanelEnFramePrincipal(new PanelReportes(resumenes,resumenesAnimales,resumenesPlantas));
    }

    public ListaEnlazadaGenerica<ResumenPartida> getResumenes() {
        return resumenes;
    }

    public void setResumenes(ListaEnlazadaGenerica<ResumenPartida> resumenes) {
        this.resumenes = resumenes;
    }

    public ListaEnlazadaGenerica<ResumenAnimal> getResumenesAnimales() {
        return resumenesAnimales;
    }

    public void setResumenesAnimales(ListaEnlazadaGenerica<ResumenAnimal> resumenesAnimales) {
        this.resumenesAnimales = resumenesAnimales;
    }

    public ListaEnlazadaGenerica<ResumenPlanta> getResumenesPlantas() {
        return resumenesPlantas;
    }

    public void setResumenesPlantas(ListaEnlazadaGenerica<ResumenPlanta> resumenesPlantas) {
        this.resumenesPlantas = resumenesPlantas;
    }
    
    
}
