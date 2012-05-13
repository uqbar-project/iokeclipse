package org.uqbar.eclipse.blide.editor.text.rule;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.text.detector.KeyWordDetector;
import org.uqbar.eclipse.blide.editor.text.detector.WhitespaceDetector;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * @author jfernandes
 */
public class RulesBuilder {
	protected static final String DOBLE_QUOTE_CHAR = "\"";
	protected static final String QUOTE_CHAR = "'";
	protected static final char STRING_ESCAPE_CHAR = '\\';
	private static final IPredicateRule[] PREDICATE_RULES_EMPTY_ARRAY = new IPredicateRule[0];
	private static final IRule[] RULES_EMPTY_ARRAY = new IRule[0];
	private List<IRule> rules = new ArrayList<IRule>();
	private ColorManager colorManager;
	
	public RulesBuilder() {
	}
	
	public RulesBuilder(ColorManager manager) {
		this.colorManager = manager;
	}

	public RulesBuilder addRule(IRule rule) {
		this.rules.add(rule);
		return this;
	}
	
	public RulesBuilder addSingleLineRule(String startSequence, String endSequence, IToken token) {
		return this.addRule(new SingleLineRule(startSequence, endSequence, token));
	}
	
	public RulesBuilder addSingleLineRule(String startSequence, String endSequence, TextAttribute textAttribute) {
		return this.addSingleLineRule(startSequence, endSequence, new Token(textAttribute));
	}
	
	public RulesBuilder addSingleLineRule(String startSequence, String endSequence, RGB color) {
		return this.addSingleLineRule(startSequence, endSequence, new TextAttribute(this.colorManager.getColor(color)));
	}
	
	public RulesBuilder addSingleLineRule(String startSequence, String endSequence, Color color) {
		return this.addSingleLineRule(startSequence, endSequence, new TextAttribute(color));
	}
	
	public RulesBuilder addSingleLineRule(String startSequence, String endSequence, IToken token, char escapeCharacter) {
		return this.addRule(new SingleLineRule(startSequence, endSequence, token, escapeCharacter));
	}
	
	public RulesBuilder addSingleLineDoubleQuoteStringRule(IToken token) {
		return this.addSingleLineRule(DOBLE_QUOTE_CHAR, DOBLE_QUOTE_CHAR, token, STRING_ESCAPE_CHAR);
	}
	
	public RulesBuilder addSingleLineQuoteStringRule(IToken token) {
		return this.addRule(new SingleLineRule(QUOTE_CHAR, QUOTE_CHAR, token, STRING_ESCAPE_CHAR));
	}
	
	public RulesBuilder addSingleLineDoubleQuoteStringRule(String tokenData) {
		return this.addSingleLineDoubleQuoteStringRule(new Token(tokenData));
	}
	
	public RulesBuilder addEndOfLineRule(String startSequence, String tokenData) {
		return this.addRule(new EndOfLineRule(startSequence, new Token(tokenData)));
	}
	
	protected List<IRule> getRules() {
		return this.rules;
	}
	
	public IRule[] buildIRuleArray() {
		return this.getRules().toArray(RULES_EMPTY_ARRAY);
	}
	
	public IPredicateRule[] buildIPredicateRuleArray() {
		return this.getRules().toArray(PREDICATE_RULES_EMPTY_ARRAY);
	}

	public RulesBuilder addWhitespaceRule() {
		return this.addWhitespaceRule(new WhitespaceDetector());
	}
	
	public RulesBuilder addWhitespaceRule(IWhitespaceDetector whitespaceDetector) {
		return this.addRule(new WhitespaceRule(whitespaceDetector));
	}

	public RulesBuilder addKeywordsRule(IWordDetector wordDetector, IToken defaultToken, Color color, int fontStyle, String... keywords) {
		CombinedWordRule combinedWordRule = new CombinedWordRule(wordDetector, defaultToken);
			
		CombinedWordRule.WordMatcher wordRule = new CombinedWordRule.WordMatcher();
		IToken keyWordToken = new Token(new TextAttribute(color, null, fontStyle));
		for (String keyword : keywords) {
			wordRule.addWord(keyword, keyWordToken);
		}
		combinedWordRule.addWordMatcher(wordRule);

		return this.addRule(combinedWordRule);
	}

	public void addKeyWordsRules(String[] keywords, IToken defaultToken, RGB color, int style) {
		this.addKeywordsRule(new KeyWordDetector(), defaultToken, getColor(color), style, keywords);
	}

	public KeyWordRulesBuilder keyWordsRules(IToken defaultToken) {
		return new KeyWordRulesBuilder(this, defaultToken);
	}

	public Color getColor(RGB color) {
		return this.colorManager.getColor(color);
	}
	
}