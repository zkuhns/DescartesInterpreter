package tests;

import main.Language;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LanguageTests {
	@Test
	public void testKeywords() {
		assertEquals(Language.get_terminal_index("IF"), 2);
		assertEquals(Language.get_terminal_index("THEN"), 3);
		assertEquals(Language.get_terminal_index("ELSE"), 4);
		assertEquals(Language.get_terminal_index("FI"), 5);
		assertEquals(Language.get_terminal_index("LOOP"), 6);
		assertEquals(Language.get_terminal_index("REPEAT"), 9);
		assertEquals(Language.get_terminal_index("BREAK"), 10);
		assertEquals(Language.get_terminal_index("PRINT"), 12);
		assertEquals(Language.get_terminal_index("READ"), 13);
		assertEquals(Language.get_terminal_index("OR"), 15);
		assertEquals(Language.get_terminal_index("AND"), 16);
	}
	
	@Test
	public void testID() {
		assertEquals(Language.get_terminal_index("SUM"), 7);
		assertEquals(Language.get_terminal_index("SUM2"), 7);
		assertNotEquals(Language.get_terminal_index("PRINT"), 7);
	}
	
	@Test
	public void testConst() {
		assertEquals(Language.get_terminal_index("4"), 29);
		assertEquals(Language.get_terminal_index("4.4"), 29);
		assertEquals(Language.get_terminal_index("-4"), 29);
		assertEquals(Language.get_terminal_index("-2.4"), 29);
		assertEquals(Language.get_terminal_index("124.4324"), 29);
		assertNotEquals(Language.get_terminal_index("."), 29);
		assertNotEquals(Language.get_terminal_index("-"), 29);
	}
	
	@Test
	public void testBadTokens() {
		assertEquals(Language.get_terminal_index("2TEST"), -1);
		assertEquals(Language.get_terminal_index("TE.ST"), -1);
		assertEquals(Language.get_terminal_index("T-EST"), -1);
	}
	
	@Test
	public void testOperatorIndex() {
		assertEquals(Language.get_operator_index("*"), 1);
		assertEquals(Language.get_operator_index("/"), 2);
		assertEquals(Language.get_operator_index("+"), 3);
		assertEquals(Language.get_operator_index("-"), 4);
		assertEquals(Language.get_operator_index(":="), 13);
	}
	
	@Test
	public void testOperatorPrecedence() {
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("+")]
				[Language.get_operator_index("-")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("-")]
				[Language.get_operator_index("+")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("+")]
				[Language.get_operator_index("+")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("-")]
				[Language.get_operator_index("-")],false);
		
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("*")]
				[Language.get_operator_index("/")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("/")]
				[Language.get_operator_index("*")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("*")]
				[Language.get_operator_index("*")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("/")]
				[Language.get_operator_index("/")],false);
		
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("+")]
				[Language.get_operator_index("*")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("-")]
				[Language.get_operator_index("/")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("*")]
				[Language.get_operator_index("+")],true);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("/")]
				[Language.get_operator_index("-")],true);
	}
	
	@Test
	public void testComparatorIndex() {
		assertEquals(Language.get_operator_index("<"), 5);
		assertEquals(Language.get_operator_index("<="), 6);
		assertEquals(Language.get_operator_index("="), 7);
		assertEquals(Language.get_operator_index(">="), 8);
		assertEquals(Language.get_operator_index(">"), 9);
		assertEquals(Language.get_operator_index("<>"), 10);
		assertEquals(Language.get_operator_index("AND"), 11);
		assertEquals(Language.get_operator_index("OR"), 12);
	}
	
	@Test
	public void testComparatorPrecedence() {
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("+")]
				[Language.get_operator_index("<")],true);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("-")]
				[Language.get_operator_index("<=")],true);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("*")]
				[Language.get_operator_index(">=")],true);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("/")]
				[Language.get_operator_index(">")],true);
		
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("<")]
				[Language.get_operator_index("OR")],true);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index(">")]
				[Language.get_operator_index("AND")],true);
		
		/* There should never be more than one comparator on the stack at a time */
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("<")]
				[Language.get_operator_index("<")],null);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index(">")]
				[Language.get_operator_index(">")],null);
	}
	
	@Test
	public void testParenthesisIndex() {
		assertEquals(Language.get_operator_index(")"), 0);
		assertEquals(Language.get_operator_index("("), 13);
		
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index("(")]
				[Language.get_operator_index("<")],false);
		assertEquals(Language.OPERATOR_PRECEDENCE[Language.get_operator_index(")")]
				[Language.get_operator_index("<")],true);
	}
	
	@Test
	public void testTerminalIndex() {
		assertEquals(Language.get_terminal_index("."), 0);
		assertEquals(Language.get_terminal_index(";"), 1);
		assertEquals(Language.get_terminal_index("IF"), 2);
		assertEquals(Language.get_terminal_index("THEN"), 3);
		assertEquals(Language.get_terminal_index("ELSE"), 4);
		assertEquals(Language.get_terminal_index("FI"), 5);
		assertEquals(Language.get_terminal_index("LOOP"), 6);
		assertEquals(Language.get_terminal_index("ID"), 7);
		assertEquals(Language.get_terminal_index(":"), 8);
		assertEquals(Language.get_terminal_index("REPEAT"), 9);
		assertEquals(Language.get_terminal_index("BREAK"), 10);
		assertEquals(Language.get_terminal_index(":="), 11);
		assertEquals(Language.get_terminal_index("PRINT"), 12);
		assertEquals(Language.get_terminal_index("READ"), 13);
		assertEquals(Language.get_terminal_index(","), 14);
		assertEquals(Language.get_terminal_index("OR"), 15);
		assertEquals(Language.get_terminal_index("AND"), 16);
		assertEquals(Language.get_terminal_index("<"), 17);
		assertEquals(Language.get_terminal_index("<="), 18);
		assertEquals(Language.get_terminal_index("="), 19);
		assertEquals(Language.get_terminal_index(">="), 20);
		assertEquals(Language.get_terminal_index(">"), 21);
		assertEquals(Language.get_terminal_index("<>"), 22);
		assertEquals(Language.get_terminal_index("+"), 23);
		assertEquals(Language.get_terminal_index("-"), 24);
		assertEquals(Language.get_terminal_index("*"), 25);
		assertEquals(Language.get_terminal_index("/"), 26);
		assertEquals(Language.get_terminal_index("("), 27);
		assertEquals(Language.get_terminal_index(")"), 28);
		assertEquals(Language.get_terminal_index("CONST"), 29);
	}
	
	@Test
	public void testNonterminalIndex() {
		assertEquals(Language.get_nonterminal_index("prog"), 0);
		assertEquals(Language.get_nonterminal_index("stmt-list"), 1);
		assertEquals(Language.get_nonterminal_index("stmt"), 2);
		assertEquals(Language.get_nonterminal_index("stmt-tail"), 3);
		assertEquals(Language.get_nonterminal_index("if-stmt"), 4);
		assertEquals(Language.get_nonterminal_index("loop-stmt"), 5);
		assertEquals(Language.get_nonterminal_index("break-stmt"), 6);
		assertEquals(Language.get_nonterminal_index("assign-stmt"), 7);
		assertEquals(Language.get_nonterminal_index("read-stmt"), 8);
		assertEquals(Language.get_nonterminal_index("print-stmt"), 9);
		assertEquals(Language.get_nonterminal_index("expr"), 10);
		assertEquals(Language.get_nonterminal_index("else-part"), 11);
		assertEquals(Language.get_nonterminal_index("id-option"), 12);
		assertEquals(Language.get_nonterminal_index("id-list-tail"), 13);
		assertEquals(Language.get_nonterminal_index("bool-term"), 14);
		assertEquals(Language.get_nonterminal_index("bool-term-tail"), 15);
		assertEquals(Language.get_nonterminal_index("bool-factor"), 16);
		assertEquals(Language.get_nonterminal_index("bool-factor-tail"), 17);
		assertEquals(Language.get_nonterminal_index("arith-expr"), 18);
		assertEquals(Language.get_nonterminal_index("relation-option"), 19);
		assertEquals(Language.get_nonterminal_index("term"), 20);
		assertEquals(Language.get_nonterminal_index("term-tail"), 21);
		assertEquals(Language.get_nonterminal_index("factor"), 22);
		assertEquals(Language.get_nonterminal_index("factor-tail"), 23);
		assertEquals(Language.get_nonterminal_index("atom"), 24);
	}
	
	@Test
	public void testProg() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index(".")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index(";")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index("IF")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index("LOOP")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index("ID")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index("BREAK")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index("PRINT")], 0);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("prog")][Language.get_terminal_index("READ")], 0);
	}
	
	@Test
	public void testStmtList() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index(".")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index(";")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("IF")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("ELSE")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("FI")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("ID")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("REPEAT")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("BREAK")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("PRINT")], 1);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-list")][Language.get_terminal_index("READ")], 1);
	}
	
	@Test
	public void testStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index(".")], 10);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index(";")], 10);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("IF")], 4);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("ELSE")], 10);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("FI")], 10);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("LOOP")], 5);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("ID")], 7);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("REPEAT")], 10);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("BREAK")], 6);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("PRINT")], 9);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt")][Language.get_terminal_index("READ")], 8);
	}
	
	@Test
	public void testStmtTail() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-tail")][Language.get_terminal_index(".")], 3);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-tail")][Language.get_terminal_index(";")], 2);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-tail")][Language.get_terminal_index("ELSE")], 3);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-tail")][Language.get_terminal_index("FI")], 3);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("stmt-tail")][Language.get_terminal_index("REPEAT")], 3);
	}
	
	@Test
	public void testIfStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("if-stmt")][Language.get_terminal_index("IF")], 11);
	}
	
	@Test
	public void testLoopStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("loop-stmt")][Language.get_terminal_index("LOOP")], 14);
	}
	
	@Test
	public void testBreakStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("break-stmt")][Language.get_terminal_index("BREAK")], 15);
	}
	
	@Test
	public void testAssignStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("assign-stmt")][Language.get_terminal_index("ID")], 18);
	}
	
	@Test
	public void testReadStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("read-stmt")][Language.get_terminal_index("READ")], 20);
	}
	
	@Test
	public void testPrintStmt() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("print-stmt")][Language.get_terminal_index("PRINT")], 19);
	}
	
	@Test
	public void testExpr() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("expr")][Language.get_terminal_index("ID")], 23);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("expr")][Language.get_terminal_index("-")], 23);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("expr")][Language.get_terminal_index("(")], 23);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("expr")][Language.get_terminal_index("CONST")], 23);
	}
	
	@Test
	public void testElsePart() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("else-part")][Language.get_terminal_index("ELSE")], 12);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("else-part")][Language.get_terminal_index("FI")], 13);
	}
	
	@Test
	public void testIdOption() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-option")][Language.get_terminal_index(".")], 17);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-option")][Language.get_terminal_index(";")], 17);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-option")][Language.get_terminal_index("ELSE")], 17);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-option")][Language.get_terminal_index("FI")], 17);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-option")][Language.get_terminal_index("ID")], 16);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-option")][Language.get_terminal_index("REPEAT")], 17);
	}
	
	@Test
	public void testIdListTail() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-list-tail")][Language.get_terminal_index(".")], 22);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-list-tail")][Language.get_terminal_index(";")], 22);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-list-tail")][Language.get_terminal_index("ELSE")], 22);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-list-tail")][Language.get_terminal_index("FI")], 22);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-list-tail")][Language.get_terminal_index("REPEAT")], 22);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("id-list-tail")][Language.get_terminal_index(",")], 21);
	}
	
	@Test
	public void testBoolTerm() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term")][Language.get_terminal_index("ID")], 26);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term")][Language.get_terminal_index("-")], 26);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term")][Language.get_terminal_index("(")], 26);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term")][Language.get_terminal_index("CONST")], 26);
	}
	
	@Test
	public void testBoolTermTail() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index(".")], 25);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index(";")], 25);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index("THEN")], 25);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index("ELSE")], 25);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index("FI")], 25);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index("REPEAT")], 25);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index("OR")], 24);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-term-tail")][Language.get_terminal_index(")")], 25);
	}
	
	@Test
	public void testBoolFactor() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor")][Language.get_terminal_index("ID")], 29);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor")][Language.get_terminal_index("-")], 29);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor")][Language.get_terminal_index("(")], 29);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor")][Language.get_terminal_index("CONST")], 29);
	}
	
	@Test
	public void testBoolFactorTail() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index(".")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index(";")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index("THEN")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index("ELSE")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index("FI")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index("REPEAT")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index("OR")], 28);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index("AND")], 27);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("bool-factor-tail")][Language.get_terminal_index(")")], 28);
	}
	
	@Test
	public void testArithExpr() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("arith-expr")][Language.get_terminal_index("ID")], 37);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("arith-expr")][Language.get_terminal_index("-")], 37);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("arith-expr")][Language.get_terminal_index("(")], 37);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("arith-expr")][Language.get_terminal_index("CONST")], 37);
	}
	
	@Test
	public void testRelationOption() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index(".")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index(";")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("THEN")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("ELSE")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("FI")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("REPEAT")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("OR")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("AND")], 36);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("<")], 30);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("<=")], 31);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("=")], 32);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index(">=")], 33);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index(">")], 34);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index("<>")], 35);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("relation-option")][Language.get_terminal_index(")")], 36);
	}
	
	@Test
	public void testTerm() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term")][Language.get_terminal_index("ID")], 41);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term")][Language.get_terminal_index("-")], 41);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term")][Language.get_terminal_index("(")], 41);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term")][Language.get_terminal_index("CONST")], 41);
	}
	
	@Test
	public void testTermTail() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index(".")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index(";")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("THEN")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("ELSE")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("FI")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("REPEAT")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("OR")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("AND")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("<")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("<=")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("=")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index(">=")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index(">")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("<>")], 40);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("+")], 38);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index("-")], 39);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("term-tail")][Language.get_terminal_index(")")], 40);
	}
	
	@Test
	public void testFactor() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor")][Language.get_terminal_index("ID")], 46);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor")][Language.get_terminal_index("-")], 45);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor")][Language.get_terminal_index("(")], 47);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor")][Language.get_terminal_index("CONST")], 46);
	}
	
	@Test
	public void testFactorTail() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index(".")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index(";")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("THEN")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("ELSE")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("FI")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("REPEAT")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("OR")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("AND")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("<")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("<=")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("=")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index(">=")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index(">")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("<>")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("+")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("-")], 44);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("*")], 42);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index("/")], 43);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("factor-tail")][Language.get_terminal_index(")")], 44);
	}
	
	@Test
	public void testAtom() {
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("atom")][Language.get_terminal_index("ID")], 48);
		assertEquals(Language.SYNTAX[Language.get_nonterminal_index("atom")][Language.get_terminal_index("CONST")], 49);
	}
	
}
