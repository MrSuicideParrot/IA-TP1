public class Ponto {
	private int x,y;
	private final static int max = 4;
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
		return 0 <= x && x < max && 0 <= y &&  y < max;
	}

	public static Boolean isValid(int x,int y){
		return 0 <= x && x < max && 0 <= y &&  y < max;
	}

	public Ponto(int x0, int y0){
		x = x0;
		y = y0;
	}
	/*public boolean equals(Ponto p2){
		Ponto p1 = this;
		return(p1.x.equals(p2.x)&&p1.y.equals(p2.y));
	}*/
}
