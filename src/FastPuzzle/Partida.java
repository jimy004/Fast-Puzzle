/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FastPuzzle;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Partida
        implements Serializable {

    private final String Jugador;
    private final String Fecha;
    private String directorio;
    private final String imagen;
    private int Puntuacion, divhor, divver;

    public Partida() {
        Jugador = null;
        Puntuacion = 0;
        directorio = "imagenes";
        Fecha = null;
        imagen = elegirimagen();

    }

    public Partida(String jugador, String directorio, int h, int v) throws Exception{
        Jugador = jugador;
        this.directorio = directorio;
        Puntuacion = h * v;
        divhor = h;
        divver = v;
        Fecha = fecha();
        imagen = elegirimagen();
        Imagenes ficheroimagenes = new Imagenes(imagen, divhor, divver);
        ficheroimagenes.divideimagen();
       
    }

    public String fecha() {
        String s;
        Date fechaActual = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaActual);
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH) + 1; // Los meses comienzan desde 0
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);

        return s = hora + ":" + minutos + " " + dia + "/" + mes + "/" + año;

    }

    private String elegirimagen() {
        File folder = new File(directorio);
        File[] imageFiles = folder.listFiles();
        String Imatge = null;
       
        if (imageFiles != null && imageFiles.length > 0) {
            // Seleccionar una imagen aleatoria
            Random random = new Random();
            File randomImage = imageFiles[random.nextInt(imageFiles.length)];
            Imatge = randomImage.getPath();
        } else {
        }
        return Imatge;
    }

    @Override
    public String toString() {
        String s = "JUGADOR: " + Jugador + "- FECHA: " + Fecha + "   - PUNTOS: " + Puntuacion + " puntos.";
        return s;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public void setPuntuacion(int Puntuacion) {
        this.Puntuacion = Puntuacion;
    }

    public int getPuntuacion() {
        return Puntuacion;
    }

    public int getDivhor() {
        return divhor;
    }

    public int getDivver() {
        return divver;
    }

    public String getImagen() {
        return imagen;
    }

    public String getJugador() {
        return Jugador;
    }

    public String getDirectorio() {
        return directorio;
    }

}
