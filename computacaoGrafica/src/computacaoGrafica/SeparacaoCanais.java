package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class SeparacaoCanais {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat src = null, dst=null;
	
		String caminhoSaida=null,caminhoEntrada=null;
	
		//alterar diretorios de entrada e saida das imagens
		caminhoEntrada="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\rgb.jpg";
		caminhoSaida="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\rgb";
		
		src = Imgcodecs.imread(caminhoEntrada);
		dst = src.clone();
		grayBlue(src, dst, caminhoSaida);
		grayGreen(src, dst, caminhoSaida);
		grayRed(src, dst, caminhoSaida);
		
	}

	private static void grayRed(Mat src,Mat dst,String caminhoSaida) {
		
		for(int i=0;i<src.rows();i++) {
			for(int j=0;j<src.cols();j++) {
				double[] pix = src.get(i,j);
				double[] gray = {pix[2],pix[2],pix[2]};
				dst.put(i,j,gray);
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida+"grayRed.jpg", dst);
		System.out.println("Imagem grayRed - Processada!");
	}

	private static void grayGreen(Mat src,Mat dst,String caminhoSaida) {
		
		for(int i=0;i<src.rows();i++) {
			for(int j=0;j<src.cols();j++) {
				double[] pix = src.get(i,j);
				double[] gray = {pix[1],pix[1],pix[1]};
				dst.put(i,j,gray);
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida+"grayGreen.jpg", dst);
		System.out.println("Imagem grayGreen - Processada!");
	}
	
	private static void grayBlue(Mat src,Mat dst,String caminhoSaida) {
		
		for(int i=0;i<src.rows();i++) {
			for(int j=0;j<src.cols();j++) {
				double[] pix = src.get(i,j);
				double[] gray = {pix[0],pix[0],pix[0]};
				dst.put(i,j,gray);
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida+"grayBlue.jpg", dst);
		System.out.println("Imagem grayBlue - Processada!");
	}	
}
