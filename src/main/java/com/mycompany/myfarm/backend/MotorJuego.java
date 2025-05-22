package com.mycompany.myfarm.backend;

import com.mycompany.myfarm.backend.creadores.CreadorDeAnimales;
import com.mycompany.myfarm.backend.creadores.CreadorDeProductos;
import com.mycompany.myfarm.backend.fertilizante.Fertilizante;
import com.mycompany.myfarm.backend.planta.Planta;
import com.mycompany.myfarm.backend.planta.PlantaFruto;
import com.mycompany.myfarm.backend.planta.PlantaGrano;
import com.mycompany.myfarm.backend.producto.AlimentoFruto;
import com.mycompany.myfarm.backend.producto.AlimentoGrano;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.backend.semilla.Semilla;

/**
 *
 * @author ronaldo
 */
public class MotorJuego {
    
    private static Almacenador almacenador;

    public MotorJuego() {
        inicializarJuego();
    }
    
    // metodo que se encarga de agregar las semillas que iran al inicio del juego, las cuales se almacenaran en el mercado
    public Semilla[] crearSemillasIniciales() {
        Semilla[] semillas = new Semilla[2];
        
        Producto producto1 = new AlimentoFruto(5, "Manzana", false, false);
        almacenador.getProductosDisponibles().agregarElemento(producto1);
        Planta planta1 = new PlantaFruto("Manzano", 5);
        planta1.setProducto(producto1);
        semillas[0] = new Semilla(planta1, "Manzano", 6);
        
        Planta planta2 = new PlantaGrano("Maiz", 6);
        Producto producto2 = new AlimentoGrano(3, "Grano de maiz", false, false);
        almacenador.getProductosDisponibles().agregarElemento(producto2);
        planta2.setProducto(producto2);
        semillas[1] = new Semilla(planta2, planta2.getNombre(), 4);
        
        return semillas;
    }
    
    
    private Fertilizante[] crearFertilizantes() {
        Fertilizante[] fertilizantes = new Fertilizante[3];
        fertilizantes[0] = new Fertilizante("Compost", 20, 3);
        fertilizantes[1] = new Fertilizante("Abono", 15, 2);
        fertilizantes[2] = new Fertilizante("Organico", 5, 1);
        return fertilizantes;
    }
    
    public void inicializarJuego() {        
        almacenador = new Almacenador();
        CreadorDeAnimales creadorDeAnimales = new CreadorDeAnimales();
        CreadorDeProductos creado = new CreadorDeProductos();
        Producto[] productosIniciales = creado.productosIniciales();
        agregarProductosInciales(productosIniciales);
        almacenador.getMercadoGeneral().setFertilizantes(crearFertilizantes());
        almacenador.getMercadoGeneral().agregarTodasLasSemillasIniciales(crearSemillasIniciales());
        almacenador.getMercadoGeneral().agregarTodosLosAnimalesIniciles(creadorDeAnimales.crearAnimalesIniciales(productosIniciales));
        almacenador.getMercadoGeneral().setAlimentosParaAnimales(creado.generarAlimentosParaAnimales());
    }
    
    public void agregarProductosInciales(Producto[] productos) {
        for (int i = 0; i < productos.length; i++) {
            almacenador.getProductosDisponibles().agregarElemento(productos[i]);
        }
    }
   


    
    public static Almacenador getAlmacenador() {
        return almacenador;
    }
    
    public static void setAlmacenador(Almacenador almacenador) {
        MotorJuego.almacenador = almacenador;
    }
    
}
