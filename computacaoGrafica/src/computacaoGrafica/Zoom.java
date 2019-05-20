package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Zoom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat src = null, dst = null;
		String caminhoSaida=null,caminhoEntrada=null;
		
		// ALTERAR DIRETORIO DA IMAGEM DE ENTRADA		
		caminhoEntrada ="/home/leonardo/Desktop/workspace/atividades/assets/pequena.png";
		//ALTERAR DIRETORIO DE SAIDA DA IMAGEM
		caminhoSaida = "/home/leonardo/Desktop/workspace/atividades/assets/atividade2-zoomin-quadrado.png";
				
		src = Imgcodecs.imread(caminhoEntrada);
		
		zoomInQuadrado(src,dst,caminhoSaida);
		
		caminhoSaida = "/home/leonardo/Desktop/workspace/atividades/assets/atividade2-zoomin-linear.png";
		zoomInLinear(src,dst,caminhoSaida);
		
		caminhoSaida = "/home/leonardo/Desktop/workspace/atividades/assets/atividade2-zoomin-linear.png";
		zoomOutQuadrado(src,dst,caminhoSaida);
		
		caminhoSaida = "/home/leonardo/Desktop/workspace/atividades/assets/atividade2-zoomout-linear.png";
		zoomOutLinear(src,dst,caminhoSaida);
		
	}
	
	private static void zoomInQuadrado(Mat src,Mat dst,String caminhoSaida) {
		dst = new Mat(src.rows()*2, src.cols()*2,src.type());
		int i,j,k,l;
		
		for(i=0;i<src.rows();i++){
			for(j=0;j<src.cols();j++){
				for(k=0;k<2;k++) {
					for(l=0;l<2;l++) {
						dst.put(i*2+k, j*2+l, src.get(i, j));
					}
				}
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida,dst);
		System.out.println("Imagem Zoon in Quadrado - Processada!");
	}
	
	private static void zoomInLinear(Mat src,Mat dst,String caminhoSaida) {
		dst = new Mat(src.rows()*2 - 1, src.cols() * 2 -1,src.type());
		int i=0,j=0;
		double[] rgb1,rgb2,rgbMedia;
		
		//distribui os valores da matriz original na nova
		for(i=0;i<src.rows();i++) {
			for(j=0;j<src.cols();j++) {
				dst.put(i*2, j*2, src.get(i, j));
			}
		}
		
		//faz a media de cada posicao com base nas colunas vizinhas
		for(i=0;i<dst.rows();i=i+2) {
			for(j=0;j<dst.cols();j++) {
				if(j%2!=0) {
					rgb1 = dst.get(i, j-1);
					rgb2 = dst.get(i, j+1);
					
					rgbMedia = new double[] {(rgb1[0]+rgb2[0])/2,(rgb1[1]+rgb2[1])/2,(rgb1[2]+rgb2[2])/2};
					dst.put(i, j, rgbMedia);
				}
			}
		}
		
		//faz a media de cada posicao com base nos valores das linhas vizinhas
		for(i=0;i<dst.rows();i++) {
			for(j=0;j<dst.cols();j++) {
				if(i%2!=0) {
					rgb1 = dst.get(i-1, j);
					rgb2 = dst.get(i+1, j);
					rgbMedia = new double[] {(rgb1[0]+rgb2[0])/2,(rgb1[1]+rgb2[1])/2,(rgb1[2]+rgb2[2])/2};
					dst.put(i, j, rgbMedia);
				}
			}
		}

		Imgcodecs.imwrite(caminhoSaida,dst);
		System.out.println("Imagem Zoon in Linear - Processada!");
	}
	
	private static void zoomOutQuadrado(Mat src,Mat dst,String caminhoSaida) {
		dst = new Mat(src.rows()/2, src.cols()/2,src.type());
		int i,j;
		
		for(i=0;i<dst.rows();i++){
			for(j=0;j<dst.cols();j++){
				dst.put(i, j, src.get(i*2, j*2));
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida,dst);
		System.out.println("Imagem Zoon Out Quadrado - Processada!");
	}
	
	private static void zoomOutLinear(Mat src,Mat dst,String caminhoSaida) {
		int i,j;
		double[] rgb1,rgb2,rgb3,rgb4,rgbMedia;
		dst = new Mat(src.rows()/2,src.cols()/2,src.type());
				
		for(i=0;i<dst.rows();i++) {
			for(j=0;j<dst.cols();j++) {
				
				if(i+1 == dst.rows()) {
					if(j+1 == dst.cols()) {
						dst.put(i, j, src.get(i*2, j*2));
					}else {
						rgb1 = src.get(i*2, j*2);
						rgb2 = src.get(i*2, j*2 + 1);
						rgbMedia = new double[] {(rgb1[0]+rgb2[0])/2,(rgb1[1]+rgb2[1])/2,(rgb1[2]+rgb2[2])/2};
						dst.put(i, j, rgbMedia);
					}
				}else {
					if(j+1 == dst.cols()) {
						rgb1 = src.get(i*2, j*2);
						rgb2 = src.get(i*2+1, j*2);
						rgbMedia = new double[] {(rgb1[0]+rgb2[0])/2,(rgb1[1]+rgb2[1])/2,(rgb1[2]+rgb2[2])/2};
						dst.put(i, j, rgbMedia);
					}else {
						rgb1 = src.get(i*2, j*2);
						rgb2 = src.get(i*2+1, j*2);
						rgb3 = src.get(i, j*2+1);
						rgb4 = src.get(i*2+1, j*2+1);
						rgbMedia = new double[] {(rgb1[0]+rgb2[0]+rgb3[0]+rgb4[0])/4,(rgb1[1]+rgb2[1]+rgb3[1]+rgb4[1])/4,(rgb1[2]+rgb2[2]+rgb3[2]+rgb4[2])/4};
						dst.put(i, j, rgbMedia);
					}
				}
			}
		}
		Imgcodecs.imwrite(caminhoSaida,dst);
		System.out.println("Imagem Zoon Out Linear - Processada!");
	}
}
