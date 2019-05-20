package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Histograma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dst = null;
		String caminhoSaida=null,caminhoEntrada=null;
		
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA		
		caminhoEntrada ="C:\\\\Users\\\\nao-t\\\\eclipse-workspace\\\\computacaoGrafica\\\\src\\\\assets\\\\mundial.jpg";
		//ALTERAR DIRETORIO DE SAIDA DA IMAGEM
		caminhoSaida = "C:\\\\Users\\\\nao-t\\\\eclipse-workspace\\\\computacaoGrafica\\\\src\\\\assets\\\\histogramaCinza.jpg";
				
		src = Imgcodecs.imread(caminhoEntrada);
		
		HistogramaGray(src,dst,caminhoSaida);
		
		caminhoSaida = "C:\\\\Users\\\\nao-t\\\\eclipse-workspace\\\\computacaoGrafica\\\\src\\\\assets\\\\histogramaCor.jpg";
		HistogramaColor(src, dst, caminhoSaida);
		
	}
	
	private static void HistogramaGray(Mat src,Mat dst,String caminhoSaida) {
		double[] histograma = new double[256];
		int posicaoTom;
		
		//cria matriz de iluminancia
		for (int i = 0; i < src.height(); i++) {
			for(int j = 0; j<src.width(); j++) {
				posicaoTom = (int) ((src.get(i, j)[0] + src.get(i, j)[1] + src.get(i, j)[2])/3);
				//frequencia dos pixels q possue tom = posicaoTom
				histograma[posicaoTom] += 1;
			}
		}
		
		for(int i=0;i<256;i++){
			//probabilidade do nivel de cinza
			histograma[i] = histograma[i] / (src.rows() * src.cols());
			if(i>0) {
				//somatoria anterior/equalizacao
				histograma[i] = histograma[i] + histograma[i-1] ;
			}
		}
		
		dst = new Mat(src.rows(), src.cols(),src.type());
		
		for(int i=0;i<src.rows();i++) {
			for(int j=0;j<src.cols();j++) {
				double[] rgb = src.get(i, j);
				//para a cor voltar a ter intensidade
				double[] pixel = {
					histograma[(int) rgb[0]]*255,
					histograma[(int) rgb[1]]*255,
					histograma[(int) rgb[2]]*255};
				dst.put(i,j,pixel);
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida,dst);
		System.out.println("Imagem Histograma Cinza - Processada!");
	}
	
	private static void HistogramaColor(Mat src,Mat dst,String caminhoSaida) {
		double[] histograma = new double[256];
		
		//cria matriz de iluminancia
		for (int i = 0; i < src.height(); i++) {
			for(int j = 0; j<src.width(); j++) {
				double[] rgb = src.get(i,j);
				
				double[] yiq = {(0.299*rgb[0])+(0.587*rgb[1])+(0.114*rgb[2]),
						(0.596*rgb[0])-(0.275*rgb[1])-(0.321*rgb[2]),
						(0.212*rgb[0])-(0.523*rgb[1])+(0.311*rgb[2])};
				
				src.put(i, i, yiq);
				histograma[(int) yiq[0]]++;
			}
		}
		
		for(int i=0;i<256;i++){
			//probabilidade do nivel de cinza
			histograma[i] = histograma[i] / (src.rows() * src.cols());
			if(i>0) {
				//somatoria anterior/equalizacao
				histograma[i] = histograma[i] + histograma[i-1] ;
			}
		}
		
		dst = new Mat(src.rows(), src.cols(),src.type());
		
		for(int i=0;i<src.rows();i++) {
			for(int j=0;j<src.cols();j++) {
				double[] yiq = src.get(i, j);
				//para a cor voltar a ter intensidade
				double[] pixel = {
					histograma[(int) yiq[0]]*255,
					histograma[(int) yiq[1]],
					histograma[(int) yiq[2]]};
				double[] rgb = {(1*pixel[0])+(0.956*pixel[1])+(0.620*pixel[2]),
						(1*pixel[0])-(0.272*pixel[1])-(0.647*pixel[2]),
						(1*pixel[0])-(1.108*pixel[1])+(1.705*pixel[2])};
				
				dst.put(i,j,rgb);
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida,dst);
		System.out.println("Imagem Histograma Cor - Processada!");
	}
}