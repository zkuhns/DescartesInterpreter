package tests;

import main.Tokenizer;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

public class TokenizerTests {
	@Test
	public void testSpace() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("AB ; ED >", 1);
		
		assertEquals(tokens.get(0), "AB");
		assertEquals(tokens.get(1), ";");
		assertEquals(tokens.get(2), "ED");
		assertEquals(tokens.get(3), ">");
	}
	
	@Test
	public void testLetter() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("AB CD E F", 1);
		
		assertEquals(tokens.get(0), "AB");
		assertEquals(tokens.get(1), "CD");
		assertEquals(tokens.get(2), "E");
		assertEquals(tokens.get(3), "F");
	}
	
	@Test
	public void testNumber() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("13 32 1 2", 1);
		
		assertEquals(tokens.get(0), "13");
		assertEquals(tokens.get(1), "32");
		assertEquals(tokens.get(2), "1");
		assertEquals(tokens.get(3), "2");
	}
	
	@Test
	public void testPeriod() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("12.2 . .. 4.3 .", 1);
		
		assertEquals(tokens.get(0), "12.2");
		assertEquals(tokens.get(1), ".");
		assertEquals(tokens.get(2), ".");
		assertEquals(tokens.get(3), ".");
		assertEquals(tokens.get(4), "4.3");
		assertEquals(tokens.get(5), ".");
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(". ..", 1);
		assertEquals(tokens.get(0), ".");
		assertEquals(tokens.get(1), ".");
		assertEquals(tokens.get(2), ".");
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(".. .", 1);
		assertEquals(tokens.get(0), ".");
		assertEquals(tokens.get(1), ".");
		assertEquals(tokens.get(2), ".");
	}
	
	@Test
	public void testColon() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(":: : :=", 1);
		assertEquals(tokens.get(0), ":");
		assertEquals(tokens.get(1), ":");
		assertEquals(tokens.get(2), ":");
		assertEquals(tokens.get(3), ":=");
	}
	
	@Test
	public void testSubtract() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("4-3 -5", 1);
		assertEquals(tokens.get(0), "4");
		assertEquals(tokens.get(1), "-");
		assertEquals(tokens.get(2), "3");
		assertEquals(tokens.get(3), "-");
		assertEquals(tokens.get(4), "5");
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("4--3 -5", 1);
		assertEquals(tokens.get(0), "4");
		assertEquals(tokens.get(1), "-");
		assertEquals(tokens.get(2), "-");
		assertEquals(tokens.get(3), "3");
		assertEquals(tokens.get(4), "-");
		assertEquals(tokens.get(5), "5");
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("4--3 -5.1---", 1);
		assertEquals(tokens.get(0), "4");
		assertEquals(tokens.get(1), "-");
		assertEquals(tokens.get(2), "-");
		assertEquals(tokens.get(3), "3");
		assertEquals(tokens.get(4), "-");
		assertEquals(tokens.get(5), "5.1");
		assertEquals(tokens.get(6), "-");
		assertEquals(tokens.get(7), "-");
		assertEquals(tokens.get(8), "-");
	}
	
	@Test
	public void testAdd() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("1+2 +3+ 4 + 5", 1);
		assertEquals(tokens.get(0), "1");
		assertEquals(tokens.get(1), "+");
		assertEquals(tokens.get(2), "2");
		assertEquals(tokens.get(3), "+");
		assertEquals(tokens.get(4), "3");
		assertEquals(tokens.get(5), "+");
		assertEquals(tokens.get(6), "4");
		assertEquals(tokens.get(7), "+");
		assertEquals(tokens.get(8), "5");
	}
	
	@Test
	public void testMultiply() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("1*2 *3* 4 * 5", 1);
		assertEquals(tokens.get(0), "1");
		assertEquals(tokens.get(1), "*");
		assertEquals(tokens.get(2), "2");
		assertEquals(tokens.get(3), "*");
		assertEquals(tokens.get(4), "3");
		assertEquals(tokens.get(5), "*");
		assertEquals(tokens.get(6), "4");
		assertEquals(tokens.get(7), "*");
		assertEquals(tokens.get(8), "5");
	}
	
	@Test
	public void testDivide() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("1/2 /3/ 4 / 5", 1);
		assertEquals(tokens.get(0), "1");
		assertEquals(tokens.get(1), "/");
		assertEquals(tokens.get(2), "2");
		assertEquals(tokens.get(3), "/");
		assertEquals(tokens.get(4), "3");
		assertEquals(tokens.get(5), "/");
		assertEquals(tokens.get(6), "4");
		assertEquals(tokens.get(7), "/");
		assertEquals(tokens.get(8), "5");
	}
	
	@Test
	public void testLessThan() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("<> <= <<>", 1);
		assertEquals(tokens.get(0), "<>");
		assertEquals(tokens.get(1), "<=");
		assertEquals(tokens.get(2), "<");
		assertEquals(tokens.get(3), "<>");
	}
	
	@Test
	public void testGreaterThan() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(">> >= <>", 1);
		assertEquals(tokens.get(0), ">");
		assertEquals(tokens.get(1), ">");
		assertEquals(tokens.get(2), ">=");
		assertEquals(tokens.get(3), "<>");
	}
	
	@Test
	public void testEquals() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(":=:= = = :==", 1);
		assertEquals(tokens.get(0), ":=");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "=");
		assertEquals(tokens.get(3), "=");
		assertEquals(tokens.get(4), ":=");
		assertEquals(tokens.get(5), "=");
	}
	
	@Test
	public void testParenthesis() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("(()) )( )(()", 1);
		assertEquals(tokens.get(0), "(");
		assertEquals(tokens.get(1), "(");
		assertEquals(tokens.get(2), ")");
		assertEquals(tokens.get(3), ")");
		assertEquals(tokens.get(4), ")");
		assertEquals(tokens.get(5), "(");
		assertEquals(tokens.get(6), ")");
		assertEquals(tokens.get(7), "(");
		assertEquals(tokens.get(8), "(");
		assertEquals(tokens.get(9), ")");
	}
	
	@Test
	public void testSingle() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("(", 1);
		assertEquals(tokens.get(0), "(");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("A", 1);
		assertEquals(tokens.get(0), "A");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("1", 1);
		assertEquals(tokens.get(0), "1");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(";", 1);
		assertEquals(tokens.get(0), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(":", 1);
		assertEquals(tokens.get(0), ":");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(",", 1);
		assertEquals(tokens.get(0), ",");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("<", 1);
		assertEquals(tokens.get(0), "<");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine(">", 1);
		assertEquals(tokens.get(0), ">");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("=", 1);
		assertEquals(tokens.get(0), "=");
	}
	
	@Test
	public void testE() {
		Tokenizer tokenizer = new Tokenizer();
		ArrayList<String> tokens;
		
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("SUM     := 1;", 1);
		assertEquals(tokens.get(0), "SUM");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "1");
		assertEquals(tokens.get(3), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("EPSILON := 0.000001;", 1);
		assertEquals(tokens.get(0), "EPSILON");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "0.000001");
		assertEquals(tokens.get(3), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("K       :=1;", 1);
		assertEquals(tokens.get(0), "K");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "1");
		assertEquals(tokens.get(3), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("LOOP MAINLOOP:", 1);
		assertEquals(tokens.get(0), "LOOP");
		assertEquals(tokens.get(1), "MAINLOOP");
		assertEquals(tokens.get(2), ":");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    I     :=1;", 1);
		assertEquals(tokens.get(0), "I");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "1");
		assertEquals(tokens.get(3), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    TERM  :=1;", 1);
		assertEquals(tokens.get(0), "TERM");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "1");
		assertEquals(tokens.get(3), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    LOOP FACTORIAL:", 1);
		assertEquals(tokens.get(0), "LOOP");
		assertEquals(tokens.get(1), "FACTORIAL");
		assertEquals(tokens.get(2), ":");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("        TERM := TERM * I;", 1);
		assertEquals(tokens.get(0), "TERM");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "TERM");
		assertEquals(tokens.get(3), "*");
		assertEquals(tokens.get(4), "I");
		assertEquals(tokens.get(5), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("        I    := I+1;", 1);
		assertEquals(tokens.get(0), "I");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "I");
		assertEquals(tokens.get(3), "+");
		assertEquals(tokens.get(4), "1");
		assertEquals(tokens.get(5), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("        IF I>K THEN BREAK FI", 1);
		assertEquals(tokens.get(0), "IF");
		assertEquals(tokens.get(1), "I");
		assertEquals(tokens.get(2), ">");
		assertEquals(tokens.get(3), "K");
		assertEquals(tokens.get(4), "THEN");
		assertEquals(tokens.get(5), "BREAK");
		assertEquals(tokens.get(6), "FI");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    REPEAT;", 1);
		assertEquals(tokens.get(0), "REPEAT");
		assertEquals(tokens.get(1), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    NEWSUM    := SUM + 1/TERM;", 1);
		assertEquals(tokens.get(0), "NEWSUM");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "SUM");
		assertEquals(tokens.get(3), "+");
		assertEquals(tokens.get(4), "1");
		assertEquals(tokens.get(5), "/");
		assertEquals(tokens.get(6), "TERM");
		assertEquals(tokens.get(7), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    IF SUM - NEWSUM < EPSILON AND NEWSUM - SUM < EPSILON THEN", 1);
		assertEquals(tokens.get(0), "IF");
		assertEquals(tokens.get(1), "SUM");
		assertEquals(tokens.get(2), "-");
		assertEquals(tokens.get(3), "NEWSUM");
		assertEquals(tokens.get(4), "<");
		assertEquals(tokens.get(5), "EPSILON");
		assertEquals(tokens.get(6), "AND");
		assertEquals(tokens.get(7), "NEWSUM");
		assertEquals(tokens.get(8), "-");
		assertEquals(tokens.get(9), "SUM");
		assertEquals(tokens.get(10), "<");
		assertEquals(tokens.get(11), "EPSILON");
		assertEquals(tokens.get(12), "THEN");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("         RESULT := NEWSUM;", 1);
		assertEquals(tokens.get(0), "RESULT");
		assertEquals(tokens.get(1), ":=");
		assertEquals(tokens.get(2), "NEWSUM");
		assertEquals(tokens.get(3), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("         BREAK MAINLOOP", 1);
		assertEquals(tokens.get(0), "BREAK");
		assertEquals(tokens.get(1), "MAINLOOP");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("    ELSE SUM   := NEWSUM;  K := K + 1    FI", 1);
		assertEquals(tokens.get(0), "ELSE");
		assertEquals(tokens.get(1), "SUM");
		assertEquals(tokens.get(2), ":=");
		assertEquals(tokens.get(3), "NEWSUM");
		assertEquals(tokens.get(4), ";");
		assertEquals(tokens.get(5), "K");
		assertEquals(tokens.get(6), ":=");
		assertEquals(tokens.get(7), "K");
		assertEquals(tokens.get(8), "+");
		assertEquals(tokens.get(9), "1");
		assertEquals(tokens.get(10), "FI");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("REPEAT;", 1);
		assertEquals(tokens.get(0), "REPEAT");
		assertEquals(tokens.get(1), ";");
		tokens = (ArrayList<String>) tokenizer.tokenizeLine("PRINT RESULT .", 1);
		assertEquals(tokens.get(0), "PRINT");
		assertEquals(tokens.get(1), "RESULT");
		assertEquals(tokens.get(2), ".");
	}
}
