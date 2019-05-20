//ALTERAR PACKAGE
package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MediaMedianaColorida {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dst = null;
		
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA	
		String caminhoImagem="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\einstein.png";
		
		src = Imgcodecs.imread(caminhoImagem);
		
		dst = new Mat(src.rows(),src.cols(),src.type());
		Convolucao.Media(src, dst, "");

		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\einsteinMedia.png", dst);
		System.out.println("Image Media- Processada!");
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\einsteinMediana.png", dst);
		System.out.println("Image Mediana- Processada!");
	}

}
