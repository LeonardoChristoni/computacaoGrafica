package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class PreenchimentoMAIN {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dstCentro = null, dstPonto = null, dstCor = null;
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA	
		String caminhoImagem="C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\preenchimento20x20.png";
		
		src = Imgcodecs.imread(caminhoImagem);
		
		dstCentro = src.clone();
		dstPonto = src.clone();
		dstCor = src.clone();
		
		//preenchimento a partir do centro
		Preenchimento.preenchimento4vizinhos(dstCentro, 
				new Pixel(src.get(src.rows()/2, src.cols()/2), src.rows()/2, src.cols()/2),
				new double[] {255,255,0});
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\preenchimentoPorCentro.jpg", dstCentro);
		System.out.println("Preenchimento por Centro - Processado!");
		
		//alterar ponto (x,y)
		int x=2,y=2;
		
		//preenchimento a partir de ponto (x,y)
		Preenchimento.preenchimento4vizinhos(dstPonto, 
				new Pixel(src.get(x, y), x, y),
				new double[] {255,255,0});
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\preenchimentoPorPonto.jpg", dstPonto);
		System.out.println("Preenchimento por Ponto - Processado!");
		
		//preenchimento por cor
		Preenchimento.preenchimentoPorCor(dstCor, new double[] {255,255,255}, new double[] {0,0,255});
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\preenchimentoPorCor.jpg", dstCor);
		System.out.println("Preenchimento por Cor - Processado!");
	}

}
