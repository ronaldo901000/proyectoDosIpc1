package com.mycompany.myfarm.backend.suelo;

import com.mycompany.myfarm.backend.granja.Granja;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.frontend.paneles.Partida.Granja.PanelSueloAgua;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public class SueloAgua extends Suelo implements Runnable {
    private static final int UN_SEGUNDO=1000; 
    private static final int PRECIO_DE_BARCO = 80;
    private static final int PRECIO_DE_PESCADO = 5;
    private static final int MAXIMO_DE_PECES = 10;
    private boolean hayBarco;
    private int cantidadDePeces;
    private PanelSueloAgua panel;
    private boolean detenerse;
    
    public SueloAgua(int[] posicion, Granja granja) {
        super(posicion, granja);
        cantidadDePeces = generarUnValorAleatorio(MAXIMO_DE_PECES);
        panel=new PanelSueloAgua(this);
        hayBarco = false;
    }

    @Override
    public void run() {
        if (hayBarco) {
            realizarEjecucionDePesca();
        } else {
            generarNuevosPeces();
        }

    }

    // metodo que se encarga de la accion que se realiza al pescar
    public void realizarEjecucionDePesca() {
        while (hayPeces()) {
            if (detenerse) {
                try {
                    Thread.sleep(generarUnValorAleatorio(UN_SEGUNDO));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(generarUnValorAleatorio(UN_SEGUNDO * 5));
                    granja.getGranjero().guardarProductoABodega(generarUnPez());
                    granja.agregarAlimentoGenerado();
                    cantidadDePeces--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        panel.informarQueYaNoHayPeces();
    }

    // metodo que se encarga de ir llenando el agua con peces nuevos
    private void generarNuevosPeces() {
        int nuevaCantidadDePeces = generarUnValorAleatorio(MAXIMO_DE_PECES);
        while (cantidadDePeces <= nuevaCantidadDePeces && !hayBarco) {
            if (detenerse) {
                try {
                    Thread.sleep(generarUnValorAleatorio(UN_SEGUNDO));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    cantidadDePeces++;
                    Thread.sleep(generarUnValorAleatorio(UN_SEGUNDO * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Producto generarUnPez() {
        Producto pescado = new Alimento(PRECIO_DE_PESCADO, "pescado", true, false);
        return pescado;
    }

    private int generarUnValorAleatorio(int valor) {
        Random random = new Random();
        int cantidad = random.nextInt(valor);
        return cantidad + 1;
    }

    public boolean hayPeces() {
        if (cantidadDePeces > 0) {
            return true;
        }
        return false;
    }

    public static int getPRECIO_DE_BARCO() {
        return PRECIO_DE_BARCO;
    }

    public boolean isHayBarco() {
        return hayBarco;
    }

    public void setHayBarco(boolean hayBarco) {
        this.hayBarco = hayBarco;
    }

    @Override
    public PanelSueloAgua getPanel() {  
        return panel;
    }

    public int getCantidadDePeces() {
        return cantidadDePeces;
    }

    public void setCantidadDePeces(int cantidadDePeces) {
        this.cantidadDePeces = cantidadDePeces;
    }

    public boolean isDetenerse() {
        return detenerse;
    }

    public void setDetenerse(boolean detenerse) {
        this.detenerse = detenerse;
    }
    
    
}
