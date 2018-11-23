package tresenraya;

public class Tablero {

  private Pieza[][] tablero;
  private int tamanoTablero;

  public Tablero(int filas, int columnas) {
    tablero = new Pieza[filas][columnas];
    this.tamanoTablero =  this.tablero.length;
    this.limpiarTablero();
  }

  public Pieza[][] getTablero() {
    return tablero;
  }

  public void setTablero(Pieza[][] tablero) {
    this.tablero = tablero;
  }

  public Pieza getPieza(Posicion posicion) {
    return this.tablero[posicion.fila][posicion.columna];
  }

  public void setPieza(Posicion posicion, Ficha ficha) {
    this.tablero[posicion.fila][posicion.columna].setFicha(ficha);
  }

  public void limpiarTablero() {
    for (int fila = 0; fila < this.tamanoTablero; fila++) {
      for (int columna = 0; columna < this.tamanoTablero; columna++) {
        this.tablero[fila][columna] = new Pieza(Ficha.VACIA);
        this.tablero[fila][columna].setPosicion(new Posicion(fila, columna));
      }
    }
  }

  public boolean tableroLleno() {
    boolean lleno = true;
    for (int fila = 0; fila < this.tamanoTablero; fila++) {
      for (int columna = 0; columna < this.tamanoTablero; columna++) {
        if (this.tablero[fila][columna].getFicha().name().equals(Ficha.VACIA)) {
          lleno = false;
          break;
        }
      }
    }
    return lleno;
  }

  public boolean verificarDiagonal(Ficha ficha) {
    boolean existe = false;
    if ( this.getFichaPieza(0,0).equals(ficha.name()) && this.getFichaPieza(1,1).equals(ficha.name()) && this.getFichaPieza(2,2).equals(ficha.name())) {
      existe = true;
    } else if (this.getFichaPieza(0,2).equals(ficha.name()) && this.getFichaPieza(1,1).equals(ficha.name())  && this.getFichaPieza(2,0).equals(ficha.name())) {
      existe = true;
    }
    return existe;
  }

  public boolean verificarFilas(Ficha ficha) {
    boolean existe = false;
    for (int fila = 0; fila <this.tamanoTablero ; fila++) {
      if (this.getFichaPieza(fila, 0).equals(ficha.name())  && this.getFichaPieza(fila, 1).equals(ficha.name()) && this.getFichaPieza(fila,2).equals(ficha.name())) {
        existe = true;
      }
    }
    return existe;
  }

  public boolean verificarColumnas(Ficha ficha) {
    boolean existe = false;
    for (int columna = 0; columna < tamanoTablero ; columna++) {
      if (this.getFichaPieza(0,columna).equals(ficha.name()) && this.getFichaPieza(1,columna).equals(ficha.name())  &&  this.getFichaPieza(2,columna).equals(ficha.name()) ) {
        existe = true;
      }
    }
    return  existe;
  }

  private String getFichaPieza(int x, int y) {
    return  this.tablero[x][y].getFicha().name();
  }


  public void imprimirTablero() {
    for (int fila = 0; fila < this.tablero.length; fila++) {
      System.out.print("|");
      for (int columna = 0; columna < this.tablero.length; columna++) {
        System.out.print(this.tablero[fila][columna].getFicha().name());
        if (columna != this.tablero[fila].length - 1) System.out.print("\t");
      }
      System.out.println("|");
    }
  }
}
