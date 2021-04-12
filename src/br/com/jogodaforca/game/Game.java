package br.com.jogodaforca.game;

import java.util.HashSet;
import java.util.Set;

import br.com.jogodaforca.core.Config;
import br.com.jogodaforca.core.Dictionary;
import br.com.jogodaforca.core.InvalidCharacterException;
import br.com.jogodaforca.core.Word;
import br.com.jogodaforca.ui.UI;

public class Game {

	public void start(String[] args) {
		UI.print("Bem vindo ao Jogo da Forca!");

		Dictionary dictionary = Dictionary.getInstance();
		UI.print("Dicionário usado: " + dictionary.getName());
		int jogos = 0;
		int derrotas = 0;
		int vitorias = 0;

		while (true) {
			try {
				Word word = dictionary.nextWord();

				UI.print("A palavra tem " + word.size() + " letras");
				Set<Character> usedChars = new HashSet<Character>();
				int erroCount = 0;

				if (args.length > 0) {
					Config.setMaxErrors(args[0]);
				}
				int maxErrors = Integer.parseInt(Config.get("maxErrors"));
				UI.print("Você pode errar no máximo " + maxErrors + " vez(es)");

				while (true) {
					UI.print(word);
					UI.printNewLine();
					char c;
					try {
						c = UI.readChar("Digite uma letra: ");
						if (usedChars.contains(c)) {
							throw new InvalidCharacterException("Esta letra já foi utilizada");
						}
						usedChars.add(c);

						if (word.hasChar(c)) {
							UI.print("Você acertou uma letra!");
						} else {
							++erroCount;
							if (erroCount < maxErrors) {
								UI.print("Você errou! Você ainda pode errar " + (maxErrors - erroCount) + " vez(es)");
							}
						}

						UI.printNewLine();

						if (word.discovered()) {
							UI.print("PARABÉNS! Você acertou a palavra correta: " + word.getOriginalWord());
							UI.print("Fim do jogo!");
							vitorias += 1;
							jogos += 1;
							break;
						}

						if (erroCount == maxErrors) {
							UI.print("Você perdeu o jogo! A palavra correta era: " + word.getOriginalWord());
							UI.print("Fim do jogo!");
							derrotas += 1;
							jogos += 1;
							break;
						}
					} catch (InvalidCharacterException e) {
						UI.print("Erro: " + e.getMessage());
						UI.printNewLine();
					}

				}
				char resp = UI.readChar("Deseja jogar novamente? [S/N]");
				if (resp == 'N' || resp == 'n') {
					UI.printNewLine();
					UI.print("Você jogou " + jogos + " jogo(s) e acertou " + vitorias + " palavra(s) e errou " + derrotas
							+ " palavra(s).");
					UI.print("Obrigado, volte sempre!");
					break;
				}
			} catch (InvalidCharacterException e) {
				UI.print("Erro: " + e.getMessage());
				UI.printNewLine();
			}
		}
	}
}
