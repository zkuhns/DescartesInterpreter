package main;
import java.util.ArrayList;
import java.util.Stack;

public class Parser {
	private ArrayList<String> tokens;
	private int program_counter;
	
	private Stack<String> terminals;
	private Stack<String> nonterminals;
	
	public Parser(ArrayList<String> tokens) {
		this.tokens = tokens;
		terminals = new Stack<String>();
		nonterminals = new Stack<String>();
	}
	
	public boolean parse() {
		program_counter = 0;
		terminals.clear();
		nonterminals.clear();
		
		try {
			nonterminals.push("prog");
			while (nonterminals.size() > 0) {
				if (Language.translate_token(tokens.get(program_counter)).equals(nonterminals.peek())) {
					nonterminals.pop();
					terminals.push(tokens.get(program_counter));
					program_counter++;
				} else {
					int rule = Language.get_rule(nonterminals.pop(), tokens.get(program_counter));
					String[] push = Language.get_grammar(rule);
					
					for (int i = push.length-1; i >= 0; i--) {
						nonterminals.push(push[i]);
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Problems at token " + program_counter + " " + tokens.get(program_counter));
			return false;
		} catch (NullPointerException e) {
			System.out.println("Problems at token " + program_counter + " " + tokens.get(program_counter));
			return false;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No end token");
			return false;
		}
		
		return true;
	}
}
