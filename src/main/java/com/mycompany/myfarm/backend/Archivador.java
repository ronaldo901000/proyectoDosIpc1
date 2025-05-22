/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myfarm.backend;

import com.mycompany.myfarm.backend.listaEnlazada.ListaEnlazadaGenerica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author ronaldo
 */
public class Archivador {

    public static final String PATH_ARCHIVO = "Reportes.bin";

    /**
     * 
     * @param reporte 
     */
    public void guardarReporte(Reporte reporte) {
        File file = new File(PATH_ARCHIVO);
        try (FileOutputStream out = new FileOutputStream(file); ObjectOutputStream objectStream
                = new ObjectOutputStream(out)) {
            objectStream.writeObject(reporte);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Reporte devolverReporte() {
        File file = new File(PATH_ARCHIVO);
        if (!file.exists()) {
            return null;
        }
        try (FileInputStream input = new FileInputStream(file); ObjectInputStream objStream
                = new ObjectInputStream(input)) {
            Reporte reporte = (Reporte) objStream.readObject();
            return reporte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/**
 * 
 * @param partidas
 * @param nombreArchivo 
 */
    public void escribirTablaReportesPartidasHtml(ListaEnlazadaGenerica<ResumenPartida> partidas, String nombreArchivo) {
        File miArchivo = new File(nombreArchivo + ".html");
        try (FileWriter writer = new FileWriter(miArchivo)) {
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<table>\n");
            writer.write("<thead>\n");
            writer.write("<tr>\n");
            writer.write("<th>| Nombre |</th>\n");
            writer.write("<th>Duracion |</th>\n");
            writer.write("<th> Oro Generado |</th>\n");
            writer.write("<th> Alimento Generado |</th>\n");
            writer.write("<th> Alimento Consumido |</th>\n");
            writer.write("<th> Celdas compradas |</th>\n");
            writer.write("</tr>\n");
            writer.write("</thead>\n");
            writer.write("<tbody>\n");

            for (int i = 0; i < partidas.getTamaño(); i++) {
                ResumenPartida partida = partidas.obtenerContenido(i);
                writer.write("<tr>\n");
                writer.write("<td>" + partida.getNombrePartida() + "</td>\n");
                writer.write("<td>" + partida.getDuracionPartida() + "</td>\n");
                writer.write("<td>" + partida.getOroGeneradoGranja() + "</td>\n");
                writer.write("<td>" + partida.getAlimentosGenerados() + "</td>\n");
                writer.write("<td>" + partida.getProductosConsumidos() + "</td>\n");
                writer.write("<td>" + partida.getCantidadDeCeldasCompradas() + "</td>\n");
                writer.write("</tr>\n");
            }
            writer.write("</tbody>\n");
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param animales
     * @param nombreArchivo 
     */
    public void escribirTablaReportesAnimalesHtml(ListaEnlazadaGenerica<ResumenAnimal> animales, String nombreArchivo) {
        File archivo = new File(nombreArchivo + ".html");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<table>\n");
            writer.write("<thead>\n");
            writer.write("<tr>\n");
            writer.write("<th>Nombre</th>\n");
            writer.write("<th>CantidadCrias</th>\n");
            writer.write("<th>Destaces</th>\n");
            writer.write("<tbody>\n");
            for (int i = 0; i < animales.getTamaño(); i++) {
                ResumenAnimal animal = animales.obtenerContenido(i);
                writer.write("<tr>\n");
                writer.write("<td>" + animal.getNombrePlanta() + "</td>\n");
                writer.write("<td>" + animal.getCantidadCriasVendidas() + "</td>\n");
                writer.write("<td>" + animal.getCantidadUnidadesDestazada() + "</td>\n");
                writer.write("</tr>\n");
            }
            writer.write("</tbody>\n");
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param plantas
     * @param nombreArchivo 
     */
    public void escribirTablaRepotePlantasHtml(ListaEnlazadaGenerica<ResumenPlanta> plantas, String nombreArchivo) {
        File archivo = new File(nombreArchivo + ".html");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("<html>\n");
            writer.write("<body>\n");
            writer.write("<table>\n");
            writer.write("<thead>\n");
            writer.write("<tr>\n");
            writer.write("<th>Nombre</th>\n");
            writer.write("<th>CantidadCrias</th>\n");
            writer.write("<th>Destaces</th>\n");
            writer.write("<tbody>\n");
            for (int i = 0; i < plantas.getTamaño(); i++) {
                ResumenPlanta planta = plantas.obtenerContenido(i);
                writer.write("<tr>\n");
                writer.write("<td>" + planta.getNombre() + "</td>\n");
                writer.write("<td>" + planta.getTotalSemillasAdquiridas() + "</td>\n");
                writer.write("<td>" + planta.getCeldasSembradas() + "</td>\n");
                writer.write("</tr>\n");
            }
            writer.write("</tbody>\n");
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
