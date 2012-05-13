package org.uqbar.eclipse.blide.editor.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
/**
 * @author jfernandes
 */
public abstract class TokenBuilder {
	private List<TokenBuilder> components = new ArrayList<TokenBuilder>();
	
	public TokenBuilder and(TokenBuilder another) {
		this.components.add(another);
		return this;
	}
	
	public IToken build(ColorManager colorManager) {
		TokenSpec spec = new TokenSpec(colorManager);
		this.specify(spec);
		for (TokenBuilder builder : this.components) {
			builder.specify(spec);
		}
		return spec.create();
	}

	protected abstract void specify(TokenSpec spec);

	/**
	 * 
	 * @author jfernandes
	 */
	public class TokenSpec {
		private final ColorManager colorManager;
		public Color foregroundColor;
		public Color backgroundColor;
		public int style = SWT.NONE;
		public Font font;

		public TokenSpec(ColorManager colorManager) {
			this.colorManager = colorManager;
		}

		public IToken create() {
			return new Token(new TextAttribute(this.foregroundColor, this.backgroundColor, this.style, this.font));
		}

		public void foregroundColor(int red, int green, int blue) {
			this.foregroundColor = getColor(red, green, blue);
		}

		protected Color getColor(int red, int green, int blue) {
			return this.colorManager.getColor(new RGB(red, green, blue));
		}

		public void style(int style) {
			this.style |= style;
		}
		
	}
	
}
