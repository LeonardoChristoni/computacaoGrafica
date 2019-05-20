package computacaoGrafica;

import java.util.Scanner;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class TransformacaoSorriso {

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        double count[] = new double[256];
        String path_dir = "/home/joaovkn/Imagens/CG/";
        String input_file = "banana2";
        final String PNG = ".png";
        Mat original = Imgcodecs.imread(path_dir + input_file + PNG);
        double width = original.width();
        double height = original.height();
        //System.out.println("Digite o angulo para rotacionar:");
        //int angulo = sc.nextInt();
        double angle = Math.toRadians(190);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double x0 = 0.5 * (width  - 1);
        double y0 = 0.5 * (height - 1);
        //Rotacao
        Mat roted = new Mat(original.rows() , original.cols() , original.type());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double a = x - x0;
                double b = y - y0;
                int xlinha = (int) ((a * cos) - (b * sin) + x0);
                int ylinha = (int) ((a * sin) + (b * cos) + y0);
                // Permite atribuir valor na matriz caso esteja respeitando a seguinte condicao, sem extrapolar.
                if (xlinha >= 0 && xlinha < width && ylinha >= 0 && ylinha < height) {
                    roted.put(x, y, original.get(xlinha, ylinha));
                }
            }
        }
        Imgcodecs.imwrite(path_dir + input_file + "_rotacionada" + PNG, roted);
        
        
        
        
        
        
        //Translacao
        int deltax = 20;
        int deltay = 30;
        Mat transled = new Mat(original.rows() , original.cols() , original.type());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double pixel[] = new double[3];
                pixel [0] = 255;
                pixel [1] = 255;
                pixel [2] = 255;
                //Inicia a matriz vazia
                    transled.put(x, y, pixel);
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int xlinha = x + deltax;
                int ylinha = y + deltay;
                // Permite atribuir valor na matriz caso esteja respeitando a seguinte condicao, sem extrapolar.
                if (xlinha >= 0 && xlinha < width && ylinha >= 0 && ylinha < height) {
                    transled.put(x, y, original.get(xlinha, ylinha));
                }
            }
        }
        Imgcodecs.imwrite(path_dir + input_file + "_transladada" + PNG, transled);
        System.out.println("Image Processed");
    }
}