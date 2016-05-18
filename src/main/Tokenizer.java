package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import jdk.nashorn.internal.parser.Token;

public class Tokenizer {
	BufferedReader bufferedReader;
	ArrayList<String> tokens;
	String line;
	
	public Tokenizer() {
		
	}
	
	int getCharacterIndex(char character) {
		if (Language.isLetter(character)) {
			return 0;
		}
		if (Language.isNumber(character)) {
			return 1;
		}
		switch(character) {
		case '.': return 2;
		case ',': return 3;
		case ';': return 4;
		case ':': return 5;
		case '+': return 6;
		case '-': return 7;
		case '*': return 8;
		case '/': return 9;
		case '<': return 10;
		case '>': return 11;
		case '=': return 12;
		case '(': return 13;
		case ')': return 14;
		case ' ':
		case '\t': return 15;
		default: return -1;
		}
	}
	
	private boolean isSpace(String token) {
		for (int i = 0; i < token.length(); i++) {
			if (token.charAt(i) != ' ' && token.charAt(i) != '\t') {
				return false;
			}
		}
		return true;
	}
	
	private String handleLetter(String line) {
		String token = "";
		for (int i = 0; i < line.length(); i++) {
			if (!Language.isLetter(line.charAt(i)) && !Language.isNumber(line.charAt(i))) {
				break;
			} else {
				token += line.charAt(i);
			}
		}
		
		return token;
	}
	
	private String handleNumber(String line) {
		String token = "";
		boolean decimalFound = false;
		for (int i = 0; i < line.length(); i++) {
			if (!Language.isNumber(line.charAt(i)) && line.charAt(i) != '.') {
				break;
			} else if (line.charAt(i) == '.') {
				if (!decimalFound) {
					decimalFound = true;
					token += line.charAt(i);
				} else {
					break;
				}
			} else {
				token += line.charAt(i);
			}
		}
		
		return token;
	}
	
	private String handlePeriod(String line) {
		return ".";
	}
	
	private String handleComma(String line) {
		return ",";
	}
	
	private String handleSemicolon(String line) {
		return ";";
	}
	
	private String handleColon(String line) {
		String token = ":";
		for (int i = 1; i < line.length(); i++) {
			if (line.charAt(i) == '=') {
				token += line.charAt(i);
			}
			break;
		}
		return token;
	}
	
	private String handleAdd(String line) {
		return "+";
	}
	
	private String handleSubtract(String line) {
		return "-";
	}
	
	private String handleMultiply(String line) {
		return "*";
	}
	
	private String handleDivide(String line) {
		return "/";
	}
	
	private String handleLessThan(String line) {
		String token = "<";
		boolean tailFound = false;
		for (int i = 1; i < line.length(); i++) {
			if (line.charAt(i) == '>' || line.charAt(i) == '=') {
				token += line.charAt(i);
			}
			break;
		}
		return token;
	}
	
	private String handleGreaterThan(String line) {
		String token = ">";
		for (int i = 1; i < line.length(); i++) {
			if (line.charAt(i) == '=') {
				token += line.charAt(i);
			}
			break;
		}
		return token;
	}
	
	private String handleEqual(String line) {
		return "=";
	}
	
	private String handleOpenParenthesis(String line) {
		return "(";
	}
	
	private String handleCloseParenthesis(String line) {
		return ")";
	}
	
	private String handleSpace(String line) {
		String token = "";
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) != ' ' && line.charAt(i) != '\t') {
				break;
			}
			token += line.charAt(i);
		}
		return token;
	}
	
	private String getToken(String line) {
		String token = "";
		switch (getCharacterIndex(line.charAt(0))) {
		case 0: token = handleLetter(line); break;
		case 1: token = handleNumber(line); break;
		case 2: token = handlePeriod(line); break;
		case 3: token = handleComma(line); break;
		case 4: token = handleSemicolon(line); break;
		case 5: token = handleColon(line); break;
		case 6: token = handleAdd(line); break;
		case 7: token = handleSubtract(line); break;
		case 8: token = handleMultiply(line); break;
		case 9: token = handleDivide(line); break;
		case 10: token = handleLessThan(line); break;
		case 11: token = handleGreaterThan(line); break;
		case 12: token = handleEqual(line); break;
		case 13: token = handleOpenParenthesis(line); break;
		case 14: token = handleCloseParenthesis(line); break;
		case 15: token = handleSpace(line); break;
		default: return null;
		}
		return token;
	}
	
	public Collection<String> tokenizeLine(String line, int lineNumber) {
		ArrayList<String> tokens = new ArrayList<String>();
		int start_index, end_index = 0;
		String token;
		
		if (line.length() == 0) {
			return tokens;
		}
		
		while(true) {
			token = getToken(line);
			if (!isSpace(token)) {
				tokens.add(token);
			}
			line = line.substring(token.length());
			if (line.length() == 0) {
				break;
			}
		}
		return tokens;
	}
	
	public ArrayList<String> tokenize(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
		ArrayList<String> tokens = new ArrayList<String>();
		String line = null;
		int lineNumber = 1;
		
		try {
			while((line = bufferedReader.readLine()) != null) {
			    tokens.addAll(tokenizeLine(line, lineNumber));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return tokens;
	}
}
