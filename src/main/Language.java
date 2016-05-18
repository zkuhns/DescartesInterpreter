package main;
public class Language {
	final int TERMINAL_COUNT = 30;
	final int NONTERMINAL_COUNT = 25;
	
	public final static int SYNTAX[][] = {
	/*        0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 */
	/*0*/	{ 0, 0, 0,-1,-1,-1, 0, 0,-1,-1, 0,-1, 0, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*1*/	{ 1, 1, 1,-1, 1, 1, 1, 1,-1, 1, 1,-1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*2*/	{10,10, 4,-1,10,10, 5, 7,-1,10, 6,-1, 9, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*3*/	{ 3, 2,-1,-1, 3, 3,-1,-1,-1, 3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*4*/	{-1,-1,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*5*/	{-1,-1,-1,-1,-1,-1,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*6*/	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*7*/	{-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*8*/	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*9*/	{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,19,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*10*/	{-1,-1,-1,-1,-1,-1,-1,23,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,23,-1,-1,23,-1,23,},
	/*11*/	{-1,-1,-1,-1,12,13,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*12*/	{17,17,-1,-1,17,17,-1,16,-1,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*13*/	{22,22,-1,-1,22,22,-1,-1,-1,22,-1,-1,-1,-1,21,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,},
	/*14*/	{-1,-1,-1,-1,-1,-1,-1,26,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,26,-1,-1,26,-1,26,},
	/*15*/	{25,25,-1,25,25,25,-1,-1,-1,25,-1,-1,-1,-1,-1,24,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,25,-1,},
	/*16*/	{-1,-1,-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,29,-1,-1,29,-1,29,},
	/*17*/	{28,28,-1,28,28,28,-1,-1,-1,28,-1,-1,-1,-1,-1,28,27,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,28,-1,},
	/*18*/	{-1,-1,-1,-1,-1,-1,-1,37,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,37,-1,-1,37,-1,37,},
	/*19*/	{36,36,-1,36,36,36,-1,-1,-1,36,-1,-1,-1,-1,-1,36,36,30,31,32,33,34,35,-1,-1,-1,-1,-1,36,-1,},
	/*20*/	{-1,-1,-1,-1,-1,-1,-1,41,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,41,-1,-1,41,-1,41,},
	/*21*/	{40,40,-1,40,40,40,-1,-1,-1,40,-1,-1,-1,-1,-1,40,40,40,40,40,40,40,40,38,39,-1,-1,-1,40,-1,},
	/*22*/	{-1,-1,-1,-1,-1,-1,-1,46,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,45,-1,-1,47,-1,46,},
	/*23*/	{44,44,-1,44,44,44,-1,-1,-1,44,-1,-1,-1,-1,-1,44,44,44,44,44,44,44,44,44,44,42,43,-1,44,-1,},
	/*24*/	{-1,-1,-1,-1,-1,-1,-1,48,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,49,},
	};
	
	public static int get_rule(String token1, String token2) {
		return SYNTAX[get_nonterminal_index(token1)][get_terminal_index(token2)];
	}
	
	public final static Boolean[][] OPERATOR_PRECEDENCE = {
	/*           )     *     /     +     -     <    <=     =    >=     >    <>   AND    OR    := */
	/* ) */	{false, true, true, true, true, true, true, true, true, true, true, true, true, true,},
	/* * */	{ true,false,false, true, true, true, true, true, true, true, true, true, true, true,},
	/* / */	{ true,false,false, true, true, true, true, true, true, true, true, true, true, true,},
	/* + */	{ true,false,false,false,false, true, true, true, true, true, true, true, true, true,},
	/* - */	{ true,false,false,false,false, true, true, true, true, true, true, true, true, true,},
	/* < */	{ true,false,false,false,false, null, null, null, null, null, null, true, true, true,},
	/* <= */{ true,false,false,false,false, null, null, null, null, null, null, true, true, true,},
	/* = */	{ true,false,false,false,false, null, null, null, null, null, null, true, true, true,},
	/* >= */{ true,false,false,false,false, null, null, null, null, null, null, true, true, true,},
	/* > */	{ true,false,false,false,false, null, null, null, null, null, null, true, true, true,},
	/* <> */{ true,false,false,false,false, null, null, null, null, null, null, true, true, true,},
	/*AND*/	{ true,false,false,false,false,false,false,false,false,false,false, true, true, true,},
	/*OR*/	{ true,false,false,false,false,false,false,false,false,false,false,false, true, true,},
	/*:= (*/{false,false,false,false,false,false,false,false,false,false,false,false,false, null,},
	};
	
	public static boolean get_precedence(String token1, String token2) {
		return OPERATOR_PRECEDENCE[get_operator_index(token1)][get_operator_index(token2)];
	}
	
	public static int get_operator_index(String operator) {
		switch(operator) {
		case ")": return 0;
		case "*": return 1;
		case "/": return 2;
		case "+": return 3;
		case "-": return 4;
		case "<": return 5;
		case "<=": return 6;
		case "=": return 7;
		case ">=": return 8;
		case ">": return 9;
		case "<>": return 10;
		case "AND": return 11;
		case "OR": return 12;
		case "(":
		case ":=": return 13;
		default: return -1;
		}
	}
	
	public static int get_nonterminal_index(String token) {
		switch(token) {
		case ("prog"): return 0;
		case ("stmt-list"): return 1;
		case ("stmt"): return 2;
		case ("stmt-tail"): return 3;
		case ("if-stmt"): return 4;
		case ("loop-stmt"): return 5;
		case ("break-stmt"): return 6;
		case ("assign-stmt"): return 7;
		case ("read-stmt"): return 8;
		case ("print-stmt"): return 9;
		case ("expr"): return 10;
		case ("else-part"): return 11;
		case ("id-option"): return 12;
		case ("id-list-tail"): return 13;
		case ("bool-term"): return 14;
		case ("bool-term-tail"): return 15;
		case ("bool-factor"): return 16;
		case ("bool-factor-tail"): return 17;
		case ("arith-expr"): return 18;
		case ("relation-option"): return 19;
		case ("term"): return 20;
		case ("term-tail"): return 21;
		case ("factor"): return 22;
		case ("factor-tail"): return 23;
		case ("atom"): return 24;
		default: return -1;
		}
	}
	
	public static int get_terminal_index(String token) {
		if (isID(token)) {
			return 7;
		}
		if (isConst(token)) {
			return 29;
		}
		
		switch(token) {
		case ("."): return 0;
		case (";"): return 1;
		case ("IF"): return 2;
		case ("THEN"): return 3;
		case ("ELSE"): return 4;
		case ("FI"): return 5;
		case ("LOOP"): return 6;
		case ("ID"): return 7;
		case (":"): return 8;
		case ("REPEAT"): return 9;
		case ("BREAK"): return 10;
		case (":="): return 11;
		case ("PRINT"): return 12;
		case ("READ"): return 13;
		case (","): return 14;
		case ("OR"): return 15;
		case ("AND"): return 16;
		case ("<"): return 17;
		case ("<="): return 18;
		case ("="): return 19;
		case (">="): return 20;
		case (">"): return 21;
		case ("<>"): return 22;
		case ("+"): return 23;
		case ("-"): return 24;
		case ("*"): return 25;
		case ("/"): return 26;
		case ("("): return 27;
		case (")"): return 28;
		case ("CONST"): return 29;
		}
		
		return -1;
	}
	
	public static boolean isID(String token) {
		switch (token) {
		case ("IF"): return false;
		case ("THEN"): return false;
		case ("ELSE"): return false;
		case ("FI"): return false;
		case ("LOOP"): return false;
		case ("REPEAT"): return false;
		case ("BREAK"): return false;
		case ("PRINT"): return false;
		case ("READ"): return false;
		case ("OR"): return false;
		case ("AND"): return false;
		case ("ID"): return false;
		case ("CONST"): return false;
		}
		
		if (isNumber(token.charAt(0))) {
			return false;
		}
		
		for (int i = 0; i < token.length(); i++) {
			if (token.charAt(i) == '.') {
				return false;
			} else if (token.charAt(i) == '-') {
				return false;
			}
		}
		
		if (isLetter(token.charAt(0))) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isConst(String token) {
		int decimal_count = 0;
		
		if (token.length() == 1) {
			if (token.charAt(0) == '.') {
				return false;
			}
			
			if (token.charAt(0) == '-') {
				return false;
			}
		}
		
		for (int i = 0; i < token.length(); i++) {
			if (isNumber(token.charAt(i))) {
				continue;
			} else if (token.charAt(i) == '.') {
				if (decimal_count > 0) {
					return false;
				} else {
					decimal_count++;
				}
			} else if (token.charAt(i) == '-') {
				continue;
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	public static String[] get_grammar(int code) {
		switch(code) {
		case (0): return new String[] {"stmt-list", "."};
		case (1): return new String[] {"stmt", "stmt-tail"};
		case (2): return new String[] {";", "stmt", "stmt-tail"};
		case (3): return new String[] {};
		case (4): return new String[] {"if-stmt"};
		case (5): return new String[] {"loop-stmt"};
		case (6): return new String[] {"break-stmt"};
		case (7): return new String[] {"assign-stmt"};
		case (8): return new String[] {"read-stmt"};
		case (9): return new String[] {"print-stmt"};
		case (10): return new String[] {};
		case (11): return new String[] {"IF", "expr", "THEN", "stmt-list", "else-part"};
		case (12): return new String[] {"ELSE", "stmt-list", "FI"};
		case (13): return new String[] {"FI"};
		case (14): return new String[] {"LOOP", "ID", ":", "stmt-list", "REPEAT"};
		case (15): return new String[] {"BREAK", "id-option"};
		case (16): return new String[] {"ID"};
		case (17): return new String[] {};
		case (18): return new String[] {"ID", ":=", "expr"};
		case (19): return new String[] {"PRINT", "ID", "id-list-tail"};
		case (20): return new String[] {"READ", "ID", "id-list-tail"};
		case (21): return new String[] {",", "ID", "id-list-tail"};
		case (22): return new String[] {};
		case (23): return new String[] {"bool-term", "bool-term-tail"};
		case (24): return new String[] {"OR", "bool-term", "bool-term-tail"};
		case (25): return new String[] {};
		case (26): return new String[] {"bool-factor", "bool-factor-tail"};
		case (27): return new String[] {"AND", "bool-factor", "bool-factor-tail"};
		case (28): return new String[] {};
		case (29): return new String[] {"arith-expr", "relation-option"};
		case (30): return new String[] {"<", "arith-expr"};
		case (31): return new String[] {"<=", "arith-expr"};
		case (32): return new String[] {"=", "arith-expr"};
		case (33): return new String[] {">=", "arith-expr"};
		case (34): return new String[] {">", "arith-expr"};
		case (35): return new String[] {"<>", "arith-expr"};
		case (36): return new String[] {};
		case (37): return new String[] {"term", "term-tail"};
		case (38): return new String[] {"+", "term", "term-tail"};
		case (39): return new String[] {"-", "term", "term-tail"};
		case (40): return new String[] {};
		case (41): return new String[] {"factor", "factor-tail"};
		case (42): return new String[] {"*", "factor", "factor-tail"};
		case (43): return new String[] {"/", "factor", "factor-tail"};
		case (44): return new String[] {};
		case (45): return new String[] {"-", "factor"};
		case (46): return new String[] {"atom"};
		case (47): return new String[] {"(", "expr", ")"};
		case (48): return new String[] {"ID"};
		case (49): return new String[] {"CONST"};
		default: return null;
		}
	}
	
	public static boolean isLetter(char character) {
		switch (character) {
		case 'a':	case 'A':
		case 'b':	case 'B':
		case 'c':	case 'C':
		case 'd':	case 'D':
		case 'e':	case 'E':
		case 'f':	case 'F':
		case 'g':	case 'G':
		case 'h':	case 'H':
		case 'i':	case 'I':
		case 'j':	case 'J':
		case 'k':	case 'K':
		case 'l':	case 'L':
		case 'm':	case 'M':
		case 'n':	case 'N':
		case 'o':	case 'O':
		case 'p':	case 'P':
		case 'q':	case 'Q':
		case 'r':	case 'R':
		case 's':	case 'S':
		case 't':	case 'T':
		case 'u':	case 'U':
		case 'v':	case 'V':
		case 'w':	case 'W':
		case 'x':	case 'X':
		case 'y':	case 'Y':
		case 'z':	case 'Z': return true;
		default: return false;
		}
	}
	
	public static boolean isNumber(char character) {
		switch (character) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9': return true;
		default: return false;
		}
	}
	
	public static String translate_token(String token) {
		if (isID(token)) {
			return "ID";
		}
		
		if (isConst(token)) {
			return "CONST";
		}
		
		return token;
	}
	
}
