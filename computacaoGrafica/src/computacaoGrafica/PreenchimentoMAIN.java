package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class PreenchimentoMAIN {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dst = null;
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA	
		String caminhoImagem="C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\rgb.jpg";
		
		src = Imgcodecs.imread(caminhoImagem);
		
		dst = src.clone();
		
		Preenchimento.preenchimento4vizinhos(dst, new Pixel(src.get(src.rows()/2, src.cols()/2), src.rows()/2, src.cols()/2));
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\preenchimentoCentro.jpg", dst);
		System.out.println("Preenchimento por Centro - Processado!");
	}

}
