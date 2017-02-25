import java.util.Scanner;


class Jogo15{
  private final static int lado = 4;
  public static Tabuleiro inicial = null;
  public static Tabuleiro target = null;

  public static void main(String[] args) {
    /*
    /Tratamento de argumentos
    /*/
    if(args.length == 0){
      //Imprimir o help
      return;
    }
    scannerTabuleiro();
    if(!inicial.isNotImpossible(target)){
      System.out.println("Tabuleiro sem solução!");
      return;
    }
    long startTime = System.currentTimeMillis();
    switch (args[0].toUpperCase()) {
      case "ASTAR":
        Astar search1 = new Astar(inicial,target,startTime);
        search1.generalSearchAlgorithm();
        break;

      case "GULOSO":
        Gulosa search2 = new Gulosa(inicial,target,startTime);
        search2.generalSearchAlgorithm();
        break;

      case "DFS":
        Dfs search3 = new Dfs(inicial,target,startTime);
        search3.generalSearchAlgorithm();
        break;

      case "IDFS":
        if(args.length != 2){
            System.err.println("Erro: Profundiade do IDFS não inserida!");
            System.exit(1);
        }
        IDfs search5 = new IDfs(inicial,target,Integer.parseInt(args[1]),startTime);
        search5.generalSearchAlgorithm();
        break;

      case "BFS":
        Bfs search4 = new Bfs(inicial,target,startTime);
        search4.generalSearchAlgorithm();
        break;

      default:
        System.err.println("Erro: Opção não reconhecida!");
        break;
    }
  }

  private static void scannerTabuleiro(){
    Scanner input = new Scanner(System.in);
    Integer[][] area = new Integer[lado][lado];

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

  }
}
