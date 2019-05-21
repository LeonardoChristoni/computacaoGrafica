package computacaoGrafica;
//https://docplayer.com.br/11772225-Computacao-grafica-rasterizacao-e-preenchimento-de-regioes-marco-antonio-garcia-de-carvalho-fevereiro-de-2009-computacao-grafica.html
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;

public class Preenchimento {
	
	private Preenchimento() {};
	
	public static void preenchimento4vizinhos(Mat dst,Pixel pixel) {
		int x = pixel.getX();
		int y = pixel.getY();
		
		double[] cima = dst.get(x-1,y);
		double[] esq = dst.get(x,y-1);
		double[] dir = dst.get(x,y+1);
		double[] baixo = dst.get(x+1,y);
		
		List<double[]> listaVizinhos = new ArrayList<double[]>(
				Arrays.asList(cima,esq,dir,baixo));
		
		//se for pixel de borda , ignoro
		if(x-1 == 0 || y-1 == 0 || x+1 == dst.rows() || y+1 == dst.cols()) {
			return;
		}
		
		for(int i=0;i<4;i++) {
			//se for a mesma cor
			if(listaVizinhos.get(i)[0] == pixel.getPixel()[0] && listaVizinhos.get(i)[1]==pixel.getPixel()[1] 
					&& listaVizinhos.get(i)[2]==pixel.getPixel()[2]) {
				
				double[] pxCor = {255,0,255};
				
				//aplico cor de preenchimento
				dst.put(x,y, pxCor);
				
				Pixel px = null;
				
				switch(i) {
					case 0:
						px = new Pixel(pixel.getPixel(),x-1,y);
//						pixel.setX(x-1);
//						pixel.setY(y);
						break;
					case 1:
						px = new Pixel(pixel.getPixel(),x,y-1);
//						pixel.setX(x);
//						pixel.setY(y-1);
						break;
					case 2:
						px = new Pixel(pixel.getPixel(),x,y+1);
//						pixel.setX(x);
//						pixel.setY(y+1);
						break;
					case 3:
						px = new Pixel(pixel.getPixel(),x+1,y);
//						pixel.setX(x+1);
//						pixel.setY(y);
						break;
				}
				
				preenchimento4vizinhos(dst,px);
			}
		}
	}
}
