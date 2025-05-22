/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.planta;

import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.backend.suelo.SueloGrama;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public abstract class Planta implements Runnable {
    private static final int UN_SEGUNDO=1000;
    protected String nombre;
    protected int cantidadDeSemillas;
    protected int tiempoParaCosechar;
    protected int tiempoParaPudrirse;
    protected Producto producto;
    protected SueloGrama suelo;
    protected boolean productoFresco;
    protected boolean listoParaCosechar;
    protected boolean fueCosechado;
    protected boolean estaCreciendo;
    protected int cantidadDeProductosQueGenero;
    protected Producto[] productosGenerados;
    protected int celdasSembradas;
    protected int cantidadDeSemillasVendidas;
    protected boolean detenerse;
    protected boolean seAcaboPartida;

    public Planta(String nombre, int cantidadDeSemillas) {
        this.nombre = nombre;
        this.cantidadDeSemillas = cantidadDeSemillas;
    }

    public Planta(String nombre, int cantidadDeSemillas, Producto producto) {
        this.nombre = nombre;
        this.cantidadDeSemillas = cantidadDeSemillas;
        this.producto = producto;
        productoFresco = true;
    }

    public void crecimientoDeLaPlanta() {
        while (tiempoParaCosechar > 0 && !seAcaboPartida) {
            if (detenerse) {
                try {
                    Thread.sleep(UN_SEGUNDO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    estaCreciendo = true;
                    Thread.sleep(UN_SEGUNDO);
                    tiempoParaCosechar -= UN_SEGUNDO;
                    suelo.getPanel().getLblTiempo().setText("cosecha: " + tiempoParaCosechar / 1000);
                } catch (InterruptedException e) {
                }
            }

        }
        productosGenerados = generarProductos();
        suelo.getGranja().getColaCosechas().agregarACola(this);
        suelo.getPanel().getLblTiempo().setText("");
        suelo.getPanel().actualizarIcono(2);
        productoFresco = true;
        listoParaCosechar = true;
        estaCreciendo = false;
    }

    public void iniciarDescomposicion() {
        while (tiempoParaPudrirse > 0 && !fueCosechado && !seAcaboPartida) {
            if (detenerse) {
                try {
                    Thread.sleep(UN_SEGUNDO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                revisarSiSeCosecho();
                try {
                    suelo.getPanel().getLblTiempo().setText("descompone: " + tiempoParaPudrirse / 500);
                    Thread.sleep(UN_SEGUNDO/2);
                    tiempoParaPudrirse -= UN_SEGUNDO/2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!fueCosechado) {
            suelo.getPanel().actualizarIcono(3);
            suelo.getPanel().getLblTiempo().setText("Limpia esta celda");
            suelo.getGranja().getColaCosechas().eliminarUnaPlantaDeLaCola(suelo.getPosicion());
            productoFresco = false;
            suelo.setEstaSucio(true);
        }
        System.out.println("termino hilo");
    }

    public boolean revisarSiSeCosecho() {
        return fueCosechado;
    }

    public int generarValorAleatorio(int maximo, int minimo) {
        Random random = new Random();
        int valor = random.nextInt(maximo - minimo + 1) + minimo;
        return valor;
    }

    public Producto[] generarProductos() {
        int cantidadProductosInicial = generarValorAleatorio(10, 5);
        int otrosAgregados = calcularCantidadDeProductosExta(cantidadProductosInicial);
        int total = cantidadProductosInicial + otrosAgregados;
        Producto[] productos = new Producto[total];
        for (int i = 0; i < total; i++) {
            productos[i] = producto;
        }
        cantidadDeProductosQueGenero = productos.length;
        return productos;
    }

    private int calcularCantidadDeProductosExta(int cantidadInicial) {
        int agregadoPoFertilizante = cantidadInicial * suelo.getCalidadDelSuelo();
        int agregadoPorExperiencia = (int) (suelo.getGranja().getGranjero().getExperiencia() * 0.3);
        return agregadoPoFertilizante + agregadoPorExperiencia;
    }
    
    public void agregarSemillasCompradas(int cantidad){
        cantidadDeSemillasVendidas+=cantidad;
    }
    public void agregarCeldaSembrada(){
        celdasSembradas++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDeSemillas() {
        return cantidadDeSemillas;
    }

    public int getTiempoParaCosechar() {
        return tiempoParaCosechar;
    }

    public int getTiempoParaPudrirse() {
        return tiempoParaPudrirse;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public SueloGrama getSuelo() {
        return suelo;
    }

    public boolean isProductoFresco() {
        return productoFresco;
    }

    public boolean isListoParaCosechar() {
        return listoParaCosechar;
    }

    public void setSuelo(SueloGrama suelo) {
        this.suelo = suelo;
    }

    public boolean isFueCosechado() {
        return fueCosechado;
    }

    public void setFueCosechado(boolean fueCosechado) {
        this.fueCosechado = fueCosechado;
    }

    public void setTiempoParaPudrirse(int tiempoParaPudrirse) {
        this.tiempoParaPudrirse = tiempoParaPudrirse;
    }

    public boolean isEstaCreciendo() {
        return estaCreciendo;
    }

    public void setEstaCreciendo(boolean estaCreciendo) {
        this.estaCreciendo = estaCreciendo;
    }

    public int getCantidadDeProductosQueGenero() {
        return cantidadDeProductosQueGenero;
    }

    public void setCantidadDeProductosQueGenero(int cantidadDeProductosQueGenero) {
        this.cantidadDeProductosQueGenero = cantidadDeProductosQueGenero;
    }

    public Producto[] getProductosGenerados() {
        return productosGenerados;
    }

    public void setProductosGenerados(Producto[] productosGenerados) {
        this.productosGenerados = productosGenerados;
    }

    public int getCeldasSembradas() {
        return celdasSembradas;
    }

    public void setCeldasSembradas(int celdasSembradas) {
        this.celdasSembradas = celdasSembradas;
    }

    public int getCantidadDeSemillasVendidas() {
        return cantidadDeSemillasVendidas;
    }

    public void setCantidadDeSemillasVendidas(int cantidadDeSemillasVendidas) {
        this.cantidadDeSemillasVendidas = cantidadDeSemillasVendidas;
    }

    public boolean isDetenerse() {
        return detenerse;
    }

    public void setDetenerse(boolean detenerse) {
        this.detenerse = detenerse;
    }

    public boolean isSeAcaboPartida() {
        return seAcaboPartida;
    }

    public void setSeAcaboPartida(boolean seAcaboPartida) {
        this.seAcaboPartida = seAcaboPartida;
    }

    

}
