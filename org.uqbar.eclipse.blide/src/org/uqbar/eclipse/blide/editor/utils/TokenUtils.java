package org.uqbar.eclipse.blide.editor.utils;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public class TokenUtils {

	public static Token createToken(ColorManager manager, RGB aColor) {
		return new Token(new TextAttribute(manager.getColor(aColor)));
	}
	
	public static Token createToken(int style) {
		
		
		return new Token(new TextAttribute(null, null, style));
	}
	
	public static TokenBuilder color(final int red, final int green, final int blue) {
		return new TokenBuilder() {
			@Override
			protected void specify(TokenSpec spec) {
				spec.foregroundColor(red, green, blue);
			}
		};
	}
	
	public static TokenBuilder bold() {
		return style(SWT.BOLD);
	}
	
	public static TokenBuilder italic() {
		return style(SWT.ITALIC);
	}

	public static TokenBuilder style(final int style) {
		return new TokenBuilder() {
			@Override
			protected void specify(TokenSpec spec) {
				spec.style(style);
			}
		};
	}
	
}
