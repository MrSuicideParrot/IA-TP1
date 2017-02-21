class Node implements Comparable<Node>{
  int altura;
  public Tabuleiro tabu;
  private Integer heuristica = null;

  public Node(Tabuleiro tabu, int altura, int heur,Tabuleiro target){
    this.altura = altura;
    this.tabu = tabu;
    switch (heur){
      case 0: //A star
        heuristica = altura + tabu.distToEnd(target);
        break;
      case 1: //gulosa
        heuristica = altura;
        break;
      default:
        break;
    }
  }

  @Override
  public int compareTo(Node p) {
    if(this.heuristica < p.heuristica)
      return -1;
    else
      return 1;
  }
}
