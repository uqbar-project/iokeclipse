package org.uqbar.eclipse.blide.editor.utils;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;
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
	
}
