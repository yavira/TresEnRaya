package newGui.menu;

import newGui.message.MessageUser;
import tresenraya.JugarTresEnRaya;
import newGui.Game;
import newGui.boardPosition.BoardPosition;
import newGui.player.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionGame extends JMenuBar {

  private JMenu optionsGame, play, player, more;
  private JMenuItem playerO, playerX, boardPuntuation, exit, changeView;
  private Game game;
  private JugarTresEnRaya model;
  private BoardPosition boardPosition;
  private Form form;

  public OptionGame(JugarTresEnRaya model,  Game game) {
    this.game = game;
    this.model = model;
    optionsGame = new JMenu("Opciones");
    player = new JMenu("Jugar");
    more = new JMenu("help");
    play = new JMenu("Cargar Estrategia");

    playerO = new JMenuItem("Jugador O");
    playerX = new JMenuItem("Jugador X");
    boardPuntuation = new JMenuItem("Tabla de Posiciones");
    changeView = new JMenuItem("Cambiar Interfaz");
    exit = new JMenuItem("About");

    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        new MessageUser("Hecho por Fabiola Fernandez");
      }
    });

    play.add(playerO);
    play.add(playerX);

    boardPuntuation.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        generateBoardPuntuation();
      }
    });

    playerO.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        loadPlayerO();
      }
    });
    playerX.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        loadPlayerX();
      }
    });


    player.add(play);
    player.add(boardPuntuation);

    optionsGame.add(changeView);

    more.add(exit);

    this.add(player);
    this.add(optionsGame);
    this.add(more);
    this.add(Box.createGlue());
  }

  private void showAbout() {
     //  new MessageUser("Programador Fabiola Fernandez");
  }

  private void generateBoardPuntuation() {
    try {
      this.boardPosition = new BoardPosition();
      this.boardPosition.showWindow(true);
    } catch (Exception e) {

    }
  }

  private void loadPlayerO() {
    form = new Form(this.model.getJudador("O"), this.model, this.game);
  }

  private void loadPlayerX() {
    Form form = new Form(this.model.getJudador("X"), this.model, this.game);
  }

  public void refreshBoardPosition() {
    try {
      this.boardPosition.refreshTable();
    } catch (Exception e) {
      new MessageUser("Mire la tabla de posiciones");
    }
  }

  public JugarTresEnRaya getModel() {
    return this.model;
  }

  private void setTypeGame( int value) {
    this.game.setTypeGame(value);
  }

  public int getTypeGame() {
    return this.form.getGame().getTypeGame();
  }
}
