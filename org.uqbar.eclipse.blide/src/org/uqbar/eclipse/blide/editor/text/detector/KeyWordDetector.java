package org.uqbar.eclipse.blide.editor.text.detector;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * @author jfernandes
 */
public class KeyWordDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return Character.isLetter(c) || c == '.' || c == '<' || c == '-';
	}
	
	@Override
	public boolean isWordPart(char c) {
		return Character.isLetterOrDigit(c);
	}

}
