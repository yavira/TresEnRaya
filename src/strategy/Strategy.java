package strategy;

import tresenraya.Posicion;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Pattern;

public class Strategy {
  private Class clase;
  private String route;
  private File file;
  private Method method;
  private Object estrategyInstance;
  public Strategy() {
  }

  public Posicion hacerJugada() {
    Posicion position = null;
    try {
      Method method = clase.getMethod("hacerJugada");
      position =  (Posicion) method.invoke(estrategyInstance);
      this.showOtherTablero();

    } catch (Exception e) {

    }
    return position;
  }

  public void showOtherTablero() {
    try {
      Method method = this.clase.getMethod("imprimirTablero");
      String show  = (String) method.invoke(estrategyInstance);
      System.out.println("message  " + show);
    } catch (Exception e) {
    }
  }

  public void registrarPosicion(Posicion posicion) {
    try {
      Method method = this.clase.getMethod("registrarJugadaContraria", Posicion.class);
      method.invoke(estrategyInstance, posicion);
      this.showOtherTablero();
    } catch (Exception e) {

    }
  }

  public void loadReadFileClass() {
    String regex = "/";
    String[] splitUrl = route.split(Pattern.quote(regex));
    String nameClass = splitUrl[splitUrl.length - 1];
    nameClass = nameClass.replaceAll(".class", "");

    try {
       URL url = file.getParentFile().getParentFile().toURI().toURL();
       URL[] urls = new URL[]{url};
       URLClassLoader ucl = new URLClassLoader(urls);
       this.clase = ucl.loadClass("estrategia." + nameClass);
       this.getRebootStrategy();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void getRebootStrategy() throws  Exception {
    this.estrategyInstance = this.clase.getConstructor(new Class[] {}).newInstance(new Object[] {});
  }
  public void setRoute(String route) {
    this.route = route;
  }

  public void setFile(File file) {
    this.file = file;
  }

}
