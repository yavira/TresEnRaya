import tresenraya.Ficha;
import tresenraya.Pieza;
import tresenraya.Posicion;
import tresenraya.Tablero;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableroTest {
  Tablero tablero;

  @Before
  public void setUp() {
    tablero = new Tablero(3, 3);
  }

  @Test
  public void testTableroVacio() {
    this.tablero.limpiarTablero();
    Pieza[][] res = tablero.getTablero();
    Pieza tab[][] = new Pieza[3][3];
    tab[0][0] = new Pieza(Ficha.VACIA);
    tab[0][1] = new Pieza(Ficha.VACIA);
    tab[0][2] = new Pieza(Ficha.VACIA);
    tab[1][0] = new Pieza(Ficha.VACIA);
    tab[1][1] = new Pieza(Ficha.VACIA);
    tab[1][2] = new Pieza(Ficha.VACIA);
    tab[2][0] = new Pieza(Ficha.VACIA);
    tab[2][1] = new Pieza(Ficha.VACIA);
    tab[2][2] = new Pieza(Ficha.VACIA);
    assertEquals(tab[1][1].getFicha(), this.tablero.getPieza(new Posicion(1,1)).getFicha());
  }

  @Test
  public void testVerificarDiagonal() {
    this.tablero.limpiarTablero();
    Pieza tab[][] = new Pieza[3][3];
    tab[0][0] = new Pieza(Ficha.O);
    tab[0][1] = new Pieza(Ficha.VACIA);
    tab[0][2] = new Pieza(Ficha.VACIA);
    tab[1][0] = new Pieza(Ficha.VACIA);
    tab[1][1] = new Pieza(Ficha.O);
    tab[1][2] = new Pieza(Ficha.O);
    tab[2][0] = new Pieza(Ficha.VACIA);
    tab[2][1] = new Pieza(Ficha.VACIA);
    tab[2][2] = new Pieza(Ficha.O);
    tablero.setTablero(tab);
    assertTrue(tablero.verificarDiagonal(Ficha.O));
  }

  @Test
  public void testVerificarFilas() {
    this.tablero.limpiarTablero();
    Pieza tab[][] = new Pieza[3][3];
    tab[0][0] = new Pieza(Ficha.X);
    tab[0][1] = new Pieza(Ficha.X);
    tab[0][2] = new Pieza(Ficha.X);
    tab[1][0] = new Pieza(Ficha.VACIA);
    tab[1][1] = new Pieza(Ficha.VACIA);
    tab[1][2] = new Pieza(Ficha.VACIA);
    tab[2][0] = new Pieza(Ficha.VACIA);
    tab[2][1] = new Pieza(Ficha.VACIA);
    tab[2][2] = new Pieza(Ficha.VACIA);
    tablero.setTablero(tab);
    assertTrue(tablero.verificarFilas(Ficha.X));
  }

  @Test
  public void testVerificarColumna() {
    this.tablero.limpiarTablero();
    Pieza tab[][] = new Pieza[3][3];
    tab[0][0] = new Pieza(Ficha.X);
    tab[0][1] = new Pieza(Ficha.VACIA);
    tab[0][2] = new Pieza(Ficha.VACIA);
    tab[1][0] = new Pieza(Ficha.X);
    tab[1][1] = new Pieza(Ficha.VACIA);
    tab[1][2] = new Pieza(Ficha.VACIA);
    tab[2][0] = new Pieza(Ficha.X);
    tab[2][1] = new Pieza(Ficha.VACIA);
    tab[2][2] = new Pieza(Ficha.VACIA);
    tablero.setTablero(tab);
    assertTrue(tablero.verificarColumnas(Ficha.X));
  }

  @Test
  public void testTableroLleno() {
    this.tablero.limpiarTablero();
    Pieza tab[][] = new Pieza[3][3];
    tab[0][0] = new Pieza(Ficha.X);
    tab[0][1] = new Pieza(Ficha.X);
    tab[0][2] = new Pieza(Ficha.X);
    tab[1][0] = new Pieza(Ficha.X);
    tab[1][1] = new Pieza(Ficha.X);
    tab[1][2] = new Pieza(Ficha.X);
    tab[2][0] = new Pieza(Ficha.X);
    tab[2][1] = new Pieza(Ficha.X);
    tab[2][2] = new Pieza(Ficha.X);
    tablero.setTablero(tab);
    assertTrue(tablero.tableroLleno());
  }

}