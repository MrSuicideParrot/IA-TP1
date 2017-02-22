import java.util.Scanner;
import java.util.Set;
import java.util.*;
class Teste{
  private final static int lado = 4;
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Integer[][] area = new Integer[lado][lado];
    HashSet<Tabuleiro> sete = new HashSet<Tabuleiro>();
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

    target = new Tabuleiro(area);

    sete.add(inicial);
    System.out.println(sete);
    System.out.println("equals"+target.equals(inicial)+" "+ sete.contains(target));
    sete.add(target);
    System.out.println(sete);
  }
}
