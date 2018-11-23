package tresenraya;

import newGui.player.Form;

public class JugarTresEnRaya {

  private TresEnRaya tresEnRaya;
  private Jugador jugadorX;
  private Jugador jugadorO;
  private Form formPlayer;

  public JugarTresEnRaya() {
     this.tresEnRaya = new TresEnRaya();
  }

  public void cargarJugadores(Jugador jugadorX, Jugador jugadorO) {
     this.jugadorX =  jugadorX;
     this.jugadorX.setFicha("X");
     this.jugadorO =  jugadorO;
     this.jugadorO.setFicha("O");
  }

  public void jugarConMaquina() {
    this.cargarJugadores(new Jugador("Computadora"), new Jugador("Anonimo"));
  }

  public void reanudarJuego() {
    this.tresEnRaya.reiniciarJuego();
    this.cargarJugadores(this.jugadorX, this.jugadorO);
  }

  public String getGanador() {
    String  nombre = " ";
    if (this.tresEnRaya.obtenerGanador() == 'X') {
        nombre = this.jugadorX.getNombre();
    } else  if (this.tresEnRaya.obtenerGanador() == 'O') {
        nombre = this.jugadorO.getNombre();
    } else {
        nombre = "";
    }
   return nombre;
  }

  public int jugarMaquina() {
    return this.posicionAleatoria();
  }

  public void jugarJugador(int x, int y) {
     this.hacerJugada(new Posicion(x, y));
  }

  public boolean estadoTresEnRaya() {
     return this.tresEnRaya.terminado();
  }

  public boolean exitJugadorMaquina() {
    boolean existe =  false;
     if ( this.jugadorX.getNombre() == "Computadora") {
        existe = true;
     }
    return existe;
  }

  public Jugador getJudador(String type) {
    Jugador jugador;
    if ( type == "X") {
       jugador = this.jugadorX;
    } else {
       jugador = this.jugadorO;
    }
    return jugador;
  }

  public void addJugadorTablaDePosiciones(String name, boolean ganado) {
    this.formPlayer = new Form();
    this.formPlayer.setJugador(this.getJudador(Character.toString(this.turno())));
    this.formPlayer.addBoardPosition(name, ganado);
  }

  public char turno() {
     char turnoActual = ' ';
     if ( this.tresEnRaya.esTurnoX()) {
         turnoActual = 'X';
     } else {
         turnoActual = 'O';
     }
     return turnoActual;
  }

  private void hacerJugada(Posicion posicion) {
    if ( this.tresEnRaya.esTurnoX()) {
       this.tresEnRaya.hacerJugadaX(posicion);
    } else  {
       this.tresEnRaya.hacerJugadaO(posicion);
    }
  }

  public int posicionAleatoria() {
    return  ( 1 + ( int ) ( Math.random() * 9 ) );
  }

}
