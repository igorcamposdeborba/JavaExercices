package application;

import java.util.Scanner;

import model.JogoDaVelha;
import model.Player;

public class Main {
	public static void main (String [] args) {
		Scanner input = new Scanner(System.in);

		// idenfificar jogadores e tamanho do tabuleiro
		
		System.out.println("Digite o nome do jogador 1:");
		String player1 = input.nextLine();
					
		System.out.println("Digite o nome do jogador 2:");
		String player2 = input.nextLine();
		
		Player player = new Player(player1, player2);
		

		// iniciar tabuleiro e jogadas
		boolean replay = false;	
		do {

			System.out.println("Digite o tamanho do tabuleiro (formato num�rico maior ou igual a 3)");
			int dimension = input.nextInt();

			JogoDaVelha game = new JogoDaVelha(dimension, player); // criar tabuleiro

			// iniciar jogadas
			int turnCount = 0; // contador para ser usado na conta se � a vez do player 1
			boolean turnPlayer1 = true; // l�gica boolean para ser usado na conta se � a vez do player 1

			while (game.verifyHorizontal() == false && game.verifyVertical() == false && game.verifyDiagonal() == false) {

				turnPlayer1 = (turnCount % 2 == 0); // turno do jogador par (player 1)
				if (turnPlayer1 == true) {

					game.printBoard();
					System.out.println("Jogador " + player.getPlayer1()
							+ ", digite a linha em que voc� gostaria de colocar a pe�a. M�nimo 1, m�ximo "
							+ (dimension));
					int linePlayer1 = input.nextInt();
					System.out.println("Jogador " + player.getPlayer1()
							+ ", digite a coluna em que voc� gostaria de colocar a pe�a. M�nimo 1, m�ximo "
							+ (dimension));
					int columnPlayer1 = input.nextInt();
					turnCount += 1;

					// tratar dado: caso o usu�rio digite 0, colocar 1 por causa do array
					if (linePlayer1 == 0)
						linePlayer1 = 1;
					if (columnPlayer1 == 0)
						columnPlayer1 = 1;

					boolean result = game.play(linePlayer1 - 1, columnPlayer1 - 1, 'x'); // tratar dado: diminui 1 porque o array come�a em zero
																							

				} else {
					game.printBoard();

					System.out.println("Jogador " + player.getPlayer2()
							+ ", digite a linha em que voc� gostaria de colocar a pe�a. M�nimo 1, m�ximo "
							+ (dimension));
					int linePlayer2 = input.nextInt();
					System.out.println("Jogador " + player.getPlayer2()
							+ ", digite a coluna em que voc� gostaria de colocar a pe�a. M�nimo 1, m�ximo "
							+ (dimension));
					int columnPlayer2 = input.nextInt();
					turnCount += 1;

					// tratar dado: caso o usu�rio digite 0, colocar 1 por causa do array
					if (linePlayer2 == 0)
						linePlayer2 = 1;
					if (columnPlayer2 == 0)
						columnPlayer2 = 1;

					boolean result = game.play(linePlayer2 - 1, columnPlayer2 - 1, 'o'); // formatar para come�ar de 1 e n�o zero
				}
			}
			game.printBoard();
			
			
			System.out.println("Jogar novamente?");
			String answer = input.nextLine();
			answer = input.nextLine();

			if (answer.equalsIgnoreCase("sim")) {
				replay = true;
			} else if (answer.matches("(?i)(n�o|nao)")) {
				replay = false;
				System.out.println(player.toString()); // Informar os dados do vencedor se n�o quiser mais jogar.
				System.out.println("Jogo encerrado.");
			}
		} while (replay == true);

		input.close();
	}	
}
		
		

