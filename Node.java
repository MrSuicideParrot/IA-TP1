import Tabuleiro;

class Node{
  private int altura;
  private Tabuleiro tabu;

  public Node(Tabuleiro tabu, int altura){
    this.altura = altura;
    this.tabu = tabu;
  }
}
