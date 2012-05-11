package org.uqbar.iokeclipse.views.doc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;

/**
 * @author jfernandes
 */
public class StyledTextString {
	private String separator = "\n";
	private List<StyledToken> tokens = new ArrayList<StyledToken>();

	public void setTo(StyledText text) {
		text.setText(this.text());
		text.setStyleRanges(this.styles());
	}
	
	protected String text() {
		StringBuilder text = new StringBuilder();
		for (StyledToken token : this.tokens) {
			text.append(token.text()).append(this.separator);
		}
		return text.toString();
	}

	protected StyleRange[] styles() {
		StyleRange[] ranges = new StyleRange[this.tokens.size()];
		int i = 0;
		int offset = 0;
		for (StyledToken token : this.tokens) {
			ranges[i] = new StyleRange(token.style());
			ranges[i].start = offset;
			ranges[i].length = token.text().length();
			offset = token.text().length() + this.separator.length();
			i++;
		}
		return ranges;
	}

	public void addToken(StyledToken token) {
		this.tokens.add(token);
	}
	
}
