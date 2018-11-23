package tresenraya;

import strategy.Strategy;

public class Jugador {

  private String nombre;
  private String ficha;
  private Strategy strategy;

  public Jugador(String nombre) {
      this.nombre = nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setFicha(String ficha) {
    this.ficha = ficha;
  }

  public String getFicha() {
    return ficha;
  }

  public Strategy getStrategy() {
    return strategy;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }
}
