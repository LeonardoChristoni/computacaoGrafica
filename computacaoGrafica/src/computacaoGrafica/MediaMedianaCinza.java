//ALTERAR PACKAGE
package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MediaMedianaCinza {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dst = null;
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA	
		String caminhoImagem="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\einstein.png";
		
		src = Imgcodecs.imread(caminhoImagem);
		
		dst = new Mat(src.rows(),src.cols(),src.type());
		Convolucao.Media(src, dst, "cinza");
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\einsteinMediaCinza.png", dst);
		System.out.println("Image Media Cinza - Processada!");
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\einsteinMedianaCinza.png", dst);
		System.out.println("Image Mediana Cinza- Processada!");
	}

}
