package com.mycompany.myfarm.backend.suelo;

import com.mycompany.myfarm.backend.granja.Granja;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author ronaldo
 */
public abstract class Suelo implements Serializable{
    protected int [] posicion;
    protected Granja granja;
    protected int precio;
    
    public Suelo(int [] posicion, Granja granja){
        this.posicion=posicion;
        this.granja=granja;
       
    }
    
    public int[] getPosicion() {
        return posicion;
    }
    
    public abstract JPanel getPanel(); 

    public Granja getGranja() {
        return granja;
    }

    public int getPrecio() {
        return precio;
    }

}
