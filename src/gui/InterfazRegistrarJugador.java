package gui;

import newGui.message.MessageUser;
import newGui.player.IRegister;
import newGui.player.ISearch;
import persistenceData.Store;
import tresenraya.Jugador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazRegistrarJugador extends JFrame implements IRegister, ISearch {

  private JLabel text;
  private JTextField caja;
  private JButton botonAceptar;
  private JButton botonCancelar;
  private Jugador jugador;
  private Store storeData;

  public InterfazRegistrarJugador(Jugador jugador) {
    super();
    this.jugador = jugador;
    configurarVentana();
    inicializarComponentes();
  }

  private void configurarVentana() {
    this.setTitle("Jugador " +  this.jugador.getFicha());
    this.setSize(400, 200);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.setResizable(true);
    this.setVisible(true);
  }

  private void inicializarComponentes() {
    text = new JLabel();
    caja = new JTextField();
    botonAceptar = new JButton();
    botonCancelar =  new JButton();

    text.setText("Name");
    text.setBounds(55, 50, 170, 25);
    caja.setBounds(110, 50, 220, 25);
    botonAceptar.setText("Aceptar");
    botonAceptar.setBounds(230, 100, 88, 25);
    botonAceptar.setBackground(Color.blue);
    botonAceptar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        buttonAddName(actionEvent);
      }
    });

    botonCancelar.setText("Cancelar");
    botonCancelar.setBounds(100, 100,88,25);
    botonCancelar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
         cerrarVentana(actionEvent);
      }
    });

    this.add(text);
    this.add(caja);
    this.add(botonAceptar);
    this.add(botonCancelar);
  }

  private void buttonAddName(ActionEvent event) {
      this.addData(this.getCaja().getText().toLowerCase());
  }

  private void cerrarVentana(ActionEvent event) {
    this.setVisible(false);
  }

  public JTextField getCaja() {
    return caja;
  }

  @Override
  public void addData(String data) {
    try {
       storeData = new Store();
       String name =  data;
       jugador.setNombre(name);
          if(!searchData(name)) {
            storeData.writeData(data + " " + 0);
          } else {
            new MessageUser("El usuario ya se encuentra registrado, por favor intentelo nuevamente");
          }
      this.setVisible(false);
    } catch (Exception e) {
       new MessageUser("Error del servidor");
    }
  }

  @Override
  public boolean searchData(String data) {
    boolean exits = false;
    if(storeData.searchList(data)) {
      exits =  true;
    }
    return  exits;
  }
}
