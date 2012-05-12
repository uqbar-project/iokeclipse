package org.uqbar.eclipse.blide.editor.text.detector;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

/**
 * @author jfernandes
 */
public class WhitespaceDetector implements IWhitespaceDetector {

	public boolean isWhitespace(char c) {
		return c == ' ';
	}
}
