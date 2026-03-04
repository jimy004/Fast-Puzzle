/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FastPuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SubImagen extends JPanel {
    private final String nombre;
    private final Image imagen;
    private boolean seleccionada;
    private final Border bordeSeleccionado;

    public SubImagen(String nombreImagen) {
        this.nombre=nombreImagen;
        this.imagen = cargarImagen(nombreImagen);
        this.seleccionada = false;
        this.bordeSeleccionado = BorderFactory.createLineBorder(Color.RED, 2);
    }

    private Image cargarImagen(String nombreImagen) {
        try {
            return ImageIO.read(new File(nombreImagen));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void seleccionarPanel() {
        seleccionada = true;
        setBorder(bordeSeleccionado);
    }

    public void deseleccionarPanel() {
        seleccionada = false;
        setBorder(null);
    }

    public boolean estaSeleccionada() {
        return seleccionada;
    }

    public Image getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }   
}