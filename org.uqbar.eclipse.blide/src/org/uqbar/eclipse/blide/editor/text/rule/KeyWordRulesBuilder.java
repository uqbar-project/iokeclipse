package org.uqbar.eclipse.blide.editor.text.rule;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.text.detector.KeyWordDetector;

/**
 * 
 * @author jfernandes
 */
public class KeyWordRulesBuilder {
	private final RulesBuilder rulesBuilder;
	private CombinedWordRule.WordMatcher wordRule;

	public KeyWordRulesBuilder(RulesBuilder rulesBuilder, IToken defaultToken) {
		this.rulesBuilder = rulesBuilder;
		
		CombinedWordRule combinedWordRule = new CombinedWordRule(new KeyWordDetector(), defaultToken);
		this.wordRule = new CombinedWordRule.WordMatcher();
		combinedWordRule.addWordMatcher(this.wordRule);
		
		this.rulesBuilder.addRule(combinedWordRule);
	}

	public KeyWordRulesBuilder keywords(String[] keywords, RGB color, int style) {
		IToken keyWordToken = new Token(new TextAttribute(this.rulesBuilder.getColor(color), null, style));
		for (String keyword : keywords) {
			this.wordRule.addWord(keyword, keyWordToken);
		}
		return this;
	}

}
