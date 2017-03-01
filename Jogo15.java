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
      System.out.println("Erro: nenhum argumento!");
      printBanner();
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

      case "GULOSA":
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

      case "HELP":
        printBanner();
      default:
        System.err.println("Erro: Opção não reconhecida!");
        printBanner();
        break;
    }
  }
  private static void printBanner(){
    System.out.println("Jogo15");
    System.out.println("Uso: Jogo15 [Tipo de busca] [Opções de busca]");
    System.out.println("Tipos de busca:");
    System.out.println("  ASTAR -> efetua busca A estrela;\n  GULOSA -> efetua busca Gulosa;\n  BFS -> efetua busca em largura;\n  DFS -> efetua busca em profundiade;\n  IDFS [profundiade maxima] -> efetua busca em profundiade, onde profundiade maxima é o seu limite.");
    System.out.println("Exemplo:\n  $Jogo15 IDFS 50");
  }
  private static void scannerTabuleiro(){
    Scanner input = new Scanner(System.in);
    Integer[][] area = new Integer[lado][lado];

    //Leitura do tabuleiro inicial
    System.out.println("Insira o tabuleiro inical:");
    for (int i=0; i<lado; ++i)
      for (int j=0; j<lado; ++j)
        area[i][j] = input.nextInt();

    //criacao de tabuleiro inicial
    inicial = new Tabuleiro(area);
    if(!inicial.isValid()){
      System.out.println("Erro: Tabuleiro inválido!");
      System.exit(0);
    }

    //Leitura do tabuleiro final
    System.out.println("Insira o tabuleiro finnal:");
    for (int i=0; i<lado; ++i)
      for (int j=0; j<lado; ++j)
        area[i][j] = input.nextInt();

    //criacao de tabuleiro final
    target = new Tabuleiro(area);
    if(!target.isValid()){
      System.out.println("Erro: Tabuleiro inválido!");
      System.exit(0);
    }
    System.out.println("\n");
  }
}
