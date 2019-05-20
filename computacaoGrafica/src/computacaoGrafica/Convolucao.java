//ALTERAR PACKAGE
package computacaoGrafica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.opencv.core.Mat;

public class Convolucao {
	
	private Convolucao() {}
	
	public static void Media(Mat src,Mat dst,String operacao) {
		
		for(int i=1;i<src.height()-1;i++) {
			for(int j=1;j<src.width()-1;j++) {
				
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
				
				if("cinza".equals(operacao)) {
					gerarTomCinza(pixeis);
				}
				
				//faz a media dos pixeis
				double[] pixel = {
						(esqCima[0]+cima[0]+dirCima[0]+esq[0]+central[0]+dir[0]+esqBaixo[0]+baixo[0]+dirBaixo[0])/9,
						(esqCima[1]+cima[1]+dirCima[1]+esq[1]+central[1]+dir[1]+esqBaixo[1]+baixo[1]+dirBaixo[1])/9,
						(esqCima[2]+cima[2]+dirCima[2]+esq[2]+central[2]+dir[2]+esqBaixo[2]+baixo[2]+dirBaixo[2])/9};
				
				dst.put(i, j,pixel);
			}
		}
	}
	
	public static void Mediana(Mat src,Mat dst,String operacao) {
		
		for(int i=1;i<src.height()-1;i++) {
			for(int j=1;j<src.width()-1;j++) {
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
				
				if("cinza".equals(operacao)) {
					gerarTomCinza(pixeis);
				}
				
				Collections.sort(pixeis, new Comparator<double[]>() {
					@Override
					public int compare(double[] x,double[] y) {
						return x[0] > y[0] ? -1 : (x[0] < y[0]) ? 1 : 0;
					}
				});
				
				//depois de ordenado, pega o pixel mediano
				dst.put(i,j,pixeis.get(4));
			}
		}
	}
	
	public static void gerarTomCinza(List<double[]> pixeis) {
		double pixelCinza;
		
		//somo os canais e divido por 3
		pixelCinza = (pixeis.get(0)[0]+pixeis.get(0)[1]+pixeis.get(0)[2])/3;
		pixeis.set(0,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(1)[0]+pixeis.get(1)[1]+pixeis.get(1)[2])/3;
		pixeis.set(1,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(2)[0]+pixeis.get(2)[1]+pixeis.get(2)[2])/3;
		pixeis.set(2,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(3)[0]+pixeis.get(3)[1]+pixeis.get(3)[2])/3;
		pixeis.set(3,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(4)[0]+pixeis.get(4)[1]+pixeis.get(4)[2])/3;
		pixeis.set(4,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(5)[0]+pixeis.get(5)[1]+pixeis.get(5)[2])/3;
		pixeis.set(5,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(6)[0]+pixeis.get(6)[1]+pixeis.get(6)[2])/3;
		pixeis.set(6,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(7)[0]+pixeis.get(7)[1]+pixeis.get(7)[2])/3;
		pixeis.set(7,new double[]{pixelCinza,pixelCinza,pixelCinza});
		pixelCinza = (pixeis.get(8)[0]+pixeis.get(8)[1]+pixeis.get(8)[2])/3;
		pixeis.set(8,new double[]{pixelCinza,pixelCinza,pixelCinza});
	}
}
