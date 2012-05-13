package org.uqbar.eclipse.blide.editor.text.scanner;

import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public abstract class AbstractRuleBasedTokenScanner extends RuleBasedScanner {
	private final ColorManager manager;

	public AbstractRuleBasedTokenScanner(ColorManager manager) {
		this.manager = manager;
	}

	protected void init() {
		RulesBuilder builder = new RulesBuilder(manager);
		this.addRules(manager, builder);
		this.setRules(builder.buildIRuleArray());
	}

	protected abstract void addRules(ColorManager manager, RulesBuilder rules);

}