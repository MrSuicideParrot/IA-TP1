import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;

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
      map.put(tabu,zero)
    }
  }

}
//-------------

class Jogo15{
  private final int lado = 4;

  private void generalSearchAlgorithm(Search procura){
    //contadores
    long numeroNos = 0; //Numero de nos gerados
    //final = new Node()
    while (!queue.isEmpty()) {
      Node node = queue.pop()
      if (node.tabu.equals(final)) {
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
    int area = new int[lado][lado]();
    Tabuleiro inicial;
    Tabuleiro final;

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
    final = new Tabuleiro(area);

  }
}
