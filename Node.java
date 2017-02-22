class Node implements Comparable<Node>{
  int altura;
  public Tabuleiro tabu;
  public Integer heuristica = null;

  public Node(Tabuleiro tabu, int altura, int heur,Tabuleiro target){
    this.altura = altura;
    this.tabu = tabu;
    switch (heur){
      case 0: //A star
        heuristica = altura + tabu.distToEnd(target);
        //System.out.println(heuristica);
        //System.exit(0);
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

class Info{
  public Ponto zero;
  public Integer heuristica;

  Info(Ponto zero, Integer heuristica){
    this.zero = zero;
    this.heuristica = heuristica;
  }
}
