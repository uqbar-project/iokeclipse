package org.uqbar.eclipse.blide.editor.text.rule;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * Rule to detect operators.
 * 
 * @since 3.0
 */
public class OperatorRule implements IRule {
	/** Operators */
	private final char[] operators = { ';', '.', '=', '/', '\\', '+', '-', '*', '<', '>', ':', '?', '!', ',', '|', '&', '^', '%', '~' };
	/** Token to return for this rule */
	private final IToken fToken;

	/**
	 * Creates a new operator rule.
	 * @param token Token to use for this rule
	 */
	public OperatorRule(IToken token) {
		this.fToken = token;
	}

	/**
	 * Is this character an operator character?
	 * 
	 * @param character
	 *            Character to determine whether it is an operator character
	 * @return <code>true</code> iff the character is an operator,
	 *         <code>false</code> otherwise.
	 */
	public boolean isOperator(char character) {
		for (int index = 0; index < operators.length; index++) {
			if (operators[index] == character)
				return true;
		}
		return false;
	}

	/*
	 * @see
	 * org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {
		int character = scanner.read();
		if (isOperator((char) character)) {
			do {
				character = scanner.read();
			} while (isOperator((char) character));
			scanner.unread();
			return fToken;
		} else {
			scanner.unread();
			return Token.UNDEFINED;
		}
	}
}