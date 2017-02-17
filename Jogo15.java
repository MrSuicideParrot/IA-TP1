import Tabuleiro;
import java.util.Scanner;
import LinkedList;
import Node;

class Jogo15{
  private final int lado = 4;

  private void generalSearchAlgorithm(Callable<t> func){
    //contadores
    long numeroNos = 0; //Numero de nos gerados
    //final = new Node()
    LinkedList queue = new LinkedList();
    while (!queue.isEmpty()) {
      Node node = queue.pop()
      if (node.tabu.equals(final)) {

      }
      else{
        for(Tabuleiro tabu: node.tabu.makeDescendents()){

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
