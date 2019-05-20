//ALTERAR PACKAGE
package computacaoGrafica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Sobel {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dst = null;
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA	
		String caminhoImagem="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\goku.jpg";
		int limiar = 50;
		
		src = Imgcodecs.imread(caminhoImagem);
		
		dst = new Mat(src.rows(),src.cols(),src.type());
		
		sobel(src,dst,limiar);
		
		// ALTERAR DIRETORIO DA IMAGEM DE SAIDA	
		Imgcodecs.imwrite("C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\gokuSobel.png", dst);
		System.out.println("Image Sobel- Processada!");
	}
	
	private static void sobel(Mat src,Mat dst,int limiar) {
		double[] pixelPreto = {0,0,0};
		double[] pixelBranco = {255,255,255};
		
		for(int i=1;i<src.height() - 1;i++) {
			for(int j=1; j<src.width()-1 ;j++) {
				//pixeis vizinhos
				double[] esqCima = src.get(i-1, j-1);
				double[] cima = src.get(i-1,j);
				double[] dirCima = src.get(i-1,j+1);
				double[] esq = src.get(i,j-1);
				double[] central = src.get(i,j);
				double[] dir = src.get(i,j+1);
				double[] esqBaixo = src.get(i+1,j-1);
				double[] baixo = src.get(i+1,j);
				double[] dirBaixo = src.get(i+1,j+1);
				
				List<double[]> pixeis = new ArrayList<double[]>(
						Arrays.asList(esqCima,cima,dirCima,esq,central,dir,esqBaixo,baixo,dirBaixo));
				
				Convolucao.gerarTomCinza(pixeis);
//				-1 -2 -1      -1 0 1
//				 0  0  0      -2 0 2
//				 1  2  1      -1 0 1
				
				//MULTIPLICA PELO OPERADOR SOBEL
				double pixelHorizontal = (-1*pixeis.get(0)[0])+(-2*pixeis.get(1)[0])+(-1*pixeis.get(2)[0])+pixeis.get(6)[0]+(2*pixeis.get(7)[0])+pixeis.get(8)[0];
				double pixelVertical = (-1*pixeis.get(0)[0])+pixeis.get(2)[0]+(-2*pixeis.get(3)[0])+(2*pixeis.get(5)[0])+(-1*pixeis.get(6)[0])+pixeis.get(8)[0];
				
				//FAZ VERIFICACAO DO LIMIAR, PARA SETAR PIXEIS
				if(limiar < Math.sqrt((pixelHorizontal*pixelHorizontal) + (pixelVertical*pixelVertical))) {
					dst.put(i,j,pixelPreto);
				}else dst.put(i,j,pixelBranco);
			}
		}
	}

}
