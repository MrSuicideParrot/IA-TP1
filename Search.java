import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;


class Astar {
  public PriorityQueue<Node> queue; //= new PriorityQueue<Node>();
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashMap mapa = null;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Astar(Tabuleiro inicial,Tabuleiro target){
    queue = new PriorityQueue<Node>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,0,target));
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
      }
      else{
        //if(procura.max != node.altura){
          for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
            queue.add(new Node(tabu, node.altura+1,0,target));
          }
        //}
      }

    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

/*

class Dfs {
    private LinkedList<Node> queue;

    public Dfs(int n){
      queue = new LinkedList<Node>();
      max = n;
    }

    @Override
    public Boolean isEmpty(){
      return queue.isEmpty();
    }

    @Override
    public Node poll(){
      return queue.poll();
    }

    @Override
    public void add(Tabuleiro tabu, int altura,Ponto zero){
      queue.addFirst(new Node(tabu,altura,false)); //nao imporaa ordem desde que seja adicionado no inicio
    }
}

class Bfs {
  private LinkedList<Node> queue;
  private Map<Integer,Ponto> mapa;

  public Bfs(){
    mapa = new HashMap<Integer,Ponto>();
    queue = new LinkedList<Node>();
  }

  @Override
  public Boolean isEmpty(){
    return queue.isEmpty();
  }

  @Override
  public Node poll(){
    return queue.poll();
  }

  @Override
  public void add(Tabuleiro tabu, int altura,Ponto zero){
    if(!mapa.containsKey(tabu)){
      queue.add(new Node(tabu,altura,false)); //nos tem d eser acrescentados no final da fila
      map.put(tabu,zero)
    }
  }

*/
