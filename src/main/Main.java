package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		BufferedReader bufferedReader;
		FileReader fileReader;
		
		for (int i = 0; i < args.length; i++) {
			String filename = args[i];
			
			try {
				fileReader = new FileReader(filename);
				bufferedReader = new BufferedReader(fileReader);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			Tokenizer tokenizer = new Tokenizer();
			ArrayList<String> tokens = tokenizer.tokenize(bufferedReader);
			
			System.out.println("Tokenized!");
			
			Parser parser = new Parser(tokens);
			if (!parser.parse()) {
				return;
			}
			
			System.out.println("Parsed!");
			
			Interpreter interpreter = new Interpreter();
			interpreter.interpret(tokens);

			System.out.println("Interpreted!");
		}
	}
}
