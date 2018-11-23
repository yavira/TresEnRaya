package tresenraya;

public class Pieza {

  private Posicion posicion;
  private Ficha ficha;

  public Pieza (Ficha ficha) {
        this.ficha = ficha;
  }

  public Posicion getPosicion() {
        return  this.posicion;
  }

  public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
  }

  public Ficha getFicha() {
    return ficha;
  }

  public void setFicha(Ficha ficha) {
        this.ficha = ficha;
  }
}
