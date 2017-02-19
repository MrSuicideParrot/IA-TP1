public interface Search{
  void add(Tabuleiro tabu, int altura,Ponto zero);
  Node poll();
  Boolean isEmpty();
}
/*
public class Gulosa implements Search{

  Gulosa(){

  }

} */

class Astar implements Search{
  private PriorityQueue queue;

  public Astar(){
    queue = PriorityQueue();
  }

  @Override
  public Boolean isEmpty(){
    return queue.isEmpty();
  }

  @Override
  public Node poll(){
    return queue.poll()
  }

  @Override
  public void add(Tabuleiro tabu, int altura,Ponto zero){
    queue.add(new Node(tabu,altura,true));
  }
}

class Dfs implements Search{
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

class Bfs implements Search{
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
