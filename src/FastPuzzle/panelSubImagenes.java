/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FastPuzzle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author jaume
 */
public class panelSubImagenes extends JPanel {

    private final SubImagen[] paneles;
    private final SubImagen[] solucion;
    private final int numerodepaneles;
    private final int numDivisionsX;
    private final int numDivisionsY;
    private SubImagen panelSeleccionado;
    private Boolean solucio=false;

    public panelSubImagenes(int h, int v) {
        super();

        numDivisionsX = h;
        numDivisionsY = v;
        numerodepaneles = h * v;

        setBackground(Color.black);
        setLayout(new GridLayout(numDivisionsY, numDivisionsX, 2, 2));
        paneles = new SubImagen[numerodepaneles];
        solucion = new SubImagen[numerodepaneles];
        setSize(700, 460);
        inicializacion();

    }

    private void inicializacion() {
        int index = 0;
        for (int y = 0; y < numDivisionsY; y++) {
            for (int x = 0; x < numDivisionsX; x++) {
                paneles[index] = new SubImagen("subimagenes/subimatge_" + y + "_" + x + ".jpg");
                solucion[index] = new SubImagen("subimagenes/subimatge_" + y + "_" + x + ".jpg");
                paneles[index].addMouseListener(eventosRaton());
                add(paneles[index]);
                add(solucion[index]);
                index++;
            }
        }

        desordenar();

    }

    private void desordenar() {
        Random random = new Random();

        for (int i = paneles.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            SubImagen temp = paneles[i];
            paneles[i] = paneles[j];
            paneles[j] = temp;
        }

        removeAll(); // Elimina todos los paneles del contenedor

        for (SubImagen panel : paneles) {
            add(panel); // Agrega los paneles actualizados al contenedor
        }

        revalidate(); // Vuelve a validar la disposición del contenedor
        repaint(); // Vuelve a pintar el contenedor para reflejar los cambios
    }

    private void intercambiarPaneles(SubImagen panel1, SubImagen panel2) {
        int indicePanel1 = -1;
        int indicePanel2 = -1;

        for (int i = 0; i < paneles.length; i++) {
            if (paneles[i] == panel1) {
                indicePanel1 = i;
            } else if (paneles[i] == panel2) {
                indicePanel2 = i;
            }
        }

        if (indicePanel1 != -1 && indicePanel2 != -1) {
            SubImagen temp = paneles[indicePanel1];
            paneles[indicePanel1] = paneles[indicePanel2];
            paneles[indicePanel2] = temp;

            removeAll(); // Elimina todos los paneles del contenedor

            for (SubImagen panel : paneles) {
                add(panel); // Agrega los paneles actualizados al contenedor
            }

            revalidate(); // Vuelve a validar la disposición del contenedor
            repaint(); // Vuelve a pintar el contenedor para reflejar los cambios
        }
    }

    private void comprobarsolucion() {
        boolean solucionCorrecta = true;

        for (int i = 0; i < paneles.length; i++) {
            if (!paneles[i].getNombre().equals(solucion[i].getNombre())) {
                solucionCorrecta = false;
                break;
            }
        }

        if (solucionCorrecta) {
            solucio=true;  
        } 
    }

    public Boolean getSolucio() {
        return solucio;
    }

    public MouseListener eventosRaton() {
        MouseListener accion = new MouseListener() {
            @Override

            public void mousePressed(MouseEvent evento) {
                SubImagen panelActual = (SubImagen) evento.getSource();
                if (panelSeleccionado == null) {
                    panelSeleccionado = panelActual;
                    panelSeleccionado.seleccionarPanel();
                } else {
                    intercambiarPaneles(panelSeleccionado, panelActual);
                    panelSeleccionado.deseleccionarPanel();
                    panelSeleccionado = null;
                    comprobarsolucion();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        return accion;
    }
}
