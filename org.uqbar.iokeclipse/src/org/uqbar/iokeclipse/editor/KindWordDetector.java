package org.uqbar.iokeclipse.editor;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * 
 * @author jfernandes
 */
public class KindWordDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return Character.isUpperCase(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return Character.isLetter(c);
	}

}
