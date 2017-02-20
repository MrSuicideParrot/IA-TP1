import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;

class Tabuleiro{ // implements Comparable<Tabuleiro>{
  private final static int lado = 4;

  private Integer[][] posic;
  private Ponto zero;

  public Tabuleiro(Tabuleiro p2, Ponto target){
    this.posic = new Integer[lado][lado];
    Tabuleiro p1 = this;
    MatrixCopy(p1.posic,p2.posic);
    //p1.posic = newInteger(p2.posic);
    p1.zero = target;
    p1.posic[p2.zero.getX()][p2.zero.getY()]= new Integer(p1.posic[target.getX()][target.getY()]);
    p1.posic[target.getX()][target.getY()] = 0;
  }

  public Tabuleiro(Integer[][] posic){
    this.posic = new Integer[lado][lado];
    MatrixCopy(this.posic,posic);
    this.zero = findZero();
  }

  public void MatrixCopy(Integer[][] dest,Integer[][] source){
    //dest = new Integer[lado][lado];
    for (int i = 0;i < lado ;++i ) {
      for (int j = 0;j <lado ;++j ) {
        dest[i][j] = new Integer(source[i][j]);
      }
    }
  }

  //--------Verificar se e possivel chegar a posicao final
  public Boolean isNotImpossible(Tabuleiro targetTab){
    int[] inicial = new int[lado*lado];
    int[] target = new int[lado*lado];

    //organizacao em vetor
    int number1 = 0;
    for (int i =0 ; i < lado; ++i)
      for(int j= 0; j < lado;++j){
          inicial[number1] = this.posic[i][j];
          ++number1;
      }

    number1 = 0;
    for (int i =0 ; i < lado; ++i)
      for(int j= 0; j < lado;++j){
          target[number1] = targetTab.posic[i][j];
          ++number1;
      }


    //calculo da paridade da matriz
    number1 = 0;
    for (int i = 0;i < lado*lado ;++i ) {
      for (int j = i; j < lado*lado;++j){
          if(inicial[i]>inicial[j])
            number1+=1;
      }
    }

    int number2 = 0;
    for (int i = 0;i < lado*lado ;++i ) {
      for (int j = i; j < lado*lado;++j){
          if(target[i]>target[j])
            number2+=1;
      }
    }

      // ( (grid width odd) && (#inversions even) )  ||  ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) )
      // odd -> impar
      // even -> par
      //logo (blank on odd row from bottom) == (#inversions even))
    return ((number1 % 2 == 0 && number2 % 2 == 0) || (number1 % 2 == 1 && number2 % 2 == 1));

    //return(4-zero.getY()%2 == number%2);
  }
  public LinkedList<Tabuleiro> makeDescendents(Map registo){
    LinkedList<Tabuleiro> descendentes = new LinkedList<Tabuleiro>();
    int[] moveX ={0, 1, 0, -1};
    int[] moveY ={-1, 0, 1, 0};
    for(int i = 0; i < lado; ++i){
      Ponto ponto = new Ponto(moveX[i],moveY[i]);
      if(!ponto.isValid()){
        // ver se elimina o ponto
        continue;
      }
      Tabuleiro tabu = new Tabuleiro(this, new Ponto(moveX[i],moveY[i]));
      if(registo != null){
        if(registo.containsKey(tabu))
          //eliminar tabuleiro
          continue;
      }
      descendentes.addFirst(tabu);
    }
    return descendentes;
  }

  public int distToEnd(Tabuleiro target){
    int aux = 0;
    for (int i = 0;i < lado; ++i) {
      for (int j = 0;j < lado; ++j) {
        aux += distToEnd(i,j,target);
      }
    }
    return aux;
  }

  private int distToEnd(int x, int y, Tabuleiro target){
    int[] moveX = {0, 1, 0, -1};
    int[] moveY = {-1, 0, 1, 0};
    Boolean[][] visited = new Boolean[lado][lado];
    //iniciate visited Array
    for (int i = 0;i <lado ;++i )
      for (int j = 0;j <lado ;++j )
        visited[i][j] = false;

    Queue<Integer[]> fila = new LinkedList<Integer[]>();
    Integer[] posicao = {x,y,0}; //pq o java nao e tao fixe como python e nao tem tuplos levando nos a fazer coisas feias
    fila.add(posicao);
    visited[x][y] = true;
    while(!fila.isEmpty()){
      posicao = fila.poll();
      if(target.posic[posicao[0]][posicao[1]]==posic[x][y]){
        return posicao[0];
      }
      for (int i = 0;i < lado ;++i) {
        if (Ponto.isValid(posicao[0]+moveX[i],posicao[1]+moveY[i])) {
          Integer aux[] = {posicao[0]+moveX[i],posicao[1]+moveY[i],posicao[2]+1};
          fila.add(aux);
        }
      }
    }
    System.err.println("Deu problemas o distToEnd!!!!");
    return -1;
  }

  public String toString() {
    String s = new String();
    for (int i = 0;i < lado;++i ) {
      for (int j = 0; j < lado;++j){
          s += " " + posic[i][j];
      }
      s += "\n";
    }
    return s;
  }

  private Ponto findZero(){
    for (int i=0;i<lado;++i)
      for (int j=0;j<lado;++j)
        if(posic[i][j]==0)
          return new Ponto(i,j);

    //-------caso nao seja encontrado nenhum zero para o programa
    System.err.println("Zero not found!");
    System.exit(1);
    return null ;
  }
    private void printMATRIX(Integer[][] matriz){
      for (int i = 0;i < lado ;++i ) {
        for (int j = 0;j < lado ; ++j) {
          System.out.print(" "+matriz[i][j]);
        }
          System.out.println();
      }
    }
    @Override
    public int hashCode(){
		return this.toString().hashCode();
	}

    public boolean equals(Tabuleiro p2){
     Tabuleiro p1 = this;
     for (int i=0;i<lado;++i)
      for (int j=0;j<lado;++j)
        if(p1.posic[i][j]!=p2.posic[i][j])
          return false;
     return true;
   }

}
