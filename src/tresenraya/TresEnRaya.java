package tresenraya;

public class TresEnRaya {

  private Tablero tablero;
  private char turno;
  private boolean estado;
  private int movimiento;
  private char ganador;

  public TresEnRaya() {
    this.tablero = new Tablero(3,3);
    this.estado = true;
    this.movimiento = 0;
    this.turno = 'X';
  }

  public void hacerJugadaX(Posicion posicion) {
      if ( esTurnoX() && this.estado && casillaDisponible(posicion)) {
         this.marcarCasilla(posicion, Ficha.X);
         this.ganoTurnoActual();
      }
  }

  public void hacerJugadaO(Posicion posicion) {
      if (!esTurnoX() && this.estado && casillaDisponible(posicion)) {
         this.marcarCasilla(posicion, Ficha.O);
         this.ganoTurnoActual();
      }
  }

  private void marcarCasilla(Posicion posicion, Ficha ficha) {
    this.tablero.setPieza(posicion, ficha);
    this.movimiento++;
  }

  private boolean casillaDisponible(Posicion posicion) {
    boolean vacia = false;
    if ( this.tablero.getPieza(posicion).getFicha().name().equals(Ficha.VACIA.name())) {
       vacia = true;
    }
    return  vacia;
  }

  public boolean terminado() {
    boolean terminado = false;
    if (movimiento == 9 || !this.estado) {
      terminado =  true;
    }
    return terminado;
  }

  public boolean esTurnoX() {
     boolean turnoX = false;
     if(this.turno == 'X') {
       turnoX = true;
     }
     return  turnoX;
  }
  public void reiniciarJuego() {
    this.tablero = new Tablero(3,3);
    this.estado = true;
    this.movimiento = 0;
    this.turno = 'X';
  }

  public char obtenerGanador() {
    return this.ganador;
  }

  private void ganoTurnoActual() {
    if ( movimiento > 3) {
      if ( this.esTurnoX() ) {
         this.buscarGanador(Ficha.X);
      } else if (!this.esTurnoX()) {
         this.buscarGanador(Ficha.O);
      }
    }
    this.changeTurno();
  }

  private void changeTurno() {
    if ( estado) {
      if ( this.turno == 'X') {
        this.turno = 'O';
      } else {
        this.turno = 'X';
      }
    }
  }

  private void buscarGanador(Ficha ficha) {
    if (this.tablero.verificarFilas(ficha) || this.tablero.verificarDiagonal(ficha) || this.tablero.verificarColumnas(ficha)) {
      this.ganador = this.turno;
      this.estado = false;
    } else {
      if ( movimiento == 9) {
        this.ganador = ' ';
        this.estado = false;
      }
    }
  }
}
