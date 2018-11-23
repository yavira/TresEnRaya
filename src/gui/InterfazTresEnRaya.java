package gui;

import newGui.boardPosition.BoardPosition;
import tresenraya.JugarTresEnRaya;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class InterfazTresEnRaya extends Frame {
  private static JButton button[];
  private MenuBar menuBar1;
  private Menu menu1, menu2, menu3;
  private MenuItem menuNewGame, menuExit, menuAbout, menuChangeName1, menuChangeName2, table, anotherInterfaz;
  private JugarTresEnRaya jugar;
  private InterfazRegistrarJugador registerGame;
  private BoardPosition tablaPosiciones;

  public InterfazTresEnRaya() {
    super("Tic Tac Toe");
    initComponents();
    this.newGameWithPc();
    this.setVisible(true);
  }

  private void newGameWithPc() {
    this.jugar = new JugarTresEnRaya();
    this.jugar.jugarConMaquina();
    this.setNewGame();
    int index = this.playMachine();
    this.buttonAction(String.valueOf(index));
    this.loadBoardPosition();
  }

  private void rebootGame() {
    this.jugar.reanudarJuego();
    this.setNewGame();
    int index = this.playMachine();
    this.buttonAction(String.valueOf(index));
  }

  private int playMachine() {
    int index = this.jugar.jugarMaquina();
     while ( button[index].getText() != "") {
          index =  this.jugar.jugarMaquina();
     }
     return  index;
  }

  public void setNewGame() {
    for (int contador = 1; contador < 10; contador++) {
      button[contador].setText("");
    }
  }

  private void initComponents() {
    setLayout(new GridLayout(3, 3, 1, 1));
    setResizable(false);

    menuBar1 = new MenuBar();
    menu1 = new Menu();
    menu2 = new Menu();
    menu3 = new Menu();
    menuNewGame = new MenuItem();
    menuExit = new MenuItem();
    menuAbout = new MenuItem();
    menuChangeName1 = new MenuItem();
    menuChangeName2 = new MenuItem();
    table = new MenuItem();
    anotherInterfaz =  new MenuItem();

    menu1.setLabel("File");
    menu2.setLabel("Play");
    menu3.setLabel("Options");

    menuNewGame.setLabel("New Game");
    menuNewGame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        newGameActionPerformed(evt);
      }
    });
    menu2.add(menuNewGame);


    menuExit.setLabel("Exit");
    menuExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        exitActionPerformed(evt);
      }
    });
    menu1.add(menuExit);

    menuAbout.setLabel("About...");
    menuAbout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        aboutActionPerformed(evt);
      }
    });
    menu1.add(menuAbout);

    menuChangeName1.setLabel("Register Player O");
    menuChangeName1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setJugadorO(evt);
      }
    });

    table.setLabel("Table of Positions");
    table.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        showTable(evt);
      }
    });

    anotherInterfaz.setLabel("Change Design");
    anotherInterfaz.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        showAnotherUI();
      }
    });

    menu2.add(menuChangeName1);
    menu2.add(table);

    menu3.add(anotherInterfaz);

    menuBar1.add(menu2);
    menuBar1.add(menu3);
    menuBar1.add(menu1);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        exitForm(evt);
      }
    });

    setMenuBar(menuBar1);


    button = new JButton[10];
    for (int contador = 1; contador < 10; contador++) {
      button[contador] = new JButton();
      button[contador].setFocusPainted(false);
      button[contador].setActionCommand(Integer.toString(contador));
      button[contador].setFont(new Font("Dialog", 0, 48));
      button[contador].setPreferredSize(new Dimension(100, 100));
      button[contador].setToolTipText("Click to make your move");
      button[contador].addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          buttonAction(evt.getActionCommand());
        }
      });
      add(button[contador]);
    }
    pack();
  }

  private void showTable( ActionEvent evt) {
    this.tablaPosiciones.showWindow(true);
  }

  private void aboutActionPerformed(ActionEvent evt) {
    JOptionPane.showMessageDialog(InterfazTresEnRaya.this, "This simple game is created by \nD3M0L1SH3R.",
      "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
  }

  private void exitActionPerformed(ActionEvent evt) {
    System.exit(0);
  }

  private void newGameActionPerformed(ActionEvent evt) {
    this.newGameWithPc();
  }

  private void exitForm(WindowEvent evt) {
    System.exit(0);
  }

  private void setJugadorO(ActionEvent evt) {
    this.registerGame = new InterfazRegistrarJugador(this.jugar.getJudador("O"));
    try {
       this.updateBoardPositions();
    } catch (Exception e) {

    }

  }

  private void storePlayer(String name, boolean ganado) {
    this.jugar.addJugadorTablaDePosiciones(name, ganado);
  }

  private void buttonAction(String btn) {
    int index = Integer.parseInt(btn);
    this.putSymbol(index);
    if (this.jugar.exitJugadorMaquina() && this.jugar.turno() == 'X' ) {
      int posicion = this.playMachine();
      this.putSymbol(posicion);
    }
  }

  private void putSymbol(int index) {
    if (button[index].getText() == "") {
      button[index].setText(Character.toString(this.jugar.turno()));
      this.jugar.jugarJugador((index - 1) / 3, (index - 1) % 3);
       checkGameStatus();
    } else {
      JOptionPane.showMessageDialog(InterfazTresEnRaya.this,
        "Esta casilla ya esta ocupada, \nPor favor intente en otra casilla",
        "Oops...", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void checkGameStatus() {
    if (this.jugar.estadoTresEnRaya()) {
      this.gameStop(this.jugar.getGanador());
    }
  }

  private void gameStop(String win) {
    if (win == "") {
      JOptionPane.showMessageDialog(InterfazTresEnRaya.this, "Juego empatado !!! \n vamos a jugar otra vez!",
        "Resultado del juego...", JOptionPane.INFORMATION_MESSAGE);
      this.newGameWithPc();
    } else {
      String output = "El jugador \"" + win + "\" gano!!! \n te gustaria jugar otra vez?";
      int choice = JOptionPane.showConfirmDialog(InterfazTresEnRaya.this, output,
        "Felicidades!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
           if (!win.equals("Computadora")) {
             this.storePlayer(win, true);
             this.updateBoardPositions();
           }
      if (choice == 0) {
        this.rebootGame();
      }
    }
  }

  private void updateBoardPositions() {
    try {
      this.tablaPosiciones.refreshTable();
    } catch (Exception e) {
    }
  }
  private void loadBoardPosition() {
    try {
      this.tablaPosiciones = new BoardPosition();
    } catch (Exception e) {

    }
  }
  private void showAnotherUI(){
    this.setVisible(false);
    new newGui.Game();
  }
}

/// cuando empata  el juego y reanuda el juego no actualiza la tabla


