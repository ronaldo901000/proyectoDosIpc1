package com.mycompany.myfarm.backend.creadores;

import com.mycompany.myfarm.backend.MotorJuego;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.producto.AlimentoFruto;
import com.mycompany.myfarm.backend.producto.AlimentoGrano;
import com.mycompany.myfarm.backend.producto.MateriaPrima;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.frontend.FramePrograma;
import com.mycompany.myfarm.frontend.paneles.PanelCreacionProductoPlanta;
import com.mycompany.myfarm.frontend.paneles.PanelCreadorDeProductosAnimal;


/**
 *
 * @author ronaldo
 */
public class CreadorDeProductos {

    private PanelCreadorDeProductosAnimal panel1;
    private PanelCreacionProductoPlanta panel2;

    public CreadorDeProductos() {

    }

    public void iniciarCreacionProductoAnimal() {
        panel1 = new PanelCreadorDeProductosAnimal(this);
        FramePrograma.ponerPanelEnFramePrincipal(panel1);
    }
    public void iniciarCreacionProductoPlanta(){
        panel2= new PanelCreacionProductoPlanta(this);
        FramePrograma.ponerPanelEnFramePrincipal(panel2);
    }

    /**
     * metodo que se encarga de crear un producto con los datos que le pasa por parametro su panel, retorna un producto
     * @param nombre
     * @param precioDeVenta
     * @param seObtieneConDestace
     * @param tipoDeProducto
     * @return 
     */
    public Producto crearUnNuevoProductoAnimal(String nombre, int precioDeVenta, boolean seObtieneConDestace, int tipoDeProducto) {
        Producto productoNuevo;
        if (tipoDeProducto == 1) {
            productoNuevo = new Alimento(precioDeVenta, nombre, seObtieneConDestace, false);
            return productoNuevo;
        }
        productoNuevo = new MateriaPrima(precioDeVenta, nombre, seObtieneConDestace);
        return productoNuevo;
    }
    
    public Producto crearUnNuevoProductoPlanta(String nombre, int precioDeVenta, int tipoDeAlimento){
        Producto productoNuevo;
        if(tipoDeAlimento==0){
            productoNuevo= new AlimentoGrano(precioDeVenta, nombre, false, false);
            return productoNuevo;
        }
        productoNuevo= new AlimentoFruto(precioDeVenta, nombre, false, false);
        return productoNuevo;
    }

    /*
    cuero,carne,leche, huevos
    metodo que se encarga de crear los 4 tipos de productos iniciales
     */
    public Producto[] productosIniciales() {
        Producto[] productos = new Producto[4];
        productos[0] = new MateriaPrima(30, "Cuero", true);
        productos[1] = new Alimento(10, "Carne", true, false);
        productos[2] = new Alimento(5, "Leche", false, false);
        productos[3] = new Alimento(2, "Huevo", false, false);
        return productos;
    }
    

    public boolean elNombreEstaDisponible(String nombre) {
        for (int i = 0; i < MotorJuego.getAlmacenador().getProductosDisponibles().getTamaño(); i++) {
            String nombreEnLista=MotorJuego.getAlmacenador().getProductosDisponibles().obtenerContenido(i).getNombre().toLowerCase();
            if (nombreEnLista.equals(nombre.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public Alimento[] generarAlimentosParaAnimales() {
        Alimento[] alimentos = new Alimento[6];
        alimentos[0] = new Alimento(5, "Hoja", false, true);
        alimentos[1] = new Alimento(4, "Verdura", false, true);
        alimentos[2] = new Alimento(7, "Grama", false, true);
        alimentos[3] = new Alimento(7, "Carne", true, false);
        alimentos[4] = new Alimento(5, "Frutas", false, false);
        alimentos[5] = new Alimento(4, "Vegetales", false, false);
        return alimentos;
    }
    

    public static int buscarIndiceDeUnProducto(String nombre) {
        for (int i = 0; i < MotorJuego.getAlmacenador().getProductosDisponibles().getTamaño(); i++) {
            if (MotorJuego.getAlmacenador().getProductosDisponibles().obtenerContenido(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }
}
