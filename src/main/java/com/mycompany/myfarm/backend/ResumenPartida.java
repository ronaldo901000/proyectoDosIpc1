/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend;
import java.io.Serializable;

/**
 *
 * @author ronaldo
 */
public class ResumenPartida  implements Serializable{
    public static final long serialVersionUID = 463534534;
    private String nombrePartida;
    private int duracionPartida;
    private int oroGeneradoGranja;
    private int alimentosGenerados;
    private int productosConsumidos;
    private int cantidadDeCeldasCompradas;

    public ResumenPartida(String nombrePartida, int duracionPartida, int oroGeneradoGranja, int alimentosGenerados, int productosConsumidos, int cantidadDeCeldasCompradas) {
        this.nombrePartida = nombrePartida;
        this.duracionPartida = duracionPartida;
        this.oroGeneradoGranja = oroGeneradoGranja;
        this.alimentosGenerados = alimentosGenerados;
        this.productosConsumidos = productosConsumidos;
        this.cantidadDeCeldasCompradas = cantidadDeCeldasCompradas;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public int getDuracionPartida() {
        return duracionPartida;
    }

    public void setDuracionPartida(int duracionPartida) {
        this.duracionPartida = duracionPartida;
    }

    public int getOroGeneradoGranja() {
        return oroGeneradoGranja;
    }

    public void setOroGeneradoGranja(int oroGeneradoGranja) {
        this.oroGeneradoGranja = oroGeneradoGranja;
    }

    public int getAlimentosGenerados() {
        return alimentosGenerados;
    }

    public void setAlimentosGenerados(int alimentosGenerados) {
        this.alimentosGenerados = alimentosGenerados;
    }

    public int getProductosConsumidos() {
        return productosConsumidos;
    }

    public void setProductosConsumidos(int productosConsumidos) {
        this.productosConsumidos = productosConsumidos;
    }

    

    public int getCantidadDeCeldasCompradas() {
        return cantidadDeCeldasCompradas;
    }

    public void setCantidadDeCeldasCompradas(int cantidadDeCeldasCompradas) {
        this.cantidadDeCeldasCompradas = cantidadDeCeldasCompradas;
    }
    
    
    
}
