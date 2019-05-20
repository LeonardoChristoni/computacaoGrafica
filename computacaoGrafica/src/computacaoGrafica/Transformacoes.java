package computacaoGrafica;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Transformacoes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat src = null, dst=null;
	
		String caminhoSaida=null,caminhoEntrada=null;
	
		//alterar diretorios de entrada e saida das imagens
		caminhoEntrada="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\blur.png";
		caminhoSaida="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\transladada.png";
		
		src = Imgcodecs.imread(caminhoEntrada);
		dst = new Mat(src.rows(),src.cols(),src.type());
		
		translacao(src, dst, caminhoSaida);
		
		//alterar diretorios de entrada e saida das imagens
		caminhoEntrada="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\blur.png";
		caminhoSaida="C:\\Users\\nao-t\\eclipse-workspace\\computacaoGrafica\\src\\assets\\rotacionada.png";
		
		src = Imgcodecs.imread(caminhoEntrada);
		dst = new Mat(src.rows(),src.cols(),src.type());
		
		rotacao(src,dst,caminhoSaida);
	}
	
	private static void translacao(Mat src,Mat dst,String caminhoSaida) {
		int i,j;
		
		int x,y;
		//preencher valores para translacao
		x=50;
		y=50;
		
		for(i=0;i<src.rows();i++) {
			for(j=0;j<src.cols();j++) {
				dst.put(i-y, j+x,src.get(i, j));
			}
		}
		
		Imgcodecs.imwrite(caminhoSaida, dst);
		System.out.println("Imagem transladada - Processada!");
	}
	
	private static void rotacao(Mat src,Mat dst,String caminhoSaida) {
		int altura = src.cols();
		int largura = src.width();
		
		double angle = Math.toRadians(270);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        
        //para achar centro da imagem
        double x0 = 0.5 * (src.rows()  - 1);
        double y0 = 0.5 * (src.cols() - 1);
        
        for (int x = 0; x < src.rows(); x++) {
            for (int y = 0; y < src.cols(); y++) {
//                double a = x - x0;
//                double b = y - y0;
                int xlinha = (int) ((x * cos) - (y * sin));
                int ylinha = (int) ((y * sin) + (y * cos));
                
//                xlinha = (int) (xlinha + x0);
//                ylinha = (int) (ylinha + y0);
                
                if(xlinha > src.rows()) {
                	largura = xlinha;
                }
                if(ylinha > src.cols()) {
                	altura = ylinha;
                }
            }
        }
        
        dst = new Mat(largura,altura,src.type());
        
        for (int x = 0; x < src.rows(); x++) {
            for (int y = 0; y < src.cols(); y++) {
//                double a = x - x0;
//                double b = y - y0;
                int xlinha = (int) ((x * cos) - (y * sin));
                int ylinha = (int) ((x * sin) + (y * cos));
                
//                xlinha = (int) (xlinha + x0);
//                ylinha = (int) (ylinha + y0);
                
                dst.put(x,y, src.get(xlinha,ylinha));
            }
        }
        
        if(dst != null) {
            Imgcodecs.imwrite(caminhoSaida, dst);
    		System.out.println("Imagem rotacionada - Processada!");
        }
	}
}


//public class Rotacao {
//	
//	private Mat original;
//	
//	public Rotacao (Mat imagem){
//		this.original = imagem;
//	}
//	
//	public void executar (String output, double x){
//		
//		List<Pixel> lista = new ArrayList<Pixel>();
//		int maiorx = 0, maiory = 0;
//		
//		double a = x*Math.PI/180.0;
//		
//		int height = original.height();
//		int width = original.width();
//
//		for (int h = 0; h < height; h++) {
//			for (int w = 0; w < width; w++) {
//				double[] rgb = original.get(h, w);
//				lista.add(new Pixel(rgb, getX(h, w, a), getY(h, w, a)));
//				if(getX(h, w, a) > maiorx) maiorx = getX(h, w, a);
//				if(getY(h, w, a) > maiory) maiory = getY(h, w, a);
//			}
//		}
//		
//		Mat img = new Mat(maiorx, maiory, original.type());
//		
//		for(int i=0; i<lista.size(); i++){
//			img.put(lista.get(i).getX(), lista.get(i).getY(), lista.get(i).getRgb());
//		}
//
//		Manipulacao m = new Manipulacao();
//		m.salvarImagem(output, img);
//		
//	}
//	
//	//TODO Math.abs causa perda de pixel, mas da bom, mas tem que arrumar.
//	public int getX(int x, int y, double a){
//		return (int) Math.abs(x*Math.cos(a)-y*Math.sin(a));
//	}
//	
//	public int getY(int x, int y, double a){
//		return (int) Math.abs(x*Math.sin(a)+y*Math.cos(a));
//	}
//	
//}