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
    //if(inicial.isNotImpossible(target)){
      //System.out.println("Astar:");
      //Astar search1 = new Astar(inicial,target);
      //search1.generalSearchAlgorithm();
      //System.out.println("Gulosa:");
      //Gulosa search2 = new Gulosa(inicial,target);
      //search2.generalSearchAlgorithm();
      //System.out.println("Dfs:");
      //Dfs search3 = new Dfs(inicial,target);
      //search3.generalSearchAlgorithm();
      //System.out.println("IDFS:");
      //IDfs search3 = new IDfs(inicial,target,50);
      //search3.generalSearchAlgorithm();

      System.out.println("Bfs:");
      Bfs search4 = new Bfs(inicial,target);
      search4.generalSearchAlgorithm();
  //  }
  //  else
    //  System.out.println("isImpossible");
  }
}
