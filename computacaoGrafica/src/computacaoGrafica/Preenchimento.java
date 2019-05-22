package computacaoGrafica;
//https://docplayer.com.br/11772225-Computacao-grafica-rasterizacao-e-preenchimento-de-regioes-marco-antonio-garcia-de-carvalho-fevereiro-de-2009-computacao-grafica.html
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;

public class Preenchimento {
	
	private Preenchimento() {};
	
	public static void preenchimento4vizinhos(Mat dst,Pixel pixel,double[] corNova) {
		int x = pixel.getX();
		int y = pixel.getY();
		
		Pixel cima = new Pixel(dst.get(x-1,y),x-1,y);
		Pixel esq = new Pixel(dst.get(x,y-1),x,y-1);
		Pixel dir = new Pixel(dst.get(x,y+1),x,y+1);
		Pixel baixo = new Pixel(dst.get(x+1,y),x+1,y);
		
		List<Pixel> listaVizinhos = new ArrayList<Pixel>(Arrays.asList(cima,esq,dir,baixo));
		
		//aplico cor de preenchimento
		dst.put(x,y, corNova);
		
		for(int i=0;i<4;i++) {
			
			//se for pixel de borda , ignoro
			if(listaVizinhos.get(i).getX()<0 || listaVizinhos.get(i).getY()<0 || 
					listaVizinhos.get(i).getX()>dst.rows()-1 || listaVizinhos.get(i).getY()>dst.cols()-1) {
				continue;
			}

			//se for a mesma cor
			if(listaVizinhos.get(i).getPixel()[0] == pixel.getPixel()[0] && 
			   listaVizinhos.get(i).getPixel()[1]==pixel.getPixel()[1] && 
			   listaVizinhos.get(i).getPixel()[2]==pixel.getPixel()[2]) {
				
				switch(i) {
					case 0:
						pixel.setX(x-1);
						pixel.setY(y);
						break;
					case 1:
						pixel.setX(x);
						pixel.setY(y-1);
						break;
					case 2:
						pixel.setX(x);
						pixel.setY(y+1);
						break;
					case 3:
						pixel.setX(x+1);
						pixel.setY(y);
						break;
				}
				preenchimento4vizinhos(dst,pixel,corNova);
			}
		}
	}
	
	public static void preenchimentoPorCor(Mat dst,double[] cor,double[] corNova) {
		
		for(int i=0;i<dst.rows();i++) {
			for(int j=0;j<dst.cols();j++) {
				if(dst.get(i, j)[0] == cor[0] && dst.get(i,j)[1]==cor[1] && dst.get(i,j)[2]==cor[2]) {
					dst.put(i, j,corNova);
				}
			}
		}
		
	}
}
