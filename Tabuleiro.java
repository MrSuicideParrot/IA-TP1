import java.util.Queue;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;

class Tabuleiro{
  private final static int lado = 4;

  public Integer[][] posic; //matriz onde encontra-se guardado o tabuleiro
  public Ponto zero; //localizaçao do zero no tabuleiro atual
  public Tabuleiro pai; //apontador para o tabuleiro pai , null quando e o inicial

  //------------- Construtores-------------------------------------

  /*
  // Tabuleiro p2 -> Tabuleiro pai
  //Ponto target -> localização do novo zero que pretendemos, colocamos o conteudo do Ponto target
  //na posicao do zero de p2 e por fim colocamos a posicao p2 a 0
  */
  public Tabuleiro(Tabuleiro p2, Ponto target){
    this.posic = new Integer[lado][lado];
    pai = p2;
    Tabuleiro p1 = this;
    MatrixCopy(p1.posic,p2.posic);
    p1.zero = target;
    p1.posic[p2.zero.getX()][p2.zero.getY()]= new Integer(p1.posic[target.getX()][target.getY()]);
    p1.posic[target.getX()][target.getY()] = 0;
  }

  //Construcao de tabuleiro atravez de uma matriz
  public Tabuleiro(Integer[][] posic){
    pai = null;
    this.posic = new Integer[lado][lado];
    MatrixCopy(this.posic,posic);
    this.zero = findZero();
  }
// ----------------------------------------------------

//efetua uma copia completa de uma matriz
  public void MatrixCopy(Integer[][] dest,Integer[][] source){
    for (int i = 0;i < lado ;++i )
      for (int j = 0;j <lado ;++j )
        dest[i][j] = new Integer(source[i][j]);
  }

  //imprime o caminho que deu origem ao tabuleiro this, do mais profundo para menos profundo
  public void caminhoPrint(int altura){
    Tabuleiro tabu = this;
    while(tabu != null){
      System.out.println("Nó de profundiade: "+altura);
      --altura;
      System.out.println(tabu);
      tabu = tabu.pai;
    }
  }

  /*
  //Verificar se e possivel chegar a posicao final
  //Tabuleiro targetTab -> tabuleiro com posicao final
  */
  public Boolean isNotImpossible(Tabuleiro targetTab){  //retirar argumento
    if(this.equals(targetTab)) //se forem o mesmo tabuleiro retorna true
      return true;

    int[] inicial = new int[lado*lado];
    int[] target = new int[lado*lado];

    //organizacao das duas matrizes em vetores correpondentes
    int number1=0,number2 = 0;
    for (int i =0 ; i < lado; ++i)
      for(int j= 0; j < lado;++j){
          inicial[number1] = this.posic[i][j];
          target[number1] = targetTab.posic[i][j];
          ++number1;
      }

    //calculo da paridade dos vetores
    number1 = 0;
    for (int i = 0;i < lado*lado ;i++ ) {
      for (int j = i+1; j < lado*lado;j++){
          if(inicial[j]!=0&&inicial[i]>inicial[j]){
            number1+=1;
          if(target[j]!=0 && target[i]>target[j])
            number2+=1;
          }
      }
    }
    return ((((4-targetTab.zero.getY())%2 == 1)==(number2%2 ==0)) == ((4-zero.getY())%2 == 1 == (number1%2 ==0)));
}

/*
//Função que retorna uma linked list com os descendentes
//Esta funcao e aquela que se encontra preparada para trabalhar com HashSet
//O HashSet quando nao se encontra a null, significa que e necessario efetuar uam verificacao
//de nos repetidos
*/
  public LinkedList<Tabuleiro> makeDescendents(HashSet registo){
    LinkedList<Tabuleiro> descendentes = new LinkedList<Tabuleiro>();
    int[] moveX ={0, 1, 0, -1};
    int[] moveY ={-1, 0, 1, 0};
    for(int i = 0; i < lado; ++i){
      Ponto ponto = new Ponto(zero.getX()+moveX[i],zero.getY()+moveY[i]);
      if(!ponto.isValid()) //verificacao se o ponto que estamos a tentar criar e valido
        continue;
      Tabuleiro tabu = new Tabuleiro(this, ponto);
      if(pai!=null && tabu.equals(pai)) //evita que sejam calculados nos pais
        continue;
      if(registo != null){
        if(registo.contains(tabu))
          continue;
      }
      descendentes.addFirst(tabu); //adiciona a lista de descendentes
    }
    return descendentes; //retorna a lista de descendentes
  }

  /*
  //Funcao com o memso trabalho que a anterior no entanto encontra se preparada
  //para utilizar HashMap
  */
  public LinkedList<Tabuleiro> makeDescendentsM(HashMap registo){
    LinkedList<Tabuleiro> descendentes = new LinkedList<Tabuleiro>();
    int[] moveX ={0, 1, 0, -1};
    int[] moveY ={-1, 0, 1, 0};
    for(int i = 0; i < lado; ++i){
      Ponto ponto = new Ponto(zero.getX()+moveX[i],zero.getY()+moveY[i]);
      if(!ponto.isValid())
        continue;
      Tabuleiro tabu = new Tabuleiro(this, ponto);
      if(pai!=null && tabu.equals(pai)) //evita que sejam calculados nos pais
        continue;
      if(registo != null){
        if(registo.containsKey(tabu))
          continue;
      }
      descendentes.addFirst(tabu);
    }
    return descendentes;
  }


  //-------------calculo da distancia de manhattan----------------------
  //Tabuleiro target -> posicao final a aqual queremos chegar
  public int distToEnd(Tabuleiro target){
    int aux = 0;
    for (int i = 0;i < lado; ++i)
      for (int j = 0;j < lado; ++j)
          aux += distToEnd(i,j,target);
    return aux;
  }

  private int distToEnd(int x, int y, Tabuleiro target){
    int[] moveX = {0, 1, 0, -1};
    int[] moveY = {-1, 0, 1, 0};
    Boolean[][] visited = new Boolean[lado][lado];

    //inicia visited Array a falso
    for (int i = 0;i <lado ;++i )
      for (int j = 0;j <lado ;++j )
        visited[i][j] = false;

    Queue<Integer[]> fila = new LinkedList<Integer[]>();
    Integer[] posicao = {x,y,0}; //pq o java nao e tao fixe como python e nao tem tuplos levando nos a fazer coisas feias
    fila.add(posicao);
    visited[x][y] = true;
    while(!fila.isEmpty()){
      posicao = fila.poll();
      if(target.posic[posicao[0]][posicao[1]].equals(posic[x][y])){
        return posicao[2];
      }
      for (int i = 0;i < lado ;++i) {
        if (Ponto.isValid(posicao[0]+moveX[i],posicao[1]+moveY[i])) {
          Integer aux[] = {posicao[0]+moveX[i],posicao[1]+moveY[i],posicao[2]+1};
          fila.add(aux);
        }
      }
    }
    System.err.println("Deu problemas o distToEnd!!!!");
    return -1;
  }

  //---------------------------------------------------------------------

  public String toString() {
    String s = new String();
    for (int i = 0;i < lado;++i ) {
      for (int j = 0; j < lado;++j){
          s += " " + posic[i][j];
      }
      s += "\n";
    }
    return s;
  }

  //encontra a posicao do zero
  private Ponto findZero(){
    for (int i=0;i<lado;++i)
      for (int j=0;j<lado;++j)
        if(posic[i][j]==0)
          return new Ponto(i,j);

    //caso nao seja encontrado nenhum zero para o programa
    System.err.println("Zero not found!");
    System.exit(1);
    return null ;
  }

  //Imprime uma matriz
  private void printMATRIX(Integer[][] matriz){
    for (int i = 0;i < lado ;++i ) {
      for (int j = 0;j < lado ; ++j) {
        System.out.print(" "+matriz[i][j]);
      }
      System.out.println();
    }
  }

  @Override //calcula o hashcode de um Tabuleiro
  public int hashCode(){
    return this.toString().hashCode();
  }

  @Override
  public boolean equals(Object no){
    Tabuleiro p2 = (Tabuleiro) no;
    Tabuleiro p1 = this;

    for (int i=0;i<lado;++i)
      for (int j=0;j<lado;++j)
        if(!p1.posic[i][j].equals(p2.posic[i][j]))
          return false;

    return true;
  }

}
