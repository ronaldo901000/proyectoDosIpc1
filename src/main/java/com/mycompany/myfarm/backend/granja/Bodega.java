
package com.mycompany.myfarm.backend.granja;

import com.mycompany.myfarm.backend.cola.exception.ListaEnlazadaException;
import com.mycompany.myfarm.backend.granjero.Granjero;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.producto.Producto;

/**
 *
 * @author ronaldo
 */
public class Bodega {

    private ListaEnlazadaGenerica<Producto> productosGuardados;
    private ListaEnlazadaGenerica<Alimento> alimentosParaAnimales;

    public Bodega() {
        productosGuardados = new ListaEnlazadaGenerica<>();
        alimentosParaAnimales = new ListaEnlazadaGenerica<>();
    }

    public ListaEnlazadaGenerica<Producto> getProductosGuardados() {
        return productosGuardados;
    }

    /**
     * 
     * @param alimento
     * @param cantidadEliminar 
     */
    public void sacarAlimentosDeBodega(Alimento alimento, int cantidadEliminar) {
        int contador = 0;
        for (int i = 0; i < alimentosParaAnimales.getTamaño(); i++) {
            if (alimentosParaAnimales.obtenerContenido(i).getNombre().equals(alimento.getNombre()) && contador < cantidadEliminar) {
                try {
                    alimentosParaAnimales.eliminar(i);
                    i -= 1;
                    contador++;
                } catch (ListaEnlazadaException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    /**
     * 
     * @param cantidad
     * @param granjero 
     */
    //se sacan los productos vendidos y se le da el dinero al granjero
    public void sacarProductos(int cantidad, Granjero granjero) {
        int contador = 0;
        while (contador < cantidad) {
            try {
                granjero.agregarOro(productosGuardados.obtenerContenido(0).getPrecioDeVenta());
                granjero.getGranja().agregarOroGenerado(productosGuardados.obtenerContenido(0).getPrecioDeVenta());
                productosGuardados.eliminar(0);
                contador++;
            } catch (ListaEnlazadaException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public void setProductosGuardados(ListaEnlazadaGenerica<Producto> productosGuardados) {
        this.productosGuardados = productosGuardados;
    }

    public ListaEnlazadaGenerica<Alimento> getAlimentosParaAnimales() {
        return alimentosParaAnimales;
    }

    public void setAlimentosParaAnimales(ListaEnlazadaGenerica<Alimento> alimentosParaAnimales) {
        this.alimentosParaAnimales = alimentosParaAnimales;
    }

}
