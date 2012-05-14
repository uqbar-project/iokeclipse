package org.uqbar.iokeclipse.editor;

import org.eclipse.jface.text.rules.IWordDetector;

/**
 * 
 * @author jfernandes
 */
public class MessageWordDetector implements IWordDetector {

	@Override
	public boolean isWordStart(char c) {
		return '\'' == c;
	}

	@Override
	public boolean isWordPart(char c) {
		return c != ' ';
	}

}
