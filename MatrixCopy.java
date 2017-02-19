class MatrixCopy{
  private final static lado = 4;
  public static void MatrixCopyFunc(Integer[][] dest,Integer[][] source){
    dest = new Integer[lado][lado];
    for (int i = 0;i < lado ;++i ) {
      for (int j = 0;j <lado ;++j ) {
        dest[i][j] = source[i][j];
      }
    }
  }
}
