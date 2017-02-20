import java.util.Scanner;

class Jogo15{
  private final static int lado = 4;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Integer[][] area = new Integer[lado][lado];
    Tabuleiro inicial;
    Tabuleiro target;

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
    target = new Tabuleiro(area);

    if(inicial.isNotImpossible(target)){
      Astar search = new Astar(inicial,target);
      search.generalSearchAlgorithm();
    }
    else
      System.out.println("isImpossible");
  }
}
