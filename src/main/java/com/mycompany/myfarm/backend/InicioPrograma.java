/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend;

import com.mycompany.myfarm.frontend.FramePrograma;
import com.mycompany.myfarm.frontend.paneles.PanelMenuPrincipal;

/**
 *
 * @author ronaldo
 */
public class InicioPrograma {
    
    private PanelMenuPrincipal panelMenuPrincipal;
    private MotorJuego motor;
    private FramePrograma frame;

    public InicioPrograma() {
        frame = new FramePrograma();
        motor = new MotorJuego();
        panelMenuPrincipal = new PanelMenuPrincipal();
    }

    
    public void iniciarPrograma() {
        cargarReporte();
        FramePrograma.ponerPanelEnFramePrincipal(panelMenuPrincipal);
        frame.setVisible(true);
    }

    // carga el reporte guardado en el archivo binario si es que existe
    public void cargarReporte() {
        Archivador archivador = new Archivador();
        Reporte reporte = archivador.devolverReporte();

        if (reporte != null) {
            MotorJuego.getAlmacenador().setReporte(reporte);
        }

    }

}
