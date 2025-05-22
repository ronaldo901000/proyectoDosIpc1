/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend.granja;

import com.mycompany.myfarm.backend.cola.ColaCosecha;
import com.mycompany.myfarm.backend.granjero.Granjero;
import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import com.mycompany.myfarm.backend.partida.Partida;
import com.mycompany.myfarm.backend.producto.Alimento;
import com.mycompany.myfarm.backend.producto.Producto;
import com.mycompany.myfarm.backend.suelo.Suelo;
import com.mycompany.myfarm.backend.suelo.SueloAgua;
import com.mycompany.myfarm.backend.suelo.SueloDesierto;
import com.mycompany.myfarm.backend.suelo.SueloGrama;
import com.mycompany.myfarm.frontend.FramePrograma;
import com.mycompany.myfarm.frontend.paneles.Partida.Granja.PanelGranja;
import java.util.Random;

/**
 *
 * @author ronaldo
 */
public class Granja {

           private static final int MAXIMO_PRECIO = 50;
           private static final int MINIMO_PRECIO = 30;
           private static final int PRECIO_ELIMINACION_PARCELA = 75;
           private static final int PRECIO_LIMPIEZA_CASILLA = 5;
           private int filas;
           private int columnas;
           private Suelo[][] suelos;
           private Granjero granjero;
           private int oroGenerado;
           private int alimentosGenerados;
           private ListaEnlazadaGenerica<Parcela> parcelas;
           private Bodega bodega;
           private ColaCosecha colaCosechas;
           private int celdasSeleccionadas;
           private Random random;
           private Partida partida;
           private boolean actualizarPanelGranja;
           private int celdasCompradas;
           private ControladorDeHilosGranja controladorHilos;

           public Granja(Granjero granjero) {
                      this.granjero = granjero;
                      inicializarGranja();
           }

           public void agregarSueloComprado() {
                      celdasCompradas++;
           }

           public void mostrarPanel() {
                      actualizarPanelGranja = false;
                      PanelGranja panel = new PanelGranja(this);
                      Thread hilo = new Thread(panel);
                      hilo.start();
                      FramePrograma.ponerPanelEnFramePrincipal(panel);
           }

           public int generarPrecioCompraSuelo() {
                      int valor = random.nextInt(MAXIMO_PRECIO - MINIMO_PRECIO + 1) + MINIMO_PRECIO;
                      return valor;
           }

           public void agregarAlimentoGenerado() {
                      alimentosGenerados++;
           }

           // se genera un nuevo suelo en la casilla donde el jugador eligio
           public void generarUnNuevoSuelo(int[] posicion) {
                      int probabilidad = random.nextInt(100);
                      if (probabilidad >= 0 && probabilidad <= 39) {
                                 suelos[posicion[0]][posicion[1]] = new SueloGrama(posicion, this);
                      } else if (probabilidad > 39 && probabilidad <= 74) {
                                 suelos[posicion[0]][posicion[1]] = new SueloAgua(posicion, this);
                      } else {
                                 suelos[posicion[0]][posicion[1]] = new SueloDesierto(posicion, this);
                      }
                      if (!haySuelosEnVenta()) {
                                 columnas += 1;
                                 aumentarMatriz();
                      }
                      mostrarPanel();
           }

           public void detenerTodo() {
                      controladorHilos.detenerHiloPlantas();
                      controladorHilos.detenerHilosParcelas();
                      controladorHilos.controlarHilosPesca();
           }

           public void continuarTodo() {
                      controladorHilos.continuarHiloPlantas();
                      controladorHilos.continuarHilosParcelas();
                      controladorHilos.controlarHilosPesca();
           }

           public void aumentarMatriz() {
                      Suelo[][] nuevaMatriz = new Suelo[filas][columnas];
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas - 1; j++) {
                                            nuevaMatriz[i][j] = suelos[i][j];
                                 }
                      }
                      suelos = nuevaMatriz;
           }

           // metodo que se encarga de veridicar que hayan suelos en venta(null)
           public boolean haySuelosEnVenta() {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] == null) {
                                                       return true;
                                            }
                                 }
                      }
                      return false;
           }

           private void crearMatrizDeSuelos() {
                      suelos = new Suelo[filas][columnas];
                      int probabilidad;
                      int[] posicion;
                      for (int i = 0; i < filas - 1; i++) {
                                 for (int j = 0; j < columnas - 1; j++) {
                                            probabilidad = random.nextInt(100);
                                            posicion = new int[]{i, j};
                                            if (probabilidad >= 0 && probabilidad <= 39) {
                                                       suelos[i][j] = new SueloGrama(posicion, this);
                                            } else if (probabilidad > 39 && probabilidad <= 74) {
                                                       suelos[i][j] = new SueloAgua(posicion, this);
                                            } else {
                                                       suelos[i][j] = new SueloDesierto(posicion, this);
                                            }
                                 }
                      }

           }

           private void inicializarGranja() {
                      random = new Random();
                      filas = 6;
                      columnas = 6;
                      crearMatrizDeSuelos();
                      parcelas = new ListaEnlazadaGenerica();
                      bodega = new Bodega();
                      colaCosechas = new ColaCosecha(this);
                      controladorHilos = new ControladorDeHilosGranja(this);

           }

           public void agregarOroGenerado(int cantidad) {
                      oroGenerado += cantidad;
           }

           public void reiniciarEstadoSuelo() {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.getPanelDisponibilidad().isFueSeleccionado()) {
                                                                  suelo.getPanelDisponibilidad().setFueSeleccionado(false);
                                                                  suelo.setEstaOcupado(false);
                                                       }
                                            }
                                 }

                      }
           }

           public boolean haySueloSeleccionado() {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.isSeleccionada()) {
                                                                  return true;
                                                       }
                                            }
                                 }

                      }
                      return false;
           }

           public int calcularPrecioLimpieza() {
                      int precio = 0;
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.isEstaSucio()) {
                                                                  precio += PRECIO_LIMPIEZA_CASILLA;
                                                       }
                                            }
                                 }

                      }
                      return precio;
           }

           /**
            *
            * @param productos
            */
           public void agregarCosechasABodega(Producto[] productos) {
                      for (int i = 0; i < productos.length; i++) {
                                 bodega.getProductosGuardados().agregarElemento(productos[i]);
                      }
           }

           public void limpiarTerreno() {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.isEstaSucio()) {
                                                                  suelo.setEstaSucio(false);
                                                                  suelo.setYaTieneFertilizante(false);
                                                                  suelo.setEstaOcupado(false);
                                                                  //suelo.cambiarIconoAPanel(0);
                                                                  suelo.getPanel().actualizarIcono(0);
                                                                  suelo.setCalidadDelSuelo(0);
                                                                  suelo.setPlanta(null);
                                                       }
                                            }
                                 }

                      }
           }

//metodo que se encarga de verificar que todas las celdas seleccionadas sean adyacentes a la primera encontrada
           public boolean seleccionoCeldasCorrectas() {
                      celdasSeleccionadas = 0;
                      boolean yaSeEncontro = false;
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.getPanelDisponibilidad().isFueSeleccionado()) {
                                                                  if (!yaSeEncontro) {
                                                                             suelo.setYaFueRevisado(true);
                                                                             buscarCeldasAdyacentesSeleccionadas(suelo);
                                                                             yaSeEncontro = true;
                                                                             celdasSeleccionadas++;
                                                                  } else if (!suelo.isYaFueRevisado()) {
                                                                             return false;
                                                                  }
                                                       }
                                            }
                                 }
                      }
                      return yaSeEncontro;
           }

           /**
            * metodo que se encarga de encontrar todas las celdas adyacentes
            *
            * @param suelo
            */
           public void buscarCeldasAdyacentesSeleccionadas(SueloGrama suelo) {
                      int fila = suelo.getPosicion()[0];
                      int filaArriba = fila - 1;
                      int filaAbajo = fila + 1;
                      int columna = suelo.getPosicion()[1];
                      int columnaAntes = columna - 1;
                      int columnaDespues = columna + 1;
                      revisarAydacente(filaArriba, columnaAntes);
                      revisarAydacente(filaArriba, columna);
                      revisarAydacente(filaArriba, columnaDespues);
                      revisarAydacente(fila, columnaAntes);
                      revisarAydacente(fila, columnaDespues);
                      revisarAydacente(filaAbajo, columnaAntes);
                      revisarAydacente(filaAbajo, columna);
                      revisarAydacente(filaAbajo, columnaDespues);
           }

           /**
            * metodo que se encarga de revisar si un suelo fue seleccionado por el
            * granjero como parte de la parcela
            *
            * @param fila
            * @param columna
            */
           private void revisarAydacente(int fila, int columna) {
                      if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
                                 if (suelos[fila][columna] instanceof SueloGrama) {
                                            SueloGrama adyacente = (SueloGrama) suelos[fila][columna];
                                            if (adyacente.getPanelDisponibilidad().isFueSeleccionado() && !adyacente.isYaFueRevisado()) {
                                                       adyacente.setYaFueRevisado(true);
                                                       celdasSeleccionadas++;
                                                       buscarCeldasAdyacentesSeleccionadas(adyacente);
                                            }
                                 }
                      }
           }

           public void reiniciarEstadoSuelosGrama() {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.getPanelDisponibilidad().isFueSeleccionado()) {
                                                                  suelo.setSeleccionada(false);
                                                                  suelo.setYaFueRevisado(false);
                                                                  suelo.getPanelDisponibilidad().setFueSeleccionado(false);
                                                                  suelo.getPanelDisponibilidad().inicilizarFondoYLabelInfo();
                                                       }
                                            }
                                 }
                      }
           }

           /**
            *
            * @param parcela
            */
           public void añadirSuelosAParcela(Parcela parcela) {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.getPanelDisponibilidad().isFueSeleccionado() && !suelo.isEstaOcupado()) {
                                                                  parcela.getSuelos().agregarElemento(suelo);
                                                                  parcela.agregarCapacidad();
                                                                  suelo.setEstaOcupado(true);
                                                                  suelo.setSeleccionada(false);
                                                                  suelo.setYaFueRevisado(false);
                                                                  suelo.getPanelDisponibilidad().setFueSeleccionado(false);
                                                                  suelo.getPanel().actualizarIcono(4);
                                                       }
                                            }
                                 }
                      }
           }

           // metodo que quita las celdas seleccionadas cuando el granjero selecciona celdas pero no inicia siembra o no crea parcela
           public void quitarSeleccionadoASuelos() {
                      for (int i = 0; i < filas; i++) {
                                 for (int j = 0; j < columnas; j++) {
                                            if (suelos[i][j] instanceof SueloGrama) {
                                                       SueloGrama suelo = (SueloGrama) suelos[i][j];
                                                       if (suelo.isSeleccionada()) {
                                                                  suelo.getPanelDisponibilidad().setFueSeleccionado(false);
                                                                  suelo.setSeleccionada(false);
                                                       }
                                            }
                                 }
                      }
           }

           public boolean hayAlimentosEnBodega() {
                      for (int i = 0; i < bodega.getProductosGuardados().getTamaño(); i++) {
                                 if (bodega.getProductosGuardados().obtenerContenido(i) instanceof Alimento) {
                                            return true;
                                 }
                      }
                      return false;
           }

           public ListaEnlazadaGenerica<Parcela> getParcelas() {
                      return parcelas;
           }

           public void setParcelas(ListaEnlazadaGenerica<Parcela> parcelas) {
                      this.parcelas = parcelas;
           }

           public int getFilas() {
                      return filas;
           }

           public void setFilas(int filas) {
                      this.filas = filas;
           }

           public int getColumnas() {
                      return columnas;
           }

           public void setColumnas(int columnas) {
                      this.columnas = columnas;
           }

           public Suelo[][] getSuelos() {
                      return suelos;
           }

           public Granjero getGranjero() {
                      return granjero;
           }

           public Bodega getBodega() {
                      return bodega;
           }

           public ColaCosecha getColaCosechas() {
                      return colaCosechas;
           }

           public void setColaCosechas(ColaCosecha colaCosechas) {
                      this.colaCosechas = colaCosechas;
           }

           public int getCeldasSeleccionadas() {
                      return celdasSeleccionadas;
           }

           public void setCeldasSeleccionadas(int celdasSeleccionadas) {
                      this.celdasSeleccionadas = celdasSeleccionadas;
           }

           public Partida getPartida() {
                      return partida;
           }

           public void setPartida(Partida partida) {
                      this.partida = partida;
           }

           public boolean isActualizarPanelGranja() {
                      return actualizarPanelGranja;
           }

           public void setActualizarPanelGranja(boolean actualizarPanelGranja) {
                      this.actualizarPanelGranja = actualizarPanelGranja;
           }

           public int getOroGenerado() {
                      return oroGenerado;
           }

           public void setOroGenerado(int oroGenerado) {
                      this.oroGenerado = oroGenerado;
           }

           public static int getPRECIO_ELIMINACION_PARCELA() {
                      return PRECIO_ELIMINACION_PARCELA;
           }

           public static int getPRECIO_LIMPIEZA_CASILLA() {
                      return PRECIO_LIMPIEZA_CASILLA;
           }

           public int getCeldasCompradas() {
                      return celdasCompradas;
           }

           public void setCeldasCompradas(int celdasCompradas) {
                      this.celdasCompradas = celdasCompradas;
           }

           public int getAlimentosGenerados() {
                      return alimentosGenerados;
           }

           public void setAlimentosGenerados(int alimentosGenerados) {
                      this.alimentosGenerados = alimentosGenerados;
           }

           public ControladorDeHilosGranja getControladorHilos() {
                      return controladorHilos;
           }

           public void setControladorHilos(ControladorDeHilosGranja controladorHilos) {
                      this.controladorHilos = controladorHilos;
           }
}
