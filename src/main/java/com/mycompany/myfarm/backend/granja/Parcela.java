
package com.mycompany.myfarm.backend.granja;

import com.mycompany.myfarm.backend.animal.Animal;
import com.mycompany.myfarm.backend.cola.exception.ColaException;
import com.mycompany.myfarm.backend.cola.exception.ListaEnlazadaException;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.producto.MateriaPrima;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.backend.suelo.SueloGrama;

/**
 *
 * @author ronaldo
 */
public class Parcela {

    private String nombre;
    private ListaEnlazadaGenerica<SueloGrama> suelos;
    private ListaEnlazadaGenerica<Animal> animales;
    private int capacidad;
    private boolean losAnimalesNecesitanAlimento;

    public Parcela() {
        suelos = new ListaEnlazadaGenerica<>();
        animales = new ListaEnlazadaGenerica<>();
    }

    /**
     *
     * @param alimentos
     * @param nombreAlimento
     * @param cantidadRequerida
     * @return
     */
    public boolean hayComidaEnBodaga(ListaEnlazadaGenerica<Alimento> alimentos, String nombreAlimento, int cantidadRequerida) {
        int contador = 0;
        for (int i = 0; i < alimentos.getTamaño(); i++) {
            if (alimentos.obtenerContenido(i).getNombre().equals(nombreAlimento)) {
                contador++;
            }
        }
        if (contador >= cantidadRequerida) {
            return true;
        }
        return false;
    }

    public void detenerHiloAnimales() {
        for (int i = 0; i < animales.getTamaño(); i++) {
            Animal animal = animales.obtenerContenido(i);
            if (animal.isEstaVivo()) {
                animal.setDetenerse(true);
            }
        }
    }

    public void continuarHiloAnimales() {
        for (int i = 0; i < animales.getTamaño(); i++) {
            Animal animal = animales.obtenerContenido(i);
            if (animal.isEstaVivo()) {
                animal.setDetenerse(false);
            }
        }
    }

    public void terminarHiloAnimales() {
        for (int i = 0; i < animales.getTamaño(); i++) {
            Animal animal = animales.obtenerContenido(i);
            if (animal.isEstaVivo()) {
                animal.setSeAcaboPartida(true);
            }
        }
    }

    public void eliminarTodosLosAnimalesMuertos() {
        for (int i = 0; i < animales.getTamaño(); i++) {
            Animal animal = animales.obtenerContenido(i);
            if (!animal.isEstaVivo()) {
                try {
                    animales.eliminar(i);
                    i -= 1;
                } catch (ListaEnlazadaException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    // metodo que se encarga de quitar los suelos que tiene la parcela cuando el granjero lo quiere eliminar 
    public void desocuparSuelos() {
        for (int i = 0; i < suelos.getTamaño(); i++) {
            SueloGrama suelo = suelos.obtenerContenido(i);
            suelo.setEstaOcupado(false);
            suelo.getPanel().actualizarIcono(0);
            suelo.getPanelDisponibilidad().inicilizarFondoYLabelInfo();
        }
    }

    public int contarAnimalesMuertos() {
        int contador = 0;
        for (int i = 0; i < animales.getTamaño(); i++) {
            Animal animal = animales.obtenerContenido(i);
            if (!animal.isEstaVivo()) {
                contador++;
            }
        }
        return contador;
    }

    public boolean hayAnimalesVivos() {
        for (int i = 0; i < animales.getTamaño(); i++) {
            Animal animal = animales.obtenerContenido(i);
            if (animal.isEstaVivo()) {
                return true;
            }
        }
        return false;
    }

    // metodo que se encarga de procesar una animal, verificando si el granjero quiere obtener todo o solo los productos sin destace
    /**
     * *
     *
     * @param bodega
     * @param animal
     * @param tipoProcesamiento
     * @param indiceAnimal
     */
    public void procesarUnAnimal(Bodega bodega, Animal animal, int tipoProcesamiento, int indiceAnimal) {
        ListaEnlazadaGenerica<Producto> productosGenerados = animal.getProductosAcumulados();
        Granja granja = suelos.obtenerContenido(0).getGranja();
        if (animal.isEstaVivo()) {
            // se procesan productos sin destace
            if (tipoProcesamiento == 0) {
                for (int i = 0; i < productosGenerados.getTamaño(); i++) {
                    Producto producto = productosGenerados.obtenerContenido(i);
                    bodega.getProductosGuardados().agregarElemento(producto);
                    if (producto instanceof Alimento) {
                        granja.agregarAlimentoGenerado();
                    }
                }
                animal.getProductosAcumulados().setInicio(null);
                animal.getProductosAcumulados().setTamaño(0);
                //se procesan productos con y sin destace
            } else if (tipoProcesamiento == 1) {
                boolean hayProductoConDestace = false;
                animal.generarProductosConDestace();
                for (int i = 0; i < productosGenerados.getTamaño(); i++) {
                    Producto producto = productosGenerados.obtenerContenido(i);
                    bodega.getProductosGuardados().agregarElemento(producto);
                    if (productosGenerados.obtenerContenido(i).isSeObtieneConDestace()) {
                        hayProductoConDestace = true;
                        if (producto instanceof Alimento) {
                            granja.agregarAlimentoGenerado();
                        }
                    }

                }
                if (hayProductoConDestace) {
                    animal.setEstaVivo(false);
                    try {
                        animales.eliminar(indiceAnimal);
                    } catch (ListaEnlazadaException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

    }

    /**
     *
     * @param bodega
     */
    public void procesarParcela(Bodega bodega) {
        boolean esDestasable = animales.obtenerContenido(0).isEsDestasable();
        if (esDestasable) {
            while (animales.getTamaño() > 0) {
                procesarUnAnimal(bodega, animales.obtenerContenido(0), 1, 0);
            }
        } else {
            int contador = 0;
            while (contador < animales.getTamaño()) {
                procesarUnAnimal(bodega, animales.obtenerContenido(contador), 0, 0);
                contador++;
            }
        }

    }

    /**
     * 
     * @param bodega 
     */
    public void procesarParcelaProduccionAlimento(Bodega bodega) {
        boolean esDestasable = animales.obtenerContenido(0).isEsDestasable();
        if (esDestasable) {
            while (animales.getTamaño() > 0) {
                procesarProductosAlimentoDeUnAnimal(bodega, animales.obtenerContenido(0), 0);
            }
        } else {
            int contador = 0;
            while (contador < animales.getTamaño()) {
                procesarProductosAlimentoDeUnAnimal(bodega, animales.obtenerContenido(contador), 0);
                contador++;
            }
        }
    }

    /**
     *
     * @param bodega
     * @param animal
     * @param indiceAnimal
     */
    public void procesarProductosAlimentoDeUnAnimal(Bodega bodega, Animal animal, int indiceAnimal) {
        Granja granja = suelos.obtenerContenido(0).getGranja();
        animal.generarProductosConDestace();
        boolean hayProductoConDestace = false;
        for (int i = 0; i < animal.getProductosAcumulados().getTamaño(); i++) {
            if (animal.getProductosAcumulados().obtenerContenido(i) instanceof Alimento) {
                Producto producto = animal.getProductosAcumulados().obtenerContenido(i);
                bodega.getProductosGuardados().agregarElemento(producto);
                if (producto.isSeObtieneConDestace()) {
                    hayProductoConDestace = true;
                }
                granja.agregarAlimentoGenerado();
            }
        }
        if (hayProductoConDestace) {
            animal.setEstaVivo(false);
            try {
                animales.eliminar(indiceAnimal);
            } catch (ListaEnlazadaException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String hacerListaProductos() {
        String lista = "";
        for (int i = 0; i < animales.obtenerContenido(0).getProductos().getTamaño(); i++) {
            lista = lista + animales.obtenerContenido(0).getProductos().obtenerContenido(i).getNombre() + ", ";
        }
        return lista;
    }

    public String hacerListaAlimentos() {
        String lista = "";
        for (int i = 0; i < animales.obtenerContenido(0).getProductos().getTamaño(); i++) {
            if (animales.obtenerContenido(0).getProductos().obtenerContenido(i) instanceof Alimento) {
                lista = lista + animales.obtenerContenido(0).getProductos().obtenerContenido(i).getNombre() + ", ";
            }

        }
        return lista;
    }

    public void actualizarIconoParcela(int opcion) {
        for (int i = 0; i < suelos.getTamaño(); i++) {
            suelos.obtenerContenido(i).getPanel().actualizarIcono(opcion);

        }
    }

    public void procesarParcelaProduccionMateriaPrima(Bodega bodega) {
        boolean esDestasable = animales.obtenerContenido(0).isEsDestasable();
        if (esDestasable) {
            while (animales.getTamaño() > 0) {
                procesarProductosMateriaPrimaDeUnAnimal(bodega, animales.obtenerContenido(0), 0);
            }
        } else {
            int contador = 0;
            while (contador < animales.getTamaño()) {
                procesarProductosMateriaPrimaDeUnAnimal(bodega, animales.obtenerContenido(contador), 0);
                contador++;
            }
        }
    }

    /**
     * metodo que se encarga de procesar la materia prima que tenga un animal
     *
     * @param bodega
     * @param animal
     * @param indiceAnimal
     */
    public void procesarProductosMateriaPrimaDeUnAnimal(Bodega bodega, Animal animal, int indiceAnimal) {
        animal.generarProductosConDestace();
        boolean hayProductoConDestace = false;
        for (int i = 0; i < animal.getProductosAcumulados().getTamaño(); i++) {
            if (animal.getProductosAcumulados().obtenerContenido(i) instanceof MateriaPrima) {
                Producto producto = animal.getProductosAcumulados().obtenerContenido(i);
                bodega.getProductosGuardados().agregarElemento(producto);
                if (producto.isSeObtieneConDestace()) {
                    hayProductoConDestace = true;
                }
            }
        }
        if (hayProductoConDestace) {
            animal.setEstaVivo(false);
            try {
                animales.eliminar(indiceAnimal);
            } catch (ListaEnlazadaException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public boolean elAnimalGeneraMateriaPrima() {
        Animal animal = animales.obtenerContenido(0);
        for (int i = 0; i < animal.getProductos().getTamaño(); i++) {
            Producto producto = animal.getProductos().obtenerContenido(i);
            if (producto instanceof MateriaPrima) {
                return true;
            }
        }
        return false;
    }

    public void procesarParcelaProduccionConDestace(Bodega bodega) {
        while (animales.getTamaño() > 0) {
            procesarProductosDeUnAnimalConDestace(bodega, animales.obtenerContenido(0), 0);
        }
    }

    public void procesarProductosDeUnAnimalConDestace(Bodega bodega, Animal animal, int indiceAnimal) {
        animal.generarProductosConDestace();
        for (int i = 0; i < animal.getProductosAcumulados().getTamaño(); i++) {
            Producto producto = animal.getProductosAcumulados().obtenerContenido(i);
            if (producto.isSeObtieneConDestace()) {
                bodega.getProductosGuardados().agregarElemento(producto);
            }
        }
        animal.setEstaVivo(false);
        try {
            animales.eliminar(indiceAnimal);
        } catch (ListaEnlazadaException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void procesarParcelaProduccionSinDestace(Bodega bodega) {
        int contador = 0;
        while (contador < animales.getTamaño()) {
            procesarUnAnimal(bodega, animales.obtenerContenido(contador), 0, 0);
            contador++;
        }
    }

    public boolean elAnimalGeneraAlimentos() {
        Animal animal = animales.obtenerContenido(0);
        for (int i = 0; i < animal.getProductos().getTamaño(); i++) {
            Producto producto = animal.getProductos().obtenerContenido(i);
            if (producto instanceof Alimento) {
                return true;
            }
        }
        return false;
    }

    public String hacerListaMateriaPrima() {
        String lista = "";
        for (int i = 0; i < animales.obtenerContenido(0).getProductos().getTamaño(); i++) {
            if (animales.obtenerContenido(0).getProductos().obtenerContenido(i) instanceof MateriaPrima) {
                lista = lista + animales.obtenerContenido(0).getProductos().obtenerContenido(i).getNombre() + ", ";
            }

        }
        return lista;
    }

    public String hacerListaProductosConDestace() {
        String lista = "";
        for (int i = 0; i < animales.obtenerContenido(0).getProductos().getTamaño(); i++) {
            if (animales.obtenerContenido(0).getProductos().obtenerContenido(i).isSeObtieneConDestace()) {
                lista = lista + animales.obtenerContenido(0).getProductos().obtenerContenido(i).getNombre() + ", ";
            }

        }
        return lista;
    }

    public boolean elAnimalGeneraProductosConDestace() {
        Animal animal = animales.obtenerContenido(0);
        for (int i = 0; i < animal.getProductos().getTamaño(); i++) {
            Producto producto = animal.getProductos().obtenerContenido(i);
            if (producto.isSeObtieneConDestace()) {
                return true;
            }
        }
        return false;
    }

    //metodo para verificar si un animal es destasable
    public boolean elAnimalGeneraProductosSinDestace() {
        Animal animal = animales.obtenerContenido(0);
        for (int i = 0; i < animal.getProductos().getTamaño(); i++) {
            Producto producto = animal.getProductos().obtenerContenido(i);
            if (!producto.isSeObtieneConDestace()) {
                return true;
            }
        }
        return false;
    }

    public String hacerListaProductosSinDestace() {
        String lista = "";
        for (int i = 0; i < animales.obtenerContenido(0).getProductos().getTamaño(); i++) {
            if (!animales.obtenerContenido(0).getProductos().obtenerContenido(i).isSeObtieneConDestace()) {
                lista = lista + animales.obtenerContenido(0).getProductos().obtenerContenido(i).getNombre() + ", ";
            }

        }
        return lista;
    }

    public void darleDeComerAnimal(Animal animal, Alimento alimento, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            try {
                animal.getAlimentosAcumulados().agregarACola(alimento);
            } catch (ColaException ex) {

            }
        }
    }

    public void agregarCapacidad() {
        capacidad++;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ListaEnlazadaGenerica<SueloGrama> getSuelos() {
        return suelos;
    }

    public void setSuelos(ListaEnlazadaGenerica<SueloGrama> suelos) {
        this.suelos = suelos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaEnlazadaGenerica<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(ListaEnlazadaGenerica<Animal> animales) {
        this.animales = animales;
    }

    public boolean isLosAnimalesNecesitanAlimento() {
        return losAnimalesNecesitanAlimento;
    }

    public void setLosAnimalesNecesitanAlimento(boolean losAnimalesNecesitanAlimento) {
        this.losAnimalesNecesitanAlimento = losAnimalesNecesitanAlimento;
    }

}
