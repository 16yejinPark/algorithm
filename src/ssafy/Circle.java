package ssafy;

public class Circle {
	private final double PI;
	private int r;
	Circle(int r, double PI){
		this.setR(r);
		this.PI = PI;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
}
