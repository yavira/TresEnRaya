package newGui.player;

import newGui.message.MessageUser;
import tresenraya.JugarTresEnRaya;
import strategy.Strategy;
import newGui.Game;
import persistenceData.Store;
import tresenraya.Jugador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Form extends JFrame implements IRegister, ISearch {
  private JPanel jPanel;
  private JLabel texto;
  private JTextField fieldName;
  private JTextField nameStrategy;
  private JButton acceptButton;
  private JButton exitButton;
  private JButton selectFileButton;
  private Jugador jugador;
  private Store store;
  private JFileChooser jFileChooser;
  private Strategy strategy;
  private JugarTresEnRaya model;
  private Game game;

  public Form() { }

  public Form(Jugador jugador, JugarTresEnRaya model, Game game) {
    super("Jugador " +  jugador.getFicha());
    this.setSize(300,300);
    this.jugador = jugador;
    this.model =  model;
    this.game =  game;
    this.configureForm();
  }

  public void setJugador(Jugador jugador) {
    this.jugador = jugador;
  }

  private void configureForm() {
    this.initForm();
  }

  private void initForm() {
    this.setSize(250,200);
    this.setResizable(false);
    this.strategy = new Strategy();
    jPanel = new JPanel();
    this.jPanel.setBounds(100, 100, 450, 300);
    texto = new JLabel("Nombre", JLabel.LEFT);
    fieldName = new JTextField(15);
    nameStrategy =  new JTextField(20);
    nameStrategy.setColumns(9);


    exitButton =  new JButton("Cancelar");
    exitButton.setBounds(700,900,50,50);
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        dispose();
      }
    });

    acceptButton =  new JButton("Aceptar");
    acceptButton.setBounds(700,900,50,50);
    acceptButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
           saveChanges();
           game.setTypeGame(2);
      }
    });


    selectFileButton =  new JButton("Selecionar");
    selectFileButton.setBounds(300,400,50,50);
    selectFileButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
         searchFile();
      }
    });

    jPanel.add(texto);
    jPanel.add(fieldName);
    jPanel.add(nameStrategy);
    jPanel.add(selectFileButton);
    jPanel.add(exitButton);
    jPanel.add(acceptButton);

    this.setContentPane(jPanel);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }

  private void searchFile() {
    jFileChooser = new JFileChooser();
    int selection =  jFileChooser.showOpenDialog(null);
    if( selection == jFileChooser.APPROVE_OPTION ) {
       File fichero = jFileChooser.getSelectedFile();
       this.strategy.setFile(fichero);
       this.strategy.setRoute(fichero.getAbsolutePath());
       this.nameStrategy.setText(fichero.getName());
    }
  }

  private void saveChanges() {
    String name = this.fieldName.getText().toLowerCase();
    this.jugador.setNombre(name);
    addBoardPosition(name, false);

    if (this.jugador.getFicha() == "O") {
      this.loadChangeOfPlayer("O", name);
      this.game.updateDataGame("O");
    } else {
      this.loadChangeOfPlayer("X", name);
      this.game.updateDataGame("X");
    }
    this.game.clearWindowGame();
    this.dispose();
  }

  private void loadChangeOfPlayer(String type, String newName) {
    this.strategy.loadReadFileClass();
    this.jugador.setStrategy(this.strategy);
    this.model.getJudador(type).setNombre(newName);
    this.model.getJudador(type).setStrategy(this.strategy);
  }

  public void addBoardPosition(String namePlayer, boolean ganado) {
        if ( ganado ) {
           this.addData( namePlayer + " " + 1);
        } else {
           this.addData( namePlayer + " " + 0);
        }
  }

  @Override
  public void addData(String data) {
    try {
      store = new Store();
      if (!searchData(data)) {
          store.writeData(data);
      } else {
        try {
          store.updateData(store.getList());
        } catch (Exception e) {
          new MessageUser("No se pudo actualizar");
        }
      }
    } catch (Exception e) {
      new MessageUser("Error del servidor");
    }
  }

  @Override
  public boolean searchData(String data) {
    boolean exits = false;
    if(store.searchList(data)) {
      exits =  true;
    }
    return  exits;
  }

  public Game getGame() {
    return game;
  }

}
