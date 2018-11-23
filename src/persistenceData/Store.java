package persistenceData;


import newGui.message.MessageUser;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class Store {

  private File file;
  private FileReader fileReader;
  private FileWriter fileWriter;
  private PrintWriter printWriter;
  private BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;
  private ArrayList<String> list;

  public Store() throws Exception {
    this.file = new File("dataBase/store.txt");
    this.list = new ArrayList<String>();
    this.readerFile();
  }

  public void readerFile() throws  Exception {
    fileReader = new FileReader(this.file);
    bufferedReader =   new BufferedReader(fileReader);
    try {
      String line;
      while((line = bufferedReader.readLine()) != null) {
        if( line.length() > 0) {
          list.add(line);
        }
      }
    } catch ( FileNotFoundException e) {

    }

    bufferedReader.close();
    fileReader.close();
  }

  public void writeData(String data)  {
    try {
      fileWriter = new FileWriter(file, true);
      bufferedWriter = new BufferedWriter(fileWriter);
      fileWriter.append("\n" + data);
      fileWriter.close();
      bufferedWriter.close();
      // new MessageUser("Se registro correctamente!!!");
    } catch (Exception e) {
      new MessageUser("Error al registrar!!!");
    }

  }
  public void updateData(List<String> listPlayers) {
    List<String> orderList =  order(listPlayers);
    this.clearFile();
    for (int i = 0; i < orderList.size() ; i++) {
      this.writeData(orderList.get(i));
    }
  }

  private List order(List<String> list) {
    List<String> orderList = list;
    Collections.sort(orderList, new Comparator<String>() {
      @Override
      public int compare( String p1, String p2) {
        String[] player1 = p1.split(" ");
        String[] player2 = p2.split(" ");
        return new Integer(player2[1]).compareTo(new Integer(player1[1]));
      }
    });
    return  orderList;
  }

  public  void clearFile() {
    try {
      fileWriter = new FileWriter(file, false);
      printWriter =new PrintWriter(fileWriter, false);
      printWriter.flush();
      printWriter.close();
      fileWriter.close();
    } catch (Exception ex) {

    }
  }

  public boolean searchList(String name) {
    boolean existe  = false;
    for ( int i = 0 ; i < list.size() ; i++) {
      String[] jugador = list.get(i).split(" ");
      if ( jugador[0].equals(name)) {
         int puntuacion = Integer.parseInt(jugador[1]) + 1;
         String player = jugador[0] + " " +  puntuacion;
         this.list.remove(i);
         this.list.add(i, player);
         existe = true;
         break;
      }
    }
    return existe;
  }

  public ArrayList<String> getList() {
    return list;
  }

}
