package br.com.jogodaforca.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.jogodaforca.game.GameException;
import br.com.jogodaforca.utils.RandomUtils;

public class FileDictionary extends Dictionary {

	private static final String FILE_NAME = "dicionario.txt";
	private List<String> words = new ArrayList<String>();
	
	public FileDictionary() {
		load();
	}
	
	
	private void load() {
		try(Scanner scanner = new Scanner(new FileInputStream(FILE_NAME))){
			while(scanner.hasNextLine()) {
				String word = scanner.nextLine().trim();
				words.add(word);
			}
			
			if(words.isEmpty()) {
				throw new GameException("A lista de palavras não pode ser vazia");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Word nextWord() {
		int pos = RandomUtils.newRandomNumber(0, words.size());
		return new Word(words.get(pos));
	}


	@Override
	public String getName() {
		return "Arquivo " + FILE_NAME;
	}
}
