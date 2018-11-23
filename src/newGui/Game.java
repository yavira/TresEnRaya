package newGui;

import tresenraya.Posicion;
import tresenraya.JugarTresEnRaya;
import newGui.message.MessageGame;
import newGui.message.MessageUser;
import newGui.menu.OptionGame;
import newGui.board.Board;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class Game extends JFrame {

	private JLabel viewProcessGame;
	private JButton nextMove;
	private Board board;
	private MessageUser messageUser;
	private OptionGame optionGame;
	private JugarTresEnRaya model;
	private JPanel rightPanel;
  private int typeGame;

	public Game() {
		this.initComponents();
    this.setTitle("Juego Tres en Raya");
		this.typeGame = 1;
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}

	private void initComponents() {
		this.model = new JugarTresEnRaya();
		this.model.jugarConMaquina();
		optionGame = new OptionGame(this.model, this);

		viewProcessGame = new JLabel("Turno del jugador X", JLabel.CENTER);
    nextMove =  new JButton("Next");
    nextMove.setBounds(700,800,50,50);
    nextMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
          doNextMove();
			}
		});

		board = new Board(model,this);
		setLayout(new BorderLayout());
		JToolBar toolbar = new JToolBar();
		toolbar.setRollover(false);

		add(optionGame, BorderLayout.PAGE_START);
		add(board, BorderLayout.CENTER);

		rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20,20));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanel.add(viewProcessGame);
		rightPanel.add(nextMove);

		this.add(rightPanel, BorderLayout.EAST);

		pack();
    this.gameWithPc();

		this.addWindowListener(new java.awt.event.WindowAdapter(){
			public void windowActivated(java.awt.event.WindowEvent evt){
				hideInformation();
			}
		});
	}

	private void hideInformation() {
		try {
			this.typeGame = this.optionGame.getTypeGame();

		} catch (Exception e) {

		}
		if (this.typeGame == 1 ) {
			this.rightPanel.setVisible(false);
		} else  {
			this.rightPanel.setVisible(true);
		}
	}

	public void showTurnGame() {
		String turn = "Turno ";
		if (this.model.turno() == 'X') {
			   turn +=  "X";
		} else {
			   turn +=  "O";
		}
		this.viewProcessGame.setText(turn);
	}

	public void clearWindowGame() {
		this.board.clearBoard();
	}

	public Board getBoard() {
		return board;
	}

	public void gameWithPc() {
		this.model = new JugarTresEnRaya();
		this.model.jugarConMaquina();
		this.clearWindowGame();
		this.jugarAleatorio();
	}

	public void rebootGameWithPc() {
		this.clearWindowGame();
		this.model.reanudarJuego();
		this.jugarAleatorio();
	}

	public void jugarAleatorio() {
		if ( !this.model.estadoTresEnRaya() && this.model.turno() == 'X') {
			Posicion posicion = this.generatePosicionAleatoriaValida();
			this.putSymbol(posicion.fila, posicion.columna);
		}
	}

	private Posicion generatePosicionAleatoriaValida() {
		Posicion posicion;
		int numberAleatorio = this.model.jugarMaquina();
		int x = (numberAleatorio-1)/3;
		int y = (numberAleatorio-1)%3;
		while (this.getBoard().getPiece(x, y).getText() != "") {
      numberAleatorio =  this.model.jugarMaquina();
			x = (numberAleatorio-1)/3;
			y = (numberAleatorio-1)%3;
		}
		posicion = new Posicion(x, y);
		return  posicion;
	}

	public void putSymbol(int x, int y) {
		if(this.getBoard().getPiece(x, y).getText() == "" && !this.model.estadoTresEnRaya()) {
			this.getBoard().setPiece(x, y, Character.toString(model.turno()));
			this.model.jugarJugador(x, y);
			 // listener.setEnabled(true);
			this.showTurnGame();
			playerWin();
		} else {
			if( this.model.estadoTresEnRaya()) {
				 this.messageUser = new MessageUser("No es posible hacer esta operacion, por que el Juego ya termino!!! :(");
			} else {
				 this.messageUser = new MessageUser("Casilla ocupada :(!!");
			}
		}
	}

	private boolean freeSquare(int x, int y) {
		boolean free =  false;
		if ( this.getBoard().getPiece(x, y).getText() == "" ) {
			 free = true;
		}
		return free;
	}

	public void playerWin() {
		if ( this.model.estadoTresEnRaya()) {
			this.showWin(this.model.getGanador());
		}
	}

	public void showWin(String name) {
		MessageGame messageGame;
		if ( name.equals("")) {
			messageGame = new MessageGame("Juego empatado !!! te gustaria jugar de nuevo ? ", this);
		} else {
			messageGame = new MessageGame("El juego ganado por " + name + " !!! te gustaria jugar de nuevo ?", this);
      this.model.addJugadorTablaDePosiciones(name, true);
      this.optionGame.refreshBoardPosition();
		}
	}

	public void doNextMove() {
	 Posicion position = null;
		if (this.model.turno() == 'X') {
		  position = this.model.getJudador("X").getStrategy().hacerJugada();
		} else {
		  position =  this.model.getJudador("O").getStrategy().hacerJugada();
		}
		int x = position.fila;
		int y = position.columna;

		if ( this.freeSquare(x, y) && !this.model.estadoTresEnRaya() ) {
			this.registrarJugadaContraria(position);
			this.putSymbol(x, y);
		} else {
			this.doNextMove();
		}
	}

	private void registrarJugadaContraria(Posicion posicion) {
		if ( this.model.turno() == 'X') {
			this.model.getJudador("O").getStrategy().registrarPosicion(posicion);
		} else {
			this.model.getJudador("X").getStrategy().registrarPosicion(posicion);
		}
	}

	public void  updateDataGame(String type) {
		this.model.reanudarJuego();
		this.model.getJudador(type).setNombre(optionGame.getModel().getJudador(type).getNombre());
		this.model.getJudador(type).setStrategy(optionGame.getModel().getJudador(type).getStrategy());
	}

	public void rebootStrategy() throws Exception{
		this.model.getJudador("O").getStrategy().getRebootStrategy();
		this.model.getJudador("X").getStrategy().getRebootStrategy();
		this.model.reanudarJuego();
		this.showTurnGame();
		this.clearWindowGame();
	}

	public void setTypeGame(int typeGame) {
		this.typeGame = typeGame;
	}

	public int getTypeGame() {
		return typeGame;
	}
}
