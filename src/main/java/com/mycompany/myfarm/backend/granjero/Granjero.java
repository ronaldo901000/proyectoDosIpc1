
package com.mycompany.myfarm.backend.granjero;

import com.mycompany.myfarm.backend.MotorJuego;
import com.mycompany.myfarm.backend.cola.exception.ColaException;
import com.mycompany.myfarm.backend.cola.exception.ListaEnlazadaException;
import com.mycompany.myfarm.backend.creadores.CreadorDePlantas;
import com.mycompany.myfarm.backend.fertilizante.Fertilizante;
import com.mycompany.myfarm.backend.granja.Granja;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.listaEnlazada.NodoGenerico;
import com.mycompany.myfarm.backend.mercado.Mercado;
import com.mycompany.myfarm.backend.planta.Planta;
import com.mycompany.myfarm.backend.planta.PlantaFruto;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.backend.semilla.Semilla;
import com.mycompany.myfarm.backend.suelo.SueloGrama;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public class Granjero implements Runnable {

    private static final int UN_SEGUNDO = 1000;
    private static final int UN_CICLO_GRANJERO = 25000;
    private static final int LIMITE_PUNTOS_DE_VIDA = 100;
    private String nombre;
    private String nick;
    private boolean estaVivo;
    private int oroDisponible;
    private int puntosDeVida;
    private int alimentosConsumidos;
    private int celdasCompradas;
    private ListaEnlazadaGenerica<Semilla> semillas;
    private int totalDeSemillasAdquiridas;
    private int suelosSembrados;
    private Granja granja;
    private int experiencia;
    private ListaEnlazadaGenerica<Fertilizante> fertilizantes;
    private boolean salioDeLaPartida;

    public Granjero(String nombre, String nick) {
        this.nombre = nombre;
        this.nick = nick;
        estaVivo = true;
        puntosDeVida = 100;
        oroDisponible = 500;
        semillas = new ListaEnlazadaGenerica();
        fertilizantes = new ListaEnlazadaGenerica();
    }

    @Override
    public void run() {
        while (puntosDeVida > 0 && !salioDeLaPartida) {
            disminuirVida();
        }
        if (puntosDeVida <= 0) {
            estaVivo = false;
        }

    }

    public void disminuirVida() {
        int contador = 0;
        while (contador < UN_CICLO_GRANJERO && !salioDeLaPartida) {
            try {
                Thread.sleep(UN_SEGUNDO);
                contador += UN_SEGUNDO;
                granja.getPartida().aumentarDuracionPartida();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (contador <= UN_CICLO_GRANJERO) {
            puntosDeVida -= generarValorAleatorio(10, 5);
        }
    }

    public int generarValorAleatorio(int maximo, int minimo) {
        Random random = new Random();
        int valor = random.nextInt(maximo - minimo + 1) + minimo;
        return valor;
    }

    public void agregarAlimentoConsumido() {
        alimentosConsumidos++;
    }

    /**
     * metodo que se encarga de realizar la siembra
     *
     * @param semilla
     */
    public void sembrar(Semilla semilla) {
        eliminarSemillas(semilla);
        //recorre toda la matriz de suelos buscando solo suelosGrama
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloGrama) {
                    SueloGrama suelo = (SueloGrama) granja.getSuelos()[i][j];
                    //si el sueloGrama es una de las que el granjero quiere sembrar se se agrega una planta
                    if (suelo.isSeleccionada() && !suelo.isEstaOcupado()) {
                        CreadorDePlantas creador = new CreadorDePlantas();
                        Planta planta = creador.crearPlantaParSuelo(semilla);
                        suelo.setPlanta(planta);
                        suelo.setSeleccionada(false);
                        suelo.getPanelDisponibilidad().setFueSeleccionado(false);
                        suelo.setEstaOcupado(true);
                        suelo.getPanel().actualizarIcono(1);
                        suelo.getPlanta().setSuelo(suelo);
                        agregarReporteAPlanta(semilla);
                        Thread hilo = new Thread(suelo.getPlanta());
                        hilo.start();
                    }
                }
            }
        }

    }

    public void cosechar() {
        try {
            Planta planta = granja.getColaCosechas().sacarDeCola();
            planta.setFueCosechado(true);
            Producto[] productos = planta.getProductosGenerados();
            for (int i = 0; i < productos.length; i++) {
                guardarProductoABodega(productos[i]);
                granja.agregarAlimentoGenerado();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            actualizarSuelo(planta.getSuelo());
        } catch (ColaException ex) {
            System.out.println(ex.getMessage());;
        }

    }

    public void actualizarSuelo(SueloGrama suelo) {
        if (suelo.getPlanta() instanceof PlantaFruto) {
            suelo.setYaTieneFertilizante(false);
            PlantaFruto planta = (PlantaFruto) suelo.getPlanta();
            planta.restarUnaRondaDeFrutosHecha();
            if (planta.getCantidadDeVecesQueDaFrutas() > 0) {
                // si la planta es fruto y puede generar maas cosechas se inicia otro 
                planta.reiniciarAtributosPlanta();
                Thread hilo = new Thread(planta);
                hilo.start();
            } else {
                aplicarAccionesCuandoYaNoDaFrutos(suelo);
            }
        } else {
            aplicarAccionesCuandoYaNoDaFrutos(suelo);
        }
    }

    public void agregarReporteAPlanta(Semilla semilla) {
        Mercado mercado = MotorJuego.getAlmacenador().getMercadoGeneral();
        for (int i = 0; i < mercado.getSemillasDisponibles().getTamaño(); i++) {
            if (mercado.getSemillasDisponibles().obtenerContenido(i).getPlanta()
                    .getNombre().equals(semilla.getPlanta().getNombre())) {
                mercado.getSemillasDisponibles().obtenerContenido(i).getPlanta().agregarCeldaSembrada();
            }

        }
    }

    public void aplicarAccionesCuandoYaNoDaFrutos(SueloGrama suelo) {
        suelo.setEstaOcupado(false);
        suelo.setPlanta(null);
        suelo.getPanel().actualizarIcono(0);
        suelo.getPanelDisponibilidad().inicilizarFondoYLabelInfo();
    }

    public void guardarProductoABodega(Producto producto) {
        granja.getBodega().getProductosGuardados().agregarElemento(producto);
    }

    //metodo que se encarga de eliminar todas las semillas que el granjero utiliza para sembrar
    public void eliminarSemillas(Semilla semilla) {
        int totalSemillasEliminar = contarCuantasCeldasSeVanASembrar() * semilla.getPlanta().getCantidadDeSemillas();
        int contador = 0;
        for (int i = 0; i < semillas.getTamaño(); i++) {
            Semilla semillaTemporal = semillas.obtenerNodo(i).getContenido();
            if (semillaTemporal.getNombre().equals(semilla.getNombre()) && contador < totalSemillasEliminar) {
                try {
                    semillas.eliminar(i);
                    i -= 1;
                } catch (ListaEnlazadaException ex) {
                    ex.printStackTrace();
                }
                contador++;

            }
        }
    }

    public void pagar(int descuento) {
        oroDisponible -= descuento;
    }

    public void agregarOro(int ganancia) {
        oroDisponible += ganancia;
    }

    /**
     * metodo que se encarga de verificar si el granjero cuenta con las semillas
     * necesarias para sembrar
     *
     * @param semilla
     * @return
     */
    public boolean haySemillasNecesariasParaSembrar(Semilla semilla) {
        int contador = 0;
        for (int i = 0; i < semillas.getTamaño(); i++) {
            Semilla semillaTemporal = semillas.obtenerContenido(i);
            if (semillaTemporal.getNombre().equals(semilla.getNombre())) {
                contador++;
            }
        }
        int semillasNecesarias = contarCuantasCeldasSeVanASembrar() * semilla.getPlanta().getCantidadDeSemillas();
        return contador >= semillasNecesarias;
    }

    // metodo que se encarga de calcular el total de semillas que va a necesitar el granjero para sembrar en una o varias celdas
    public int contarCuantasCeldasSeVanASembrar() {
        int contador = 0;
        for (int i = 0; i < granja.getFilas(); i++) {
            for (int j = 0; j < granja.getColumnas(); j++) {
                if (granja.getSuelos()[i][j] instanceof SueloGrama) {
                    SueloGrama suelo = (SueloGrama) granja.getSuelos()[i][j];
                    if (suelo.isSeleccionada()) {
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    /**
     * metodo que se encarga de contar cuantas semillas le hacen falta al
     * granjero para poder sembrar
     *
     * @param semilla
     * @return
     */
    public int cantidadDeSemillasQueFaltan(Semilla semilla) {
        int contador = 0;
        for (int i = 0; i < semillas.getTamaño(); i++) {
            if (semillas.obtenerNodo(i).getContenido().getNombre().equals(semilla.getNombre())) {
                contador++;
            }
        }
        int semillasNecesarias = contarCuantasCeldasSeVanASembrar() * semilla.getPlanta().getCantidadDeSemillas();
        return semillasNecesarias - contador;
    }

    public void alimentarse() {

    }

    public int getOroDisponible() {
        return oroDisponible;
    }

    public Granja getGranja() {
        return granja;
    }

    public void setGranja(Granja granja) {
        this.granja = granja;
    }

    public ListaEnlazadaGenerica<Semilla> getSemillas() {
        return semillas;
    }

    public void setSemillas(ListaEnlazadaGenerica<Semilla> semillas) {
        this.semillas = semillas;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public ListaEnlazadaGenerica<Fertilizante> getFertilizantes() {
        return fertilizantes;
    }

    public void setFertilizantes(ListaEnlazadaGenerica<Fertilizante> fertilizantes) {
        this.fertilizantes = fertilizantes;
    }

    public int getPuntosDeVida() {
        return puntosDeVida;
    }

    public void setPuntosDeVida(int puntosDeVida) {
        this.puntosDeVida = puntosDeVida;
    }

    public static int getLIMITE_PUNTOS_DE_VIDA() {
        return LIMITE_PUNTOS_DE_VIDA;
    }

    public int getAlimentosConsumidos() {
        return alimentosConsumidos;
    }

    public void setAlimentosConsumidos(int alimentosConsumidos) {
        this.alimentosConsumidos = alimentosConsumidos;
    }

    public int getCeldasCompradas() {
        return celdasCompradas;
    }

    public void setCeldasCompradas(int celdasCompradas) {
        this.celdasCompradas = celdasCompradas;
    }

    public int getTotalDeSemillasAdquiridas() {
        return totalDeSemillasAdquiridas;
    }

    public void setTotalDeSemillasAdquiridas(int totalDeSemillasAdquiridas) {
        this.totalDeSemillasAdquiridas = totalDeSemillasAdquiridas;
    }

    public int getSuelosSembrados() {
        return suelosSembrados;
    }

    public void setSuelosSembrados(int suelosSembrados) {
        this.suelosSembrados = suelosSembrados;
    }

    public boolean isSalioDeLaPartida() {
        return salioDeLaPartida;
    }

    public void setSalioDeLaPartida(boolean salioDeLaPartida) {
        this.salioDeLaPartida = salioDeLaPartida;
    }

}
