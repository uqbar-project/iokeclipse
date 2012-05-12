package org.uqbar.eclipse.blide.editor.text.rule;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * Rule to detect java brackets.
 * 
 * @since 3.3
 */
public class BracketRule implements IRule {
	/** Brackets */
	private final char[] JAVA_BRACKETS = { '(', ')', '{', '}', '[', ']' };
	/** Token to return for this rule */
	private final IToken fToken;

	/**
	 * Creates a new bracket rule.
	 * @param token Token to use for this rule
	 */
	public BracketRule(IToken token) {
		this.fToken = token;
	}

	/**
	 * Is this character a bracket character?
	 * @param character Character to determine whether it is a bracket character
	 * @return <code>true</code> if the character is a bracket,
	 *         <code>false</code> otherwise.
	 */
	public boolean isBracket(char character) {
		for (int index = 0; index < JAVA_BRACKETS.length; index++) {
			if (JAVA_BRACKETS[index] == character) {
				return true;
			}
		}
		return false;
	}

	/*
	 * @see
	 * org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {
		int character = scanner.read();
		if (isBracket((char) character)) {
			do {
				character = scanner.read();
			} while (isBracket((char) character));
			scanner.unread();
			return this.fToken;
		} else {
			scanner.unread();
			return Token.UNDEFINED;
		}
	}
}