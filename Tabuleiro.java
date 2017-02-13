import Ponto;
class Tabuleiro implements Comparable<Tabuleiro>{
  private final int lado = 4;

  private Integer[][] posic;
  private Ponto zero;
  
  Tabuleiro(Tabuleiro t2){

  }
  public Tabuleiro(int[][] posic){
    posic = new Integer[lado][lado];
    this.posic = posic;
    this.zero = findZero();
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

  @Override
   public int compareTo(Tabuleiro p) {

   }
}
