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
  public Boolean isNotImpossible(Tabuleiro target){
    int[] inicial = new int[lado*lado];

    //organizacao em vetor
    int number = 0;
    for (int i =0 ; i < size; ++i)
      for(int j= 0; j <s ize;++j){
          inicial[number] = this.posic[i][j];
          ++number;
      }

    //calculo da paridade da matriz
    number = 0
    for (int i = 0;i < size*size ;++i ) {
      for (int j = i; j < size*size;++j){
          if(inicial[i]>inicial[j])
            number+=1;
      }
    }

      // ( (grid width odd) && (#inversions even) )  ||  ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) )
      // odd -> impar
      // even -> par
      //logo (blank on odd row from bottom) == (#inversions even))
    return(4-zero.getY()%2 == number%2);
  }

  public String toString() {
    String s = new String();
    for (int i = 0;i < size;++i ) {
      for (int j = 0; j < size;++j){
          s += " " + posic[i][j];
      }
      s += "\n";
    }
    return s;
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
