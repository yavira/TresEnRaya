package newGui.handler;

import tresenraya.JugarTresEnRaya;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

  private JButton listener;
  private JugarTresEnRaya model;
  private newGui.Game game;

  public ButtonListener(JugarTresEnRaya model, newGui.Game game) {
    this.model = model;
    this.game = game;
    this.model.jugarMaquina();
  }

  public void actionPerformed(ActionEvent event) {
    listener = (JButton) event.getSource();
    String command = event.getActionCommand();
    int fila = Integer.parseInt(command.substring(0, 1));
    int columna = Integer.parseInt(command.substring(1, 2));
    this.game.putSymbol(fila, columna);
    this.game.jugarAleatorio();
  }
}
