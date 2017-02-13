import Ponto;
class Tabuleiro implements Comparable<Tabuleiro>{
  private final int lado = 4;

  private Integer[][] posic;
  private Ponto zero;

  public Tabuleiro(Tabuleiro t2){

  }

  public Tabuleiro(int[][] posic){
    posic = new Integer[lado][lado](posic);
    this.zero = findZero();
  }

  //--------Verificar se e possivel chegar a posicao final
  public Boolean isNotImpossible(){

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
  /*
  Vamos ver ainda como e que vamos implementar a igaualdade usando isto ou a hash
  @Override
   public int compareTo(Tabuleiro p) {

   }

   @Override
   public int equals(){

   }
   */
}
