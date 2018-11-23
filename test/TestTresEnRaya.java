import tresenraya.Posicion;
import tresenraya.TresEnRaya;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TestTresEnRaya {
  TresEnRaya juego;

  @Before
  public void setUp() {
    juego = new TresEnRaya();
  }

  public List<Posicion> cargarJuegoGanaX() {
    List<Posicion> jugadas = new ArrayList<Posicion>();

    jugadas.add(new Posicion(1, 1));
    jugadas.add(new Posicion(0, 1));
    jugadas.add(new Posicion(1, 0));
    jugadas.add(new Posicion(1, 2));
    jugadas.add(new Posicion(2, 0));
    jugadas.add(new Posicion(0, 0));
    jugadas.add(new Posicion(0, 2));

    return jugadas;
  }

  public List<Posicion> cargarJuegoGanaO() {
    List<Posicion> jugadas = new ArrayList<Posicion>();

    jugadas.add(new Posicion(0, 0));
    jugadas.add(new Posicion(1, 1));
    jugadas.add(new Posicion(0, 1));
    jugadas.add(new Posicion(0, 2));
    jugadas.add(new Posicion(1, 0));
    jugadas.add(new Posicion(2, 0));

    return jugadas;
  }

  public List<Posicion> cargarJuegoEmpate() {
    List<Posicion> jugadas = new ArrayList<Posicion>();
    jugadas.add(new Posicion(1, 1));
    jugadas.add(new Posicion(0, 1));
    jugadas.add(new Posicion(0, 0));
    jugadas.add(new Posicion(2, 2));
    jugadas.add(new Posicion(2, 1));
    jugadas.add(new Posicion(0, 1));
    jugadas.add(new Posicion(2, 0));
    jugadas.add(new Posicion(0, 2));
    jugadas.add(new Posicion(1, 2));
    jugadas.add(new Posicion(1, 0));
    jugadas.add(new Posicion(2, 0));
    return jugadas;
  }

  public char jugar(TresEnRaya juego, List<Posicion> jugadas) {

    ListIterator<Posicion> iteradorJugadas = jugadas.listIterator();
    Posicion posicion;
    while (iteradorJugadas.hasNext()) {
      posicion = iteradorJugadas.next();
      juego.hacerJugadaX(posicion);
      if (juego.terminado() || !iteradorJugadas.hasNext()) {
        break;
      } else {
        posicion = iteradorJugadas.next();
        juego.hacerJugadaO(posicion);
        if (juego.terminado()) {
          break;
        }
      }
    }
    return juego.obtenerGanador();
  }

  @Test
  public void testXEsGanador() {
    char jugadorX = 'X';
    char ganador = jugar(juego, cargarJuegoGanaX());
    Assert.assertEquals(ganador, jugadorX);
  }

  @Test
  public void testOEsGanador() {
    char jugadorO = 'O';
    char ganador = jugar(juego, cargarJuegoGanaO());
    Assert.assertEquals(ganador, jugadorO);
  }

  @Test
  public void testEmpate() {
    char ganador = jugar(juego, cargarJuegoEmpate());
    Assert.assertTrue(ganador != 'X' && ganador != 'O');
  }

  @Test
  public void testTurno() {
     juego.hacerJugadaX(new Posicion(1,1));
     juego.hacerJugadaO(new Posicion(2,2));
     Assert.assertEquals(true, juego.esTurnoX());
  }

  @Test
  public void testJuegoReiniciado() {
    jugar(juego, cargarJuegoGanaX());
    juego.reiniciarJuego();
    Assert.assertEquals(true, juego.esTurnoX());
  }
  @Test
  public void testTerminado() {
    jugar(juego, cargarJuegoGanaX());
    Assert.assertEquals(true, juego.terminado());
  }

}