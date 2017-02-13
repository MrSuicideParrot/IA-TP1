import Tabuleiro;
import java.util.Scanner;

class Jogo15{
  private final int lado = 4;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int area = new int[lado][lado]();
    Tabuleiro inicial;
    Tabuleiro final;

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
    final = new Tabuleiro(area);

  }
}
