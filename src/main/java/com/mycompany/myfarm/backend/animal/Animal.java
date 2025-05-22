/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.animal;

import com.mycompany.myfarm.backend.cola.ColaGenerica;
import com.mycompany.myfarm.backend.cola.exception.ColaException;
import com.mycompany.myfarm.backend.granja.Parcela;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.producto.Producto;
import java.util.Random;
/**
 *
 * @author ronaldo
 */
public abstract class Animal implements Runnable {

    protected static final int MAXIMO_CON_HAMBRE = 5;
    protected static final int MINIMO_CON_HAMBRE = 3;
    protected static final int TIEMPO_ESPERA_MAX = 25000;
    protected static final int UN_SEGUNDO = 1000;
    protected String nombre;
    protected int edad;
    protected int cantidadDeCrias;
    protected int cantidadDeAlimentoConsumido;
    protected int edadMaxima;
    protected boolean estaVivo;
    protected boolean esDestasable;
    protected ListaEnlazadaGenerica<Producto> productos;
    protected ListaEnlazadaGenerica<Producto> productosAcumulados;
    protected int precioDeCompra;
    protected int precioDeVentaCria;
    protected double espacioEnParcela;
    protected int capacidadDeAguantarHambre;
    protected int vecesQueNoHaComido;
    protected boolean esAdulto;
    protected Parcela parcela;
    protected boolean fueAlimentado;
    protected ColaGenerica<Alimento> alimentosAcumulados;
    protected int criasCompradas;
    protected int cantidadDeVecesDestazado;
    protected boolean detenerse;
    protected boolean seAcaboPartida;

    public Animal(String nombre, int edadMaxima, boolean esDestasable, int precioDeCompra, int precioDeVentaCria, double espacioEnParcela) {
        this.nombre = nombre;
        this.edadMaxima = edadMaxima;
        this.esDestasable = esDestasable;
        this.precioDeCompra=precioDeCompra;
        this.precioDeVentaCria=precioDeVentaCria;
        this.espacioEnParcela=espacioEnParcela;
        productos= new ListaEnlazadaGenerica<>();
        productosAcumulados=new ListaEnlazadaGenerica<>();
       // capacidadDeAguantarHambre=generarValorAleatorio(MAXIMO_CON_HAMBRE, MINIMO_CON_HAMBRE);
        capacidadDeAguantarHambre=1;
        alimentosAcumulados= new ColaGenerica<>(5);
        estaVivo=true;
        
    }

    
    //metodo que se encarga de ir aumentando la edad del animal
    public void aumentarEdad() {
        int tiempoPasado = 0;
        while (tiempoPasado < UN_SEGUNDO * 40 && !seAcaboPartida) {
            if (detenerse) {
                try {
                    Thread.sleep(UN_SEGUNDO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            } else {
                try {
                    Thread.sleep(UN_SEGUNDO);
                    tiempoPasado += UN_SEGUNDO;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
        }
        parcela.actualizarIconoParcela(5);
        edad++;
        if (edad >= edadMaxima) {
            estaVivo = false;
        }
        esAdulto = edad >= edadMaxima / 3 && edad <= (edadMaxima / 3) * 2;
    }

    // metodo que se encarga de avisar al granjero que los animales necesitan comida
    public void pedirComida() {
        parcela.actualizarIconoParcela(6);
        int contador = 0;

        while (contador <= TIEMPO_ESPERA_MAX && !fueAlimentado && !seAcaboPartida) {
            if (detenerse) {
                try {
                    Thread.sleep(UN_SEGUNDO); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    alimentosAcumulados.sacarDeCola();
                    fueAlimentado = true;
                } catch (ColaException ex) {
                    try {
                        Thread.sleep(UN_SEGUNDO);
                        contador += UN_SEGUNDO; 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (!fueAlimentado) {
            vecesQueNoHaComido++;
            if (vecesQueNoHaComido >= capacidadDeAguantarHambre) {
                estaVivo = false;
            }
        }

        fueAlimentado = false;
    }

    public void generarProductosSinDestace() {
        int totalProductos = definirCantidadProductos();
        for (int i = 0; i < productos.getTamaño(); i++) {
            if (!productos.obtenerContenido(i).isSeObtieneConDestace()) {
                Producto producto = productos.obtenerContenido(i);
                int porcentajeDeProduccion = producto.getPorcetajeDeLaProduccion();
                double porcentaje = (double) porcentajeDeProduccion / 100;
                int totalAGenerar = (int) ((int) totalProductos * porcentaje);
                for (int j = 0; j < totalAGenerar; j++) {
                    productosAcumulados.agregarElemento(producto);
                }
            }
        }
        if (esAdulto) {
            generarCrias();
        }
    }

    public void generarCrias() {
        int cantidadCrias = generarValorAleatorio(4, 2);
        for (int i = 0; i < cantidadCrias; i++) {
            Producto producto = new Alimento(generarValorAleatorio(10, 3), "Cria de " + nombre, true, false);
            productosAcumulados.agregarElemento(producto);
        }
    }
    
    public void agregarContadorCriaComprada(int cantidad) {
        cantidadDeCrias+=cantidad;
    }

    public void agregarUnidadDestazada() {
        cantidadDeVecesDestazado++;
    }

    public void generarProductosConDestace() {
        int totalProductos = definirCantidadProductos();
        for (int i = 0; i < productos.getTamaño(); i++) {
            if (productos.obtenerContenido(i).isSeObtieneConDestace()) {
                Producto producto = productos.obtenerContenido(i);
                int porcentajeDeProduccion = producto.getPorcetajeDeLaProduccion();
                double porcentaje = (double) porcentajeDeProduccion / 100;
                int totalAGenerar = (int) ((int) totalProductos * porcentaje);
                for (int j = 0; j < totalAGenerar; j++) {
                    productosAcumulados.agregarElemento(producto);
                }
            }
        }
    }

    
    /**metodo que se encarga de calcular la cantidad de productos que va a generar el animal
     * @param tipo
     * @return 
     */
    public int definirCantidadProductos() {
        int cantidadDeProducto;
        // si el granjero esta al dia con la recoleccion de productos, genera mas productos que cuando lo tiene acumulado
        if (productosAcumulados.getTamaño() == 0) {
            cantidadDeProducto = generarValorAleatorio(5, 3);
        } else {
            cantidadDeProducto = generarValorAleatorio(2, 1);
        }
        int adicionalAdulto = generarValorAleatorio(3, 2);
        int adicionalAlimentado = generarValorAleatorio(3, 2);
        int totalProductos;
        if (esAdulto && vecesQueNoHaComido == 0) {
            totalProductos = cantidadDeProducto + adicionalAdulto + adicionalAlimentado;
        } else if (esAdulto) {
            totalProductos = cantidadDeProducto + adicionalAdulto;
        } else if (vecesQueNoHaComido == 0) {
            totalProductos = cantidadDeProducto + adicionalAlimentado;
        } else {
            totalProductos = cantidadDeProducto;
        }
        return totalProductos;
    }
    


    // metodo que se encarga de agregar los productos seleccionados por el jugador al momento de crear un animal
    public void agregarUnProducto(Producto productoNuevo) {
        boolean yaExisteElProducto = false;
        int indiceDondeYaExiste = 0;
        for (int i = 0; i < productos.getTamaño() - 1; i++) {
            if (productoNuevo.getNombre().equals(productos.obtenerContenido(i).getNombre())) {
                indiceDondeYaExiste = i;
                yaExisteElProducto = true;
                break;
            }
        }
        if (yaExisteElProducto) {
            int porcentajeAnterior = productos.obtenerContenido(indiceDondeYaExiste).getPorcetajeDeLaProduccion();
            productos.obtenerContenido(indiceDondeYaExiste).setPorcetajeDeLaProduccion(porcentajeAnterior + productoNuevo.getPorcetajeDeLaProduccion());
        } else {
            productos.agregarElemento(productoNuevo);
        }

    }
    
    public int generarValorAleatorio(int maximo,int minimo){
        Random random = new Random();
        int valor=random.nextInt(maximo-minimo+1)+minimo;
        return valor;
    }
   
    public void comer(Alimento alimento) {
    }
    
    public void generarUnaCria() {
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getCantidadDeCrias() {
        return cantidadDeCrias;
    }

    public int getCantidadDeAlimentoConsumido() {
        return cantidadDeAlimentoConsumido;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public boolean isEsDestasable() {
        return esDestasable;
    }

    public int getPrecioDeCompra() {
        return precioDeCompra;
    }

    public int getPrecioDeVentaCria() {
        return precioDeVentaCria;
    }


    public double getEspacioEnParcela() {
        return espacioEnParcela;
    }

    public ListaEnlazadaGenerica<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ListaEnlazadaGenerica<Producto> productos) {
        this.productos = productos;
    }

    public ListaEnlazadaGenerica<Producto> getProductosAcumulados() {
        return productosAcumulados;
    }

    public void setProductosAcumulados(ListaEnlazadaGenerica<Producto> productosAcumulados) {
        this.productosAcumulados = productosAcumulados;
    }

    public int getCapacidadDeAguantarHambre() {
        return capacidadDeAguantarHambre;
    }

    public void setCapacidadDeAguantarHambre(int capacidadDeAguantarHambre) {
        this.capacidadDeAguantarHambre = capacidadDeAguantarHambre;
    }

    public int getVecesQueNoHaComido() {
        return vecesQueNoHaComido;
    }

    public void setVecesQueNoHaComido(int vecesQueNoHaComido) {
        this.vecesQueNoHaComido = vecesQueNoHaComido;
    }

    public boolean isEsAdulto() {
        return esAdulto;
    }

    public void setEsAdulto(boolean esAdulto) {
        this.esAdulto = esAdulto;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public boolean isFueAlimentado() {
        return fueAlimentado;
    }

    public void setFueAlimentado(boolean fueAlimentado) {
        this.fueAlimentado = fueAlimentado;
    }

    public ColaGenerica<Alimento> getAlimentosAcumulados() {
        return alimentosAcumulados;
    }

    public void setAlimentosAcumulados(ColaGenerica<Alimento> alimentosAcumulados) {
        this.alimentosAcumulados = alimentosAcumulados;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public int getCriasCompradas() {
        return criasCompradas;
    }

    public void setCriasCompradas(int criasCompradas) {
        this.criasCompradas = criasCompradas;
    }

    public int getCantidadDeVecesDestazado() {
        return cantidadDeVecesDestazado;
    }

    public void setCantidadDeVecesDestazado(int cantidadDeVecesDestazado) {
        this.cantidadDeVecesDestazado = cantidadDeVecesDestazado;
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
