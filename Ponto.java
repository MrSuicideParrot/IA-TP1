public class Ponto {
	private int x,y;
	private final int max = 4;
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public Ponto(){
		x = y = 0;
	}
	public Boolean isValid(){
		return 0 <= x < max && 0 <= y < max;
	}

	public Ponto(int x0, int y0){
		x = x0;
		y = y0;
	}
}
