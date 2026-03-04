/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FastPuzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FicheroPartidas {

    private String ruta;
    private Boolean esta;

    public FicheroPartidas(String ruta) {
        this.ruta = ruta;
    }

    public void escribirPartida(Partida partida) {
        try {
            List<Partida> partidas = leerPartidas();
            partidas.add(partida);

            FileOutputStream fos = new FileOutputStream(ruta);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Partida p : partidas) {
                oos.writeObject(p);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Partida> leerPartidas() {
        List<Partida> partidas = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                Partida partida = (Partida) ois.readObject();
                partidas.add(partida);
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // No hacer nada si el archivo no existe o no contiene objetos serializados válidos
        }
        return partidas;
    }
    
  public List<Partida> buscarnombre(String s){
  List<Partida> partidas = new ArrayList<>();
  esta=false;
    try {
            FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (fis.available() > 0) {
                Partida partida = (Partida) ois.readObject();
                if(s.equals(partida.getJugador())){
                esta=true;
                partidas.add(partida);
                }
            }
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // No hacer nada si el archivo no existe o no contiene objetos serializados válidos
        }
  return partidas;
  }

    public Boolean getEsta() {
        return esta;
    }


}
