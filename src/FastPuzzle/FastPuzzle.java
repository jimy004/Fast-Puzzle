/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FastPuzzle;

import java.awt.*;
import static java.awt.Color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author jaume
 */
public class FastPuzzle {

    private JFrame ventana;
    private Container panelContenidos;
    private JPanel panelVisualizaciones, panelBotones;
    private JToolBar iconosMenu;
    private JMenuBar barraMenu;
    private Partida Partida;
    private Barra_Temporal barraTemporal;
    private panelSubImagenes panelImagenes;
    private Timer timer;
    private FicheroPartidas fitx = new FicheroPartidas("resultados.dat");

    public static void main(String[] args) {
        new FastPuzzle().metodoprincipal();
    }

    private void metodoprincipal() {
        ventana = new JFrame();
        ventana.setTitle("FAST-PUZZLE");
        panelContenidos = ventana.getContentPane();
        inicializa();
    }

    private void inicializa() {

        ////////////////////////////////////////////////////////////////////////////////
        //                             Menu y componentes                              //
        ////////////////////////////////////////////////////////////////////////////////
        barraMenu = new JMenuBar();
        JMenu generalMenu = new JMenu("MENU");
        String[] COMPONENTES0 = {"NUEVA PARTIDA", "CLASIFICACIÓN GENERAL", "HISTORIAL", "CAMBIAR DIRECTORIO DE IMAGENES", "SALIR"};

        for (int i = 0; i < COMPONENTES0.length; i++) {
            JMenuItem componente = new JMenuItem(COMPONENTES0[i]);
            componente.setForeground(white);
            componente.setBackground(black);
            componente.addActionListener(manipuladorEventos);
            generalMenu.add(componente);
        }

        generalMenu.setForeground(white);
        barraMenu.setBackground(black);
        generalMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        barraMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        barraMenu.add(generalMenu);
        ////////////////////////////////////////////////////////////////////////////////
        //                            ToolBar y componentes                            //
        ////////////////////////////////////////////////////////////////////////////////    
        iconosMenu = new JToolBar();
        String[] COMPONENTES1 = {"iconos/iconoNuevaPartida.jpg", "iconos/iconoHistorialGeneral.jpg",
            "iconos/iconoHistorialSelectivo.jpg", "iconos/iconoCambiarDIrectorio.jpg", "iconos/iconoSalir.jpg"};

        for (int i = 0; i < COMPONENTES1.length; i++) {
            JButton icono = new JButton(new ImageIcon(COMPONENTES1[i]));
            icono.setIcon(new ImageIcon(COMPONENTES1[i]));
            icono.setBackground(black);
            icono.setActionCommand(COMPONENTES0[i]);
            icono.addActionListener(manipuladorEventos);
            iconosMenu.add(icono);
            iconosMenu.setBackground(black);
            iconosMenu.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        }
        ////////////////////////////////////////////////////////////////////////////////
        //                       panelBotones y componentes                            //
        ////////////////////////////////////////////////////////////////////////////////    
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1));
        String[] COMPONENTES2 = {"NUEVA PARTIDA", "CLASIFICACIÓN GENERAL", "HISTORIAL", "SALIR"};

        for (int i = 0; i < COMPONENTES2.length; i++) {
            JButton boton = new JButton(COMPONENTES2[i]);
            boton.setForeground(white);
            boton.setBackground(black);
            boton.addActionListener(manipuladorEventos);
            panelBotones.add(boton);
        }

        ////////////////////////////////////////////////////////////////////////////////
        //                        panelStandby i components                          //
        ////////////////////////////////////////////////////////////////////////////////  
        panelVisualizaciones = new JPanel();
        panelVisualizaciones.setLayout(new CardLayout()); //utilitzam el CardLayout per poder alternar el panell amb els altres botons.
        JPanel panelStandby = new JPanel();
        panelStandby.setSize(700, 460);
        panelStandby.setBackground(BLACK);
        ImageIcon imagenIcono = new ImageIcon("UIB.jpg");
        Image imagen = imagenIcono.getImage();
        JLabel UIB = new JLabel(); //cream l'etiqueta que conté la imatge i l'escalam a les mesures adecuades a la linia següent.
        UIB.setIcon(new ImageIcon(imagen.getScaledInstance(panelStandby.getWidth(), panelStandby.getHeight(), Image.SCALE_SMOOTH)));
        panelStandby.add(UIB, BorderLayout.CENTER);  // ho afegim al panell.
        panelVisualizaciones.add(panelStandby, "panellStandby");
        ////////////////////////////////////////////////////////////////////////////////
        //                       JSplitPane y componentes                              //
        //////////////////////////////////////////////////////////////////////////////// 
        JPanel arriba = new JPanel(new GridLayout(2, 1));
        arriba.add(barraMenu);
        arriba.add(iconosMenu);
        panelContenidos.add(arriba, BorderLayout.NORTH);

        JSplitPane separador1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane separador2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane separador3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        separador1.add(panelBotones);
        separador1.add(panelVisualizaciones);

        separador2.add(arriba);
        separador2.add(separador1);

        separador3.add(separador1);
        separador3.add(separador1);

        panelContenidos.add(separador1, BorderLayout.CENTER);
        panelContenidos.add(separador2, BorderLayout.NORTH);
        panelContenidos.add(separador3, BorderLayout.PAGE_END);

        /////////////////////////////////////////////////////////////////////////////////
        UIDefaults lookAndFeelInfo = UIManager.getLookAndFeelDefaults();
        lookAndFeelInfo.put("OptionPane.background", Color.BLACK);
        lookAndFeelInfo.put("Panel.background", Color.BLACK);
        lookAndFeelInfo.put("OptionPane.messageForeground", Color.YELLOW);

        
        ventana.setSize(900, 600);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        Partida=new Partida();

    }

    private void historial(JTextArea text) {
        ////////////////////////////////////////////////////////////////////////////////
        //                        panelHistorial y componentes                         //
        //////////////////////////////////////////////////////////////////////////////// 
        JPanel panelHistorial = new JPanel();
        Font font = new Font("monospaced", Font.BOLD, 15);
        text.setFont(font);
        text.setEditable(false);
        panelHistorial.setBackground(white);
        panelHistorial.add(text, new GridLayout(1, 1));
        panelVisualizaciones.add(panelHistorial, "panelHistorial");
        CardLayout cardLayout = (CardLayout) panelVisualizaciones.getLayout();
        cardLayout.show(panelVisualizaciones, "panelHistorial");

        panelVisualizaciones.revalidate();
        panelVisualizaciones.repaint();
    }

    private void nuevapartidamensaje() {
        JDialog npartida = new JDialog();
        npartida.setTitle("INTRODUCCIÓN DATOS");
        Container panelContenidos = npartida.getContentPane();
        panelContenidos.setLayout(new BorderLayout());
        JPanel info = new JPanel();
        info.setLayout(new GridLayout(3, 2));
        info.setBackground(black);

        JLabel nombre = new JLabel("NOMBRE JUGADOR");
        nombre.setForeground(white);
        JLabel subHor = new JLabel("NÚMERO DE SUBDIVISIONES VERTICALES");
        subHor.setForeground(white);
        JLabel subVer = new JLabel("NÚMERO DE SUBDIVISIONES HORIZONTALES");
        subVer.setForeground(white);

        JTextField Anom = new JTextField();
        JTextField Ahor = new JTextField();
        JTextField Aver = new JTextField();

        info.add(nombre);
        info.add(Anom);
        info.add(subHor);
        info.add(Ahor);
        info.add(subVer);
        info.add(Aver);

        JButton enviar = new JButton("ENVIAR");

        panelContenidos.add(info, BorderLayout.CENTER);
        panelContenidos.add(enviar, BorderLayout.SOUTH);

        npartida.setBounds(0, 0, 600, 150);
        npartida.setLocationRelativeTo(panelVisualizaciones);
        npartida.setVisible(true);

        enviar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los JTextField

                if ((Anom.getText().isEmpty()) || (Ahor.getText().isEmpty()) || (Aver.getText().isEmpty())) {
                    System.out.println("Campos vacios");
                } else if (Anom.getText().toCharArray().length > 15) {
                    System.out.println("Jugador de com a maxim 15 caracters");
                } else {
                    try {
                        String jugador = Anom.getText();
                        while (jugador.length() <= 15) {
                            jugador += " ";
                        }
                        int divisionshorizontals = Integer.parseInt(Ahor.getText());
                        int divisionsverticals = Integer.parseInt(Aver.getText());
                        if (divisionshorizontals*divisionsverticals<=30){
                        try {
                            Partida = new Partida(jugador, Partida.getDirectorio(), divisionshorizontals, divisionsverticals);
                            JPanel panelPartida = new JPanel();
                        barraTemporal = new Barra_Temporal();
                        panelImagenes = new panelSubImagenes(Partida.getDivhor(), Partida.getDivver());
                        panelPartida.setLayout(new BorderLayout());
                        panelPartida.add(panelImagenes, BorderLayout.CENTER);
                        panelPartida.add(barraTemporal, BorderLayout.SOUTH);

                        panelVisualizaciones.add(panelPartida, "panelPartida");
                        CardLayout cardLayout = (CardLayout) panelVisualizaciones.getLayout();
                        cardLayout.show(panelVisualizaciones, "panelPartida");
                        panelVisualizaciones.revalidate();
                        panelVisualizaciones.repaint();
                        npartida.dispose();
                        // Cerrar el JDialog
                        desactivar_activar(0);//desactiva si es 0
                        timer = new Timer(10 * (divisionshorizontals * divisionsverticals) + 100, crono);
                        timer.start();
                        } catch (Exception ex) {
                           
                             JOptionPane.showMessageDialog(null, "Se ha producido un error. Compruebe el directorio.");
                             npartida.dispose();
                        }
                        }else{
                            JOptionPane.showMessageDialog(null, "COMO MÁXIMO EL PUZZLE PUEDE TENER 30 PIEZAS");
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("El campo no contiene un número.");
                    }

                }
            }
        });

    }

    private void desactivar_activar(int n) {
    Component[] componentes = panelBotones.getComponents();
    Component[] componentes2 = iconosMenu.getComponents();

    for (Component componente : componentes) {
        if (componente.getClass().equals(JButton.class)) {
            JButton boton = (JButton) componente;
            ActionListener[] actionListeners = boton.getActionListeners();
            for (ActionListener actionListener : actionListeners) {
                if ("SALIR".equals(boton.getActionCommand())) {
                } else if (n == 0) {
                    boton.removeActionListener(actionListener);
                    boton.addActionListener(partidaencurso);
                    barraMenu.getMenu(0).setEnabled(false);
                } else {
                    boton.removeActionListener(actionListener);
                    boton.addActionListener(manipuladorEventos);
                    barraMenu.getMenu(0).setEnabled(true);
                }
            }
        }
    }
    for (Component componente : componentes2) {
        if (componente.getClass().equals(JButton.class)) {
            JButton boton = (JButton) componente;
            ActionListener[] actionListeners = boton.getActionListeners();
            for (ActionListener actionListener : actionListeners) {
                if ("SALIR".equals(boton.getActionCommand())) {
                } else if (n == 0) {
                    boton.removeActionListener(actionListener);
                    boton.addActionListener(partidaencurso);
                    barraMenu.getMenu(0).setEnabled(false);
                } else {
                    boton.removeActionListener(actionListener);
                    boton.addActionListener(manipuladorEventos);
                    barraMenu.getMenu(0).setEnabled(true);
                }
            }
        }
    }
}


    private String cambiardirectorio() {
      String directorio = null;
    JFileChooser finestraseleccio = new JFileChooser();
    finestraseleccio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int option = finestraseleccio.showOpenDialog(ventana);
    if (option == JFileChooser.APPROVE_OPTION) {
        File selectedFolder = finestraseleccio.getSelectedFile();
        directorio = selectedFolder.getPath();
        System.out.println("Carpeta seleccionada: " + selectedFolder.getPath());

        // Verificar si la carpeta está vacía o no contiene imágenes
        File[] imageFiles = selectedFolder.listFiles();
        if (imageFiles != null && imageFiles.length > 0 && !contieneOtrosElementos(selectedFolder)) {
            System.out.println("La carpeta contiene imágenes.");
        } else {
            JOptionPane.showMessageDialog(null, "La carpeta está vacía o no contiene EXCLUSIVAMENTE imágenes.");  
        }
    }
    return directorio;
    }
    
    private boolean contieneOtrosElementos(File carpeta) {
    File[] archivos = carpeta.listFiles();
    if (archivos != null) {
        for (File archivo : archivos) {
            if (!esImagen(archivo)) {
                return true;  // Se encontró un archivo que no es una imagen
            }
        }
    }
    return false;  // La carpeta solo contiene imágenes
}

private boolean esImagen(File archivo) {
    try {
        // Intentar cargar el archivo como una imagen
        ImageIO.read(archivo);
        return true;  // El archivo es una imagen válida
    } catch (IOException e) {
        return false;  // El archivo no es una imagen
    }
}

    ActionListener manipuladorEventos = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evento) {
            JTextArea areaVisualizacionResultados;

            switch (evento.getActionCommand()) {
                case "NUEVA PARTIDA":
                    nuevapartidamensaje();
                    break;
                case "CLASIFICACIÓN GENERAL":
                    String s = "";
                    List<Partida> lista = new ArrayList<>();
                    lista = fitx.leerPartidas();
                    for (int i = 0; i < lista.size(); i++) {
                        s += lista.get(i).toString() + "\n";
                    }
                    String espacio = "";
                    if (new File("resultados.dat").length() != 0) {
                        for (int i = 0; i < 30; i++) {
                            espacio += " ";
                        }
                    }
                    areaVisualizacionResultados = new JTextArea(espacio + "HISTORIAL\n\n" + s);
                    historial(areaVisualizacionResultados);
                    break;
                case "HISTORIAL":
                    List<Partida> lista2 = new ArrayList<>();
                    String s2 = "";
                    try {
                        s2 = JOptionPane.showInputDialog("HISTORIAL JUGADOR\nINTRODUCIR NOMBRE JUGADOR");
                        while (s2.length() <= 15) {
                            s2 += " ";

                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se ha introducido ningun nombre.");
                        break;
                    }
                    String input = "";
                    lista2 = fitx.buscarnombre(s2);
                    for (int i = 0; i < lista2.size(); i++) {
                        input += lista2.get(i).toString() + "\n";
                    }
                    if (fitx.getEsta()==true){
                    areaVisualizacionResultados = new JTextArea("HISTORIAL\n\n" + input);
                    historial(areaVisualizacionResultados);
                    }else {
                     JOptionPane.showMessageDialog(null, "No se ha encontrado este jugador. Compruebe el nombre");
                    }
                   
                    break;
                case "CAMBIAR DIRECTORIO DE IMAGENES":
                    String directorio = cambiardirectorio();
                    Partida.setDirectorio(directorio);

                    break;
                case "SALIR"://Salir de la aplicación
                    System.exit(0);
                    break;
            }
        }
    };

    ActionListener partidaencurso = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evento) {
            JOptionPane.showMessageDialog(panelVisualizaciones, "ANTES DEBES TERMINAR LA PARTIDA EN CURSO");

        }
    };

    ActionListener crono = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent evt) {

            int valor = barraTemporal.getValorBarraTemporal();
            int valormax = barraTemporal.getValorMaximo();
            boolean show = false;
            if (valor < valormax) {
                //ASIGNAR A JProgressBar barraTemporal SU VALOR INCREMENTADO
                //EN UNA UNIDAD
                barraTemporal.setValue(++valor);
                if (panelImagenes.getSolucio()) {
                    timer.stop();
                    fitx.escribirPartida(Partida);
                    JOptionPane.showMessageDialog(panelVisualizaciones, "¡¡ENHORABUENA!! LO HAS CONSEGUEDIDO HAS OBTENIDO " + Partida.getPuntuacion() + " PUNTOS");
                    show = true;

                }
            } else {
                //DETENER EL TEMPORIZADOR 
                Partida.setPuntuacion(0);
                fitx.escribirPartida(Partida);
                timer.stop();
                JOptionPane.showMessageDialog(panelVisualizaciones, "NO LO HAS CONSEGUIDO - EL TIEMPO HA TERMINADO");
                show = true;

            }

            if (show) {
                ////////////////////////////////////////////////////////////////////////////////
                //                        panelImagenSolucion y componentes                    //
                //////////////////////////////////////////////////////////////////////////////// 
                JPanel panelImagenSolucion = new JPanel(new BorderLayout());
                ImageIcon imagensolucion = new ImageIcon(Partida.getImagen());
                Image imagensol = imagensolucion.getImage();
                JLabel imgsol = new JLabel();
                JButton botonContinuar = new JButton("CONTINUAR");
                imgsol.setIcon(new ImageIcon(imagensol.getScaledInstance(panelVisualizaciones.getWidth(), panelVisualizaciones.getHeight(), Image.SCALE_SMOOTH)));
                botonContinuar.setForeground(white);
                botonContinuar.setBackground(black);
                botonContinuar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cardLayout = (CardLayout) panelVisualizaciones.getLayout();
                        cardLayout.show(panelVisualizaciones, "panellStandby");
                        desactivar_activar(1);
                        panelVisualizaciones.revalidate();
                        panelVisualizaciones.repaint();
                    }

                });
                panelImagenSolucion.add(imgsol, BorderLayout.CENTER);
                panelImagenSolucion.add(botonContinuar, BorderLayout.SOUTH);
                panelVisualizaciones.add(panelImagenSolucion, "panelImagenSolucion");
                CardLayout cardLayout = (CardLayout) panelVisualizaciones.getLayout();
                cardLayout.show(panelVisualizaciones, "panelImagenSolucion");
                panelVisualizaciones.revalidate();
                panelVisualizaciones.repaint();
            }

        }
    };

}
