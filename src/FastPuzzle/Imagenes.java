/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FastPuzzle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Imagenes {

    private final String direccionimagen;
    private final int SubdivH;
    private final int SubdivV;

    public Imagenes(String direccion, int SubdivH, int SubdivV) {
        this.direccionimagen = direccion;
        this.SubdivH = SubdivH;
        this.SubdivV = SubdivV;
    }

    public void divideimagen() throws Exception {
        
            // Carga la imagen original
            BufferedImage ImagenOriginal = ImageIO.read(new File(direccionimagen));

            // Calcula el tamaño de las subdivisiones
            int Ancho = ImagenOriginal.getWidth() / SubdivH;
            int Altura = ImagenOriginal.getHeight() / SubdivV;

            // Crea la carpeta para las subimágenes
            File outputFolder = new File("subimagenes");
            outputFolder.mkdirs();

            String rutacarpeta = outputFolder.getPath();
            BorraCarpeta(rutacarpeta);

            // Crea las subimágenes
            for (int i = 0; i < SubdivV; i++) {
                for (int j = 0; j < SubdivH; j++) {
                    // Calcular las coordenadas de recorte
                    int x = j * Ancho;
                    int y = i * Altura;

                    // Recorta la subimagen
                    BufferedImage subImage = ImagenOriginal.getSubimage(x, y, Ancho, Altura);

                    // Guarda la subimagen en un archivo
                    File outputFile = new File(outputFolder, "subimatge_" + i + "_" + j + ".jpg");
                    ImageIO.write(subImage, "jpg", outputFile);
                }
            }
        
            
        
    }

    public static void BorraCarpeta(String ruta) {
        File folder = new File(ruta);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("La ruta especificada no es una carpeta válida.");
            return;
        }

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursivamente elimina el contenido de los subdirectorios
                    BorraCarpeta(file.getPath());
                } else {
                    // Elimina archivo
                    file.delete();
                }
            }
        }
    }
}
