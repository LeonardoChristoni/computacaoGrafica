package computacaoGrafica;

public class Pixel {
	
	public Pixel(double[] pixel,int x,int y) {
		this.pixel = pixel;
		this.x = x;
		this.y = y;
	}

	private double[] pixel;
	private int x;
	private int y;
	
	public double[] getPixel() {
		return pixel;
	}
	public void setPixel(double[] pixel) {
		this.pixel = pixel;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
