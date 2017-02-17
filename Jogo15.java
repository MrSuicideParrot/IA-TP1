import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
//
// Classe que implementa ponto
//

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
		return 0 <= x && x < max && 0 <= y && y < max;
	}

	public Ponto(int x0, int y0){
		x = x0;
		y = y0;
	}
}

//
//Classe que implementa Tabuleiro
//

class Tabuleiro{ // implements Comparable<Tabuleiro>{
  private final int lado = 4;

  private Integer[][] posic;
  private Ponto zero;

  public Tabuleiro(Tabuleiro p2, Ponto target){
    p1 = this;
    p1.posic = newInteger(p2.posic);
    //System.arraycopy(p2.posic, 0,p1.posic, 0, p2.length );
    p1.zero = target;
    p1.posic[p2.zero.getX()][p2.zero.getY()]= new Integer(p1.posic[target.getX()][target.getY()]);
    p1.posic[target.getX()][target.getY()] = 0;
  }

  public Tabuleiro(int[][] posic){
    posic = new Integer(posic);
    this.zero = findZero();
  }

  //--------Verificar se e possivel chegar a posicao final
  public Boolean isNotImpossible(){
    int[] inicial = new int[lado*lado];

    //organizacao em vetor
    int number = 0;
    for (int i =0 ; i < size; ++i)
      for(int j= 0; j <size;++j){
          inicial[number] = this.posic[i][j];
          ++number;
      }

    //calculo da paridade da matriz
    number = 0;
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
  public Tabuleiro[] makeDescendents(Hashmap registo){
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
    int[][] visited = new int[size][size];
    Arrays.fill(visited,false);
    Queue fila = new Queue();
    int[] posicao = {x,y,0}; //pq o java nao e tao fixe como python e nao tem tuplos levando nos a fazer coisas feias
    fila.add(posicao);
    visited[x][y] = true;
    while(!fila.isEmpty()){
      posicao = fila.poll();
      if(target[posicao[0]][posicao[1]]==posic[x][y]){
        return posicao[0];
      }
      for (int i = 0;i < size ;++i) {
        if (Ponto.isValid(posicao[0]+moveX[i],posicao[1]+moveY[i])) {
          int[] aux = {posicao[0]+moveX[i],posicao[1]+moveY[i],posicao[2]+1};
          fila.add(aux);
        }
      }
    }
    System.err.println("Deu problemas o distToEnd!!!!");
    return null;
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

//
//Implementacao de node
//

class Node implements Comparable<Node>{
  private int altura;
  private Tabuleiro tabu;
  private int heuristica = null;

  public Node(Tabuleiro tabu, int altura, Boolean heur){
    this.altura = altura;
    this.tabu = tabu;
    if (heur) { // se for true efetua-se o calculo da heuristica

    }
  }

  @Override
   public int compareTo(Tabuleiro p) {
    if(this.heuristica < p.heuristica)
      return -1;
    else
      return 1;
  }
}


// ---------- Metodos de enque
class Search{
  public HashMap map = null;
  public int max = -1; //maximod e profundidade se colocar -1 nao existe maximo
  public void add(Tabuleiro tabu, int altura,Ponto zero){}
}

class Gulosa extends Search{

}

class Astar extends Search{
  public PriorityQueue queue;

  public Astar(){
    queue = PriorityQueue();
  }
  @Override
  public void add(Tabuleiro tabu, int altura,Ponto zero){
    queue.add(new Node(tabu,altura,true));
  }
}

class IDFS{
  public LinkedList queue;

  public IDFS(){

  }
}

class Dfs extends Search{
    public LinkedList queue;

    public Dfs(int n){
      queue = new LinkedList();
      max = n;
    }

    @Override
    public void add(Tabuleiro tabu, int altura,Ponto zero){
      queue.addFirst(new Node(tabu,altura,false)); //nao imporaa ordem desde que seja adicionado no inicio
    }
}

class Bfs extends Search{
  public LinkedList queue;

  public Bfs(){
    map = new HashMap();
    queue = new LinkedList();
  }
  @Override
  public void add(Tabuleiro tabu, int altura,Ponto zero){
    if(!map.containsKey(tabu)){
      queue.add(new Node(tabu,altura,false)); //nos tem d eser acrescentados no final da fila
      map.put(tabu,zero);
    }
  }

}
//-------------

class Jogo15{
  private final int lado = 4;
  private Tabuleiro inicial;
  private Tabuleiro target;

  private void generalSearchAlgorithm(Search procura){
    //contadores
    long numeroNos = 0; //Numero de nos gerados
    //final = new Node()
    while (!queue.isEmpty()) {
      Node node = queue.pop();
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
      }
      else{
        if(procura.max != node.altura){
          for(Tabuleiro tabu: node.tabu.makeDescendents(procura.map)){
            procura.add(queue,tabu,node.altura+1,node.tabu.zero);
          }
        }
      }

    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int area = new int[lado][lado];

    //Leitura do tabuleiro inicial
    for (int i=0; i<lado; ++i)
      for (int j=0; j<lado; ++j)
        area[i][j] = input.nextInt();

    //criacao de tabuleiro inicial
    inicial = new Tabuleiro(area);

    //Leitura do tabuleiro final
    for (int i=0; i<lado; ++i)
      for (int j=0; j<lado; ++j)
        area[i][j] = input.nextInt();

    //criacao de tabuleiro final
    target = new Tabuleiro(area);

  }
}
