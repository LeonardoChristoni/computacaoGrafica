package telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import computacaoGrafica.Pixel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Preenchimento extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Preenchimento frame = new Preenchimento();	
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Preenchimento() {
		getContentPane().setForeground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setVisible(true);
		
		JLabel lblImage = new JLabel("");
		lblImage.setOpaque(true);
		lblImage.setBackground(new Color(255, 255, 255));
		lblImage.setPreferredSize(new Dimension(800,600));
		lblImage.setVisible(true);
		Mat imagem = new Mat();
		carregarImagem(lblImage,imagem,"C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\goku.jpg");
		
		lblImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x,y;
				x=e.getX();
				y=e.getY();
				
				Mat imagemRefresh = (Mat) lblImage.getIcon();
				
				computacaoGrafica.Preenchimento.preenchimento4vizinhos(imagemRefresh, new Pixel(imagem.get(x, y), x, y), new double[] {255,255,0});
				Imgcodecs.imwrite("C:\\\\Users\\\\1531454\\\\git\\\\computacaoGrafica\\\\computacaoGrafica\\\\src\\\\assets\\\\gokuREDI.jpg",imagemRefresh);
				lblImage.setIcon(new ImageIcon("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\gokuREDI.jpg"));
			}
		});
		getContentPane().add(lblImage, BorderLayout.CENTER);
	}
	
	//carrega imagem e redimensiona conforme Jframe
	private void carregarImagem(JLabel lblImage,Mat imagem,String caminhoImagem) {
		imagem = Imgcodecs.imread(caminhoImagem);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setVerticalAlignment(SwingConstants.CENTER);
			    
	    BufferedImage img = mat2Img(imagem);
	    
	    if(imagem.width() > 800 || imagem.height()>600) {
	    	Mat resizeImage = new Mat();
	    	double novaLargura = 800,novaAltura = 600;
	    	if(imagem.width() > imagem.height()) {
	    		novaAltura = imagem.height() * 800 / imagem.width();
	    	}else {
	    		novaLargura = imagem.width() * 600 / imagem.height();
	    	}
	 	    Size scaleSize = new Size(novaLargura,novaAltura);
	 	    Imgproc.resize(imagem, resizeImage, scaleSize);
	 	    Imgcodecs.imwrite("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\gokuREDI.jpg", resizeImage);
	 	    
	 	    img = mat2Img(resizeImage);
	    }
	    
	    lblImage.setIcon(new ImageIcon("C:\\Users\\1531454\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\gokuREDI.jpg"));
	}
	
	//Mat to BufferedImage
	private static BufferedImage mat2Img(Mat in)
    {
        BufferedImage out;
        byte[] data = new byte[320 * 240 * (int)in.elemSize()];
        int type;
        in.get(0, 0, data);

        if(in.channels() == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else
            type = BufferedImage.TYPE_3BYTE_BGR;

        out = new BufferedImage(320, 240, type);

        out.getRaster().setDataElements(0, 0, 320, 240, data);
        return out;
    }
}
