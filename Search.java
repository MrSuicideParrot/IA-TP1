import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
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
      //System.out.println(node.tabu);
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
        return;
      }
      else{
        //if(procura.max != node.altura){
          for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
          //  System.out.println(tabu);
            queue.add(new Node(tabu, node.altura+1,0,target));
          }
          //return;
        //}
      }

    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

class Gulosa {
  public PriorityQueue<Node> queue; //= new PriorityQueue<Node>();
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashMap mapa = null;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Gulosa(Tabuleiro inicial,Tabuleiro target){
    queue = new PriorityQueue<Node>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,1,target));
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      //System.out.println(node.tabu);
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
        return;
      }
      else{
        //if(procura.max != node.altura){
          for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
          //  System.out.println(tabu);
            queue.add(new Node(tabu, node.altura+1,1,target));
          }
          //return;
        //}
      }

    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

class Dfs {
  public LinkedList<Node> queue; //= new PriorityQueue<Node>();
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashMap<Tabuleiro,Ponto> mapa = null;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Dfs(Tabuleiro inicial,Tabuleiro target){
    queue = new LinkedList<Node>();
    mapa = new HashMap<Tabuleiro,Ponto>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,1,target));
    mapa.put(inicial,null);
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      //System.out.println(node.tabu);
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
        return;
      }
      else{
          //try {
            boolean flag = false;
            for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
              flag = true;
              queue.add(new Node(tabu, node.altura+1,1,target));
            }
            if(!flag)
              mapa.remove(node.tabu);
          /*}
          catch(NullPointerException e){
            mapa.remove(node.tabu);
          }*/
      }
    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}

class IDfs {
  public LinkedList<Node> queue; //= new PriorityQueue<Node>();
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashMap<Tabuleiro,Ponto> mapa = null;
  private int max;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public IDfs(Tabuleiro inicial,Tabuleiro target, int max){
    queue = new LinkedList<Node>();
    this.inicial = inicial;
    this.target = target;
    this.max = max;
    nosGerados = 0;
    nosVisitados = 0;
  }
  public void generalSearchAlgorithm(){
    for (int i = 1;i<max ;++i ) {
      Integer resul = generalSearchAlgorithm(i);
      if(resul != null){
        System.out.println("Numero minimo de jogadas encontradas:"+" "+resul);
        break;
      }
    }
  }

  private Integer generalSearchAlgorithm(int x){
    queue.add(new Node(inicial,0,1,target));
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      //System.out.println(node.tabu);
      if (node.tabu.equals(target)) {
        //System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
        return node.altura;
      }
      else{
          if(node.altura<x)
            for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
              queue.add(new Node(tabu, node.altura+1,1,target));
            }
          /*}
          catch(NullPointerException e){
            mapa.remove(node.tabu);
          }*/
      }
    }
    return null;
  }
}

class Bfs {
  public Queue<Node> queue; //= new PriorityQueue<Node>();
  private Tabuleiro inicial;
  private Tabuleiro target;
  public HashMap<Tabuleiro,Ponto> mapa = null;

  //contadores
  private int nosGerados;
  private int nosVisitados;

  public Bfs(Tabuleiro inicial,Tabuleiro target){
    queue = new LinkedList<Node>();
    //mapa = new HashMap<Tabuleiro,Ponto>();
    this.inicial = inicial;
    this.target = target;
    nosGerados = 0;
    nosVisitados = 0;
  }

  public void generalSearchAlgorithm(){
    queue.add(new Node(inicial,0,1,target));
    //mapa.put(inicial,null);
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      ++nosVisitados;
      //System.out.println(node.tabu);
      if (node.tabu.equals(target)) {
        System.out.println("Numero minimo de jogadas encontradas:"+" "+node.altura);
        return;
      }
      else{
          //try {
            for(Tabuleiro tabu: node.tabu.makeDescendents(mapa)){
              queue.add(new Node(tabu, node.altura+1,1,target));
            }
          /*}
          catch(NullPointerException e){
            mapa.remove(node.tabu);
          }*/
      }
    }
    System.err.println("Erro: Solucao nao encontrada!!!");
  }
}
