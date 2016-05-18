package main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Interpreter {
	private ArrayList<String> tokens;
	
	private int program_counter;
	private HashMap<String, Double> symbol_table;
	private Stack<String> branch_stack;
	private HashMap<String, Integer> branch_indexes;
	private String current_branch;
	
	Interpreter() {
		program_counter = 0;
		symbol_table = new HashMap<String, Double>();
		branch_stack = new Stack<String>();
		branch_indexes = new HashMap<String, Integer>();
		current_branch = null;
	}
	
	private double operate(double operand_one, double operand_two, String operation) {		
		switch (operation) {
		case "*":
			return operand_one * operand_two;
		case "/":
			return operand_one / operand_two;
		case "-":
			return operand_one - operand_two;
		case "+":
			return operand_one + operand_two;
		case "<":
			if (operand_one < operand_two) {
				return 1;
			} else {
				return 0;
			}
		case "<=":
			if (operand_one <= operand_two) {
				return 1;
			} else {
				return 0;
			}
		case "=":
			if (operand_one == operand_two) {
				return 1;
			} else {
				return 0;
			}
		case ">=":
			if (operand_one >= operand_two) {
				return 1;
			} else {
				return 0;
			}
		case ">":
			if (operand_one > operand_two) {
				return 1;
			} else {
				return 0;
			}
		case "<>":
			if (operand_one != operand_two) {
				return 1;
			} else {
				return 0;
			}
		case "AND":
			if (operand_one != 0 && operand_two != 0) {
				return 1;
			} else {
				return 0;
			}
		case "OR":
			if (operand_one != 0 || operand_two != 0) {
				return 1;
			} else {
				return 0;
			}
		}
		
		return 0;
	}
	
	private double calculateStatement(Stack<String> statement) {
		Stack<Double> variables = new Stack<Double>();
		Stack<String> operators = new Stack<String>();
		String token;
		boolean unary = true;
		
		while (statement.size() > 0) {
			token = statement.remove(0);
			if (Language.isID(token)) {
				variables.push(symbol_table.get(token));
				unary = false;
			} else if (Language.isConst(token)) {
				variables.push(Double.parseDouble(token));
				unary = false;
			} else if (token.equals("(")) {
				variables.push(calculateStatement(statement));
				unary = false;
			} else if (token.equals(")")) {
				while (operators.size() > 0) {
					double operand_two = variables.pop();
					double operand_one = variables.pop();
					variables.push(operate(operand_one, operand_two, operators.pop()));
				}
				return variables.pop();
			} else if (token.equals("-") && unary) {
				// handle subtraction or unary operations
				int negatives = 1;
				while (statement.size() > 0) {
					token = statement.remove(0);
					if (token.equals("-")) {
						negatives++;
					} else {
						double value;
						if (Language.isID(token)) {
							// handle ID
							value = symbol_table.get(token);
							if (negatives % 2 == 1) {
								value = -value;
							}
							variables.push(value);
							unary = false;
						} else if (token.equals("(")) {
							// handle parenthesis
							value = calculateStatement(statement);
							if (negatives % 2 == 1) {
								value = -value;
							}
							variables.push(value);
							unary = false;
						} else {
							// handle constants
							value = Double.parseDouble(token);
							if (negatives % 2 == 1) {
								value = -value;
							}
							variables.push(value);
							unary = false;
						}
						break;
					}
				}
			} else {
				if (operators.size() == 0) {
					operators.push(token);
					unary = true;
					continue;
				}
				
				while (operators.size() > 0 && Language.get_precedence(operators.peek(), token)) {
					double operand_two = variables.pop();
					double operand_one = variables.pop();
					variables.push(operate(operand_one, operand_two, operators.pop()));
				}
				operators.push(token);
				unary = true;
			}
		}
		
		while (operators.size() > 0) {
			double operand_two = variables.pop();
			double operand_one = variables.pop();
			variables.push(operate(operand_one, operand_two, operators.pop()));
		}
		
		return variables.pop();
	}
	
	private void handleIf(Stack<String> statement) {
		double condition = calculateStatement(statement);
		int ifCount = 0;
		
		if (condition == 0) { // false
			for (; program_counter < tokens.size(); program_counter++) {
				if (tokens.get(program_counter).equals("IF")) {
					ifCount++;
				} else if (tokens.get(program_counter).equals("FI")) {
					if (ifCount > 0) {
						ifCount--;
					} else {
						break;
					}
				} else if (tokens.get(program_counter).equals("ELSE")) {
					if (ifCount > 0) {
						ifCount--;
					} else {
						break;
					}
				}
			}
		} else { // true
			for (; program_counter < tokens.size(); program_counter++) {
				if (tokens.get(program_counter).equals("THEN")) {
					break;
				}
			}
		}
	}
	
	private void handleLoop(Stack<String> statement) {
		if (branch_stack.contains(current_branch)) {
			// Throw exception
		}
		
		current_branch = statement.get(0);
		branch_stack.push(current_branch);
		
		if (branch_indexes.containsKey(current_branch+"LOOP")) {
			return;
		}
		
		branch_indexes.put(current_branch+"LOOP", program_counter+1);
		
		int loopCount = 0;
		for (int i = program_counter; i < tokens.size(); i++) {
			if (tokens.get(i).equals("LOOP")) {
				loopCount++;
			} else if (tokens.get(i).equals("REPEAT")) {
				if (loopCount > 0) {
					loopCount--;
				} else {
					branch_indexes.put(current_branch+"BREAK", i+=2);
					break;
				}
			}
		}
	}
	
	private void handleRepeat(Stack<String> statement) {
		program_counter = branch_indexes.get(current_branch+"LOOP")-1;
	}

	private void handleBreak(Stack<String> statement) {
		if (statement.size() > 0) {
			String branch = statement.get(0);
			if (!branch_indexes.containsKey(branch+"LOOP")) {
				// Throw exception
				// Branch never existed
			}
			
			if (!branch_stack.contains(branch)) {
				// Throw exception
				// Branch is not on the stack
			}
			
			program_counter = branch_indexes.get(branch+"BREAK")-1;
			
			while (!branch_stack.peek().equals(branch)) {
				branch_stack.pop();
			}
			branch_stack.pop();
			
			if (branch_stack.size() == 0) {
				current_branch = null;
			} else {
				current_branch = branch_stack.pop();
			}
		} else {
			program_counter = branch_indexes.get(current_branch+"BREAK")-1;
			branch_stack.pop();
			if (branch_stack.size() == 0) {
				current_branch = null;
			} else {
				current_branch = branch_stack.peek();
			}
		}
	}
	
	private void handlePrint(Stack<String> statement) {
		for (int i = 0; i < statement.size(); i+=2) {
			System.out.print(statement.get(i) + "=" + symbol_table.get(statement.get(i)));
		}
		System.out.println();
	}
	
	private void handleRead(Stack<String> statement) {
		String line;
		double value;
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < statement.size(); i+=2) {
			while (true) {
				System.out.print(statement.get(i) + "=");
				line = in.nextLine();
				try {
					value = Double.parseDouble(line);
				} catch (NumberFormatException e) {
					continue;
				}
				
				symbol_table.put(statement.get(i), value);
				break;
			}
		}
	}
	
	private void handleAssign(Stack<String> statement) {
		String variable = statement.remove(0);
		statement.remove(0);
		symbol_table.put(variable, calculateStatement(statement));
	}
	
	private void handleStatement(Stack<String> statement) {
		if (statement.size() == 0) {
			return;
		}
		
		switch (statement.get(0)) {
		case "IF": statement.remove(0); handleIf(statement); break;
		case "LOOP": statement.remove(0); handleLoop(statement); break;
		case "REPEAT": statement.remove(0); handleRepeat(statement); break;
		case "BREAK": statement.remove(0); handleBreak(statement); break;
		case "PRINT": statement.remove(0); handlePrint(statement); break;
		case "READ": statement.remove(0); handleRead(statement); break;
		default: handleAssign(statement);
		}
	}
	
	public boolean interpret(ArrayList<String> tokens) {
		this.tokens = tokens;
		program_counter = 0;
		symbol_table.clear();
		branch_stack.clear();
		branch_indexes.clear();
		
		Stack<String> statement = new Stack<String>();
		
		for (; program_counter < tokens.size(); program_counter++) {
			switch (tokens.get(program_counter)) {
			case ";":
			case ":":
			case ".":
			case "FI":
			case "ELSE":
			case "THEN": {
				handleStatement(statement);
				statement.clear();
				break;
			}
			default: {
				statement.push(tokens.get(program_counter));
			}
			}
		}
		
		return true;
	}
}
