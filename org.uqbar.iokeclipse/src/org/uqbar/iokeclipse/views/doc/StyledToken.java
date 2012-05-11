package org.uqbar.iokeclipse.views.doc;

import org.eclipse.swt.graphics.TextStyle;

/**
 * 
 * @author jfernandes
 */
public class StyledToken {
	private String text;
	private TextStyle style;

	public StyledToken(String text, TextStyle style) {
		this.text = text;
		this.style = style;
	}

	public TextStyle style() {
		return this.style;
	}

	public String text() {
		return this.text;
	}

}
