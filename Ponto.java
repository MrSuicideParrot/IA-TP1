public class Ponto {
	private int x,y;
	private final static int max = 4;

	public Ponto(){
		x = y = 0;
	}

	public Ponto(int x0, int y0){
		x = x0;
		y = y0;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public Boolean isValid(){
		return 0 <= x && x < max && 0 <= y &&  y < max;
	}

	public static Boolean isValid(int x,int y){
		return 0 <= x && x < max && 0 <= y &&  y < max;
	}

}
