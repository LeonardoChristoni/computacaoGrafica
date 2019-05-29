package telas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import computacaoGrafica.Pixel;

public class PreenchimentoTela {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		JFrame preenchimento = new JFrame();
		JLabel lblImage = new JLabel();
		
		String imagemParcial="C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\imagemParcial.jpg";
		Mat imagem = Imgcodecs.imread("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\teste.jpg");
		BufferedImage img = ImageIO.read(new File("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\teste.jpg"));
		lblImage.setSize(img.getWidth(null),img.getHeight(null));
		
		Mat resizeImage = imagem.clone();
		
		Imgcodecs.imwrite(imagemParcial, resizeImage);
		
		lblImage.setIcon(new ImageIcon(imagemParcial));
		preenchimento.add(lblImage);
		preenchimento.setVisible(true);
		preenchimento.setFocusable(true);
		preenchimento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mat imagemRefresh = Imgcodecs.imread(imagemParcial);
				
				int x,y;
				x = e.getX();
				y = e.getY();
				
				computacaoGrafica.Preenchimento.preenchimento4vizinhos(imagemRefresh, new Pixel(imagemRefresh.get(x, y), x, y), new double[] {255,255,0});
				Imgcodecs.imwrite(imagemParcial,imagemRefresh);
				lblImage.setIcon(new ImageIcon(imagemParcial));
			}
		});
	}
}
