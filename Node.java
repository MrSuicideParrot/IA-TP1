class Node implements Comparable<Node>{
  int altura;
  public Tabuleiro tabu;
  public Integer heuristica = null;

  /*
  // tabu -> Tabuleiro a associar ao nó
  // altura -> altura do nó
  // heur -> tipo de heuristica: 0 altura + manhattan distance e 1 manhattan distance outros nao sao calculados
  // target -> tabuleiro ao qual queremos chegar
  */
  public Node(Tabuleiro tabu, int altura, int heur,Tabuleiro target){
    this.altura = altura;
    this.tabu = tabu;
    switch (heur){
      case 0: //A star
        heuristica = altura + tabu.distToEnd(target);
        break;

      case 1: //gulosa
        heuristica = tabu.distToEnd(target);
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
