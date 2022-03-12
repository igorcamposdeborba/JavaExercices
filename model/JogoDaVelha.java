package model;

import java.util.Scanner;



public class JogoDaVelha {
	int dimension;
	char [][] board; // array bidimensional para montar o tabuleiro
	boolean registerBoardCreated = false; // serve na valida��o para mostrar na tela se � a primeira vez que o tabuleiro est� sendo criado.
	Player player; // composi��o porque um jogador faz comp�e do jogo
	
	char x = 'x'; // pe�as para poder atribuir a cada jogador (1 = x e 2 = o)
	char circle = 'o'; 
	
	boolean player1Win = false;
	boolean player2Win = false;
	
	Scanner input = new Scanner(System.in);
	
	public JogoDaVelha(int dimension, Player player) {
		createBoard(dimension);
		this.dimension = dimension;
		this.player = player; 
	}

		// fazer jogada
		public boolean play(int line, int column, char piece) {
			try {
				if (board[line][column] != 'x' && board[line][column] != 'o'){
					if (piece == 'x') {
						board[line][column] = piece;
						return true;
					} else if (piece == 'o') {
						board[line][column] = piece;
						return true;
					}
				} else { // ! BUG: N�O T� FUNCIONANDO
					// enquanto ele digitar errado, pe�a para digitar certo
					int temporaryLine = 0;
					int temporaryColumn = 0;
					while (board[line][column] == 'x' || board[line][column] == 'o') {
						System.err.println("Erro: espa�o j� preenchido anteriormente. Digite novamente a linha"); // mostrar mensagem de erro
						temporaryLine = input.nextInt() -1; // ! talvez corrigir com -1
						System.out.println("Digite novamente a linha");
						temporaryColumn = input.nextInt() -1;
						line = temporaryLine;
						column = temporaryColumn;
					}
					if (piece == 'x') {
						board[temporaryLine][temporaryColumn] = piece;
						return true;
					} else if (piece == 'o') {
						board[temporaryLine][temporaryColumn] = piece;
						return true;
					}
				}
				// exception se digitou um tamanho maior que o espa�o do array
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Erro: voc� digitou uma linha ou coluna maior que o espa�o do tabuleiro. Digite um n�mero at� " + board.length);
				int temporaryLine = input.nextInt() -1;
				System.out.println("Digite a coluna em que voc� gostaria de colocar a pe�a. M�nimo 1, m�ximo " + board.length);
				int temporaryColumn = input.nextInt() -1;
				
				while (temporaryLine > board.length || temporaryColumn > board.length) {
					System.err.println("Erro: voc� digitou uma linha ou coluna maior que o espa�o do tabuleiro. Digite um n�mero at� " + board.length);
					temporaryLine = input.nextInt() -1;
					System.out.println("Digite a coluna em que voc� gostaria de colocar a pe�a. M�nimo 1, m�ximo " + board.length);
					temporaryColumn = input.nextInt() -1;
					
				}
				
				if (piece == 'x') {
					board[temporaryLine][temporaryColumn] = piece;
					return true;
				} else if (piece == 'o') {
					board[temporaryLine][temporaryColumn] = piece;
					return true;
				}
			}
			return false;
	}
		
	// Criar tabuleiro como um array pelo construtor
	private void createBoard(int dimension) {
		if (dimension > 2) {
			this.board = new char[dimension][dimension];
		} else {
			System.out.println("Digite um valor a partir de 3");
		}
	}
		
	public void printBoard() {
		// Mostrar orienta��es na primeira jogada, antes de criar o tabuleiro
		if (registerBoardCreated == false) {
			for (int i=0; i < board.length; i++) {
				char[] height = board[i];
				for (int j=0; j < board[i].length; j++) {
					if (i == 0 && j == 0) System.out.println("Os n�meros s�o as linhas e colunas que voc� deve digitar quando solicitado:");
					System.out.print((i+1) + "," + (j+1) + "|");
				}
				System.out.println(); // quebrar a linha
				this.registerBoardCreated = true; // registrar que o tabuleiro foi criado para n�o cair nesse if e cair no else para n�o mostrar os n�meros no tabuleiro
			}
		} else {
			for (int i=0; i < board.length; i++) {
				char[] height = board[i];
				for (int j=0; j < board[i].length; j++) {
					if (i == 0 && j == 0) System.out.println("Os n�meros s�o as linhas e colunas que voc� deve digitar quando solicitado:");
					System.out.print(board[i][j] + "|"); // imprimir os itens dentro do array (pe�as x ou o)
				}
				System.out.println(); // quebrar a linha
			}
		}
	}
	// verificar tabuleiro (l�gica junta em uma parte dentro do m�todo: contar junto validar)
	// verificar de forma din�mica (tabuleiro sem limite de tamanho)
	public boolean verifyHorizontal() {		
		// System.out.println(Arrays.deepToString(board));  // testar visualmente se array ta preenchido

		
		int countX1 = 0; // contador para comparar se o n�mero de pe�as preenchidas com x � igual ao tamanho do array da linha
		int countO1 = 0;
			
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) { // pergue o n�mero do meio e no if compare com os dos do lado
				
				
				// verificar horizontal
				if (board[i][board.length - 1] == board[i][j] // pega �ltima linha
					&& board[i][j] == 'x') { // pega a linha atual para ver se � a pe�a x
						countX1++;	 // contar o n�mero de x daquela linha
						if (countX1 == board.length) { // validar se x � igual ao tamanho da linha
							player1Win = true;
							player.setPointsPlayer1(player.getPointsPlayer1() + 1);
							System.out.println("O jogador 1 " + player.getPlayer1() + " venceu");
							System.out.println(player.toString());
							return true;
						}
						
						
						
				} else if (board[i][board.length - 1] == board[i][j] // verificar horizontal
				    && board[i][j] == 'o') {
							countO1++;
							if (countO1 == board.length) { // testar se o tamanho 
								player2Win = true;
								player.setPointsPlayer2(player.getPointsPlayer2() + 1);
								System.out.println("O jogador 2 " + player.getPlayer2() + " venceu");
								System.out.println(player.toString());
								return true;
							}
				}
				
			}
			countX1 = 0; // zerar contador ao trocar de linha i no array
			countO1 = 0;
		}
		
		return false;
	}
	
	// validar verticais (l�gica separada em duas partes dentro do m�todo: contar e depois validar)
	public boolean verifyVertical() {		
		// System.out.println(Arrays.deepToString(board));
		
		int countX1 = 0; // contador para comparar se o n�mero de pe�as preenchidas com x � igual ao tamanho do array da linha
		int countO1 = 0;
			
			
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				
				// verificar vertical
				if (board[dimension - 1][j] == board[i][j] // pegar a �ltima linha e comparar com a atual
					&& board[i][j] == 'x') { // pega a linha atual para comparar se � a pe�a x
						countX1++;
						
				} else if (board[dimension - 1][j] == board[i][j]
				    && board[i][j] == 'o') {
						countO1++;
							
				}
			}
		}
		// comparar se o array da linha j est� preenchido com todas as pe�as iguais
		if (countX1 == board.length) { 
			if (player1Win == false)
				player1Win = true;
				player.setPointsPlayer1(player.getPointsPlayer1() + 1);
				System.out.println("O jogador 1 " + player.getPlayer1() + " venceu");
				System.out.println(player.toString());
				return true;
		} else if (countO1 == board.length) {
			if (player2Win == false)
				player2Win = true;
				player.setPointsPlayer2(player.getPointsPlayer2() + 1);
				System.out.println("O jogador 2 " + player.getPlayer2() + " venceu");
				System.out.println(player.toString());
				return true;
		}
		
		return false;
	}
	
	// Validar diagonais (l�gica separada em duas partes dentro do m�todo: contar e depois validar)
	public boolean verifyDiagonal() {
			int countX1 = 0; // Para salvar o x a fim de utilizar na compara��o se o tamanho m�ximo da linha foi preenchida com a mesma pe�a
			int countO1 = 0;
			
			// validar diagonal esquerda para  direita
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (i == j) {	// identificar diagonal, que s�o valores iguais porque a matrix � quadrada
						if (board[i][j] == 'x') {
							countX1++;
						} else if (board[i][j] == 'o') {
							countO1++;
						}
					}
				}
			}
			// contar diagonal direita para esquerda
			int countX2 = 0;
			int countO2 = 0;			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (i == (board.length - j - 1) && board[i][j] == 'x') { // inverte diagonal. Come�a do tamanho do array, e vai diminuindo cada posi��o
						countX2++;											 // e diminui 1 para o que array come�a em zero
					} else if (i == (board.length - j - 1) && board[i][j] == 'x') {
						countO2++;
					}
				}
			}
			// validar diagonais: disparar resultado se o valor salvo da diagonal � igual ao tamanho do array quadrado (se o array fosse retangular, a l�gica mudaria)
			if (countX1 == board.length || countX2 == board.length) { 
				if (player1Win == false)
					player1Win = true;
					player.setPointsPlayer1(player.getPointsPlayer1() + 1);
					System.out.println("O jogador 1 " + player.getPlayer1() + " venceu");
					System.out.println(player.toString());
					return true;
			} else if (countO1 == board.length || countO2 == board.length) {
				if (player2Win == false)
					player2Win = true;
					player.setPointsPlayer2(player.getPointsPlayer2() + 1);
					System.out.println("O jogador 2 " + player.getPlayer2() + " venceu");
					System.out.println(player.toString());
					return true;
			}
			

			return false;
		}
	
}