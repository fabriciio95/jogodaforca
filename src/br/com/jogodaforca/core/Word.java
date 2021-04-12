package br.com.jogodaforca.core;

import java.util.HashSet;
import java.util.Set;

public class Word {

	private static final char SECRET_CHAR = '_';
	
	private String originalWord;
	private Set<Character> foundChars = new HashSet<Character>();
	private Set<Character> wordChars = new HashSet<Character>();
	
	public Word(String originalWord) {
		this.originalWord = originalWord.toUpperCase();
		char[] wordChars = this.originalWord.toCharArray();
		for (char c : wordChars) {
			this.wordChars.add(c);
		}
	}
	
	public int size() {
		return originalWord.length();
	}
	
	public boolean hasChar(char c) {
		c = Character.toUpperCase(c);
		if(originalWord.indexOf(c) > -1) {
			foundChars.add(c);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		char[] charArray = originalWord.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : charArray) {
			if(foundChars.contains(c)) {
				sb.append(c);
			} else {
				sb.append(SECRET_CHAR);
			}
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public boolean discovered() {
		return wordChars.equals(foundChars);
	}
	
	public String getOriginalWord() {
		return originalWord;
	}
}
