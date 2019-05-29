package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import computacaoGrafica.Pixel;

public class PreechimentoJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreechimentoJFrame frame = new PreechimentoJFrame();	
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
	public PreechimentoJFrame() {
		getContentPane().setForeground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		
		JLabel lblImage = new JLabel("");
		lblImage.setOpaque(true);
		lblImage.setBackground(new Color(255, 255, 255));
		lblImage.setVisible(true);
		Mat imagem = new Mat();
		String imagemParcial="C:\\Users\\nao-t\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\imagemParcial.png";
		carregarImagem(lblImage,imagem,"C:\\Users\\nao-t\\git\\computacaoGrafica\\computacaoGrafica\\src\\assets\\preenchimentoClick.png",imagemParcial);

		lblImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mat imagemRefresh = Imgcodecs.imread(imagemParcial);
				
				int x,y;
				x = e.getX();
				y = e.getY() - 10;
				
				computacaoGrafica.Preenchimento.preenchimento4vizinhos(imagemRefresh, new Pixel(imagemRefresh.get(x, y), x, y), new double[] {255,255,0});
				Imgcodecs.imwrite(imagemParcial,imagemRefresh);
				
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File(imagemParcial));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImageIcon icon = new ImageIcon(img);
				lblImage.setIcon(icon);
			}
		});
		getContentPane().add(lblImage, BorderLayout.CENTER);
	}
	
	//carrega imagem e redimensiona conforme Jframe
	private void carregarImagem(JLabel lblImage,Mat imagem,String caminhoImagem,String imagemParcial) {
		imagem = Imgcodecs.imread(caminhoImagem);
		
		lblImage.setHorizontalAlignment(SwingConstants.LEFT);
		lblImage.setVerticalAlignment(SwingConstants.TOP);
			    
//	    BufferedImage img = mat2Img(imagem);
		Mat resizeImage = imagem.clone();
//	    if(imagem.width() > 800 || imagem.height()>600) {
//	    	
//	    	double novaLargura = 800,novaAltura = 600;
//	    	if(imagem.width() > imagem.height()) {
//	    		novaAltura = imagem.height() * 800 / imagem.width();
//	    	}else {
//	    		novaLargura = imagem.width() * 600 / imagem.height();
//	    	}
//	 	    Size scaleSize = new Size(novaLargura,novaAltura);
//	 	    Imgproc.resize(imagem, resizeImage, scaleSize);
//	 	    
////	 	    img = mat2Img(resizeImage);
//	    }

 	    Imgcodecs.imwrite(imagemParcial, resizeImage);
	    lblImage.setIcon(new ImageIcon(imagemParcial));
	}
	
	//Mat to BufferedImage
	@SuppressWarnings("unused")
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
