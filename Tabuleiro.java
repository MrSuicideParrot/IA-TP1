import Ponto;
import java.util.Queue;
class Tabuleiro{ // implements Comparable<Tabuleiro>{
  private final int lado = 4;

  private Integer[][] posic;
  private Ponto zero;

  public Tabuleiro(Tabuleiro p2, Ponto target){
    p1 = this;
    p1.posic = newInteger(p2.posic);
    p1.zero = target;
    p1.posic[p2.zero.getX()][p2.zero.getY()]= new Integer(p1.posic[target.getX()][target.getY()]);
    p1.posic[target.getX()][target.getY()] = 0;
  }

  public Tabuleiro(int[][] posic){
    posic = new Integer[lado][lado](posic);
    this.zero = findZero();
  }

  //--------Verificar se e possivel chegar a posicao final
  public Boolean isNotImpossible(){
    int[] inicial = new int[lado*lado];

    //organizacao em vetor
    int number = 0;
    for (int i =0 ; i < size; ++i)
      for(int j= 0; j <s ize;++j){
          inicial[number] = this.posic[i][j];
          ++number;
      }

    //calculo da paridade da matriz
    number = 0
    for (int i = 0;i < size*size ;++i ) {
      for (int j = i; j < size*size;++j){
          if(inicial[i]>inicial[j])
            number+=1;
      }
    }

      // ( (grid width odd) && (#inversions even) )  ||  ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) )
      // odd -> impar
      // even -> par
      //logo (blank on odd row from bottom) == (#inversions even))
    return(4-zero.getY()%2 == number%2);
  }
  public Tabuleiro[] makeDescendents(Boolean repetidos, Hashmap registo){
    LinkedList<Tabuleiro> descendentes = new LinkedList<Tabuleiro>();
    int[] moveX ={0, 1, 0, -1};
    int[] moveY ={-1, 0, 1, 0};
    for(int i = 0; i < lado; ++i){
      Ponto ponto = new Ponto(moveX[i],moveY[i]);
      if(!ponto.isValid()){
        // ver se elimina o ponto
        continue;
      }
      tabu = new Tabuleiro(this, new Ponto(moveX[i],moveY[i]));
      if(repetidos){
        if(registo.containsKey(tabu))
          //eliminar tabuleiro
          continue;
      }
      descendentes.addFirst(tabu);
    }
    return descendentes;
  }

  public int distToEnd(Tabuleiro final){
    int aux = 0;
    for (int i = 0;i < lado; ++i) {
      for (int j = 0;j < lado; ++j) {
        aux += distToEnd(i,j,final);
      }
    }
    return aux;
  }

  private int distToEnd(int x, int y, Tabuleiro final){
    int[] moveX = {0, 1, 0, -1};
    int[] moveY = {-1, 0, 1, 0};
    int[][] visited = new int[size][size];
    Arrays.fill(visited,false);
    Queue fila = new Queue();
    int[] posicao = {x,y,0}; //pq o java nao e tao fixe como python e nao tem tuplos levando nos a fazer coisas feias
    visited[x][y] = true;
    while(!fila.isEmpty()){
      posicao = fila.poll();
      if()
    }
  }

  public String toString() {
    String s = new String();
    for (int i = 0;i < size;++i ) {
      for (int j = 0; j < size;++j){
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
  }
  /*
  Vamos ver ainda como e que vamos implementar a igaualdade usando isto ou a hash
  @Override
   public int compareTo(Tabuleiro p) {

   }
   */
   @Override
   public Boolean equals(Tabuleiro p2){
     Tabuleiro p1 = this;
     for (int i=0;i<lado;++i)
      for (int j=0;j<lado;++j)
        if(p1.posic[i][j]!=p2.posic[i][j])
          return false;
     return true;
   }

}
