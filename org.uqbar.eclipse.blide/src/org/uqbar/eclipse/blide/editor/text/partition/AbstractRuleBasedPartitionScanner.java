package org.uqbar.eclipse.blide.editor.text.partition;

import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * NOTE: subclasses must directly call the init method on their constructor
 * this allow them to assign instance variables that will be used later in the
 * template methods.
 * 
 * @author jfernandes
 */
public abstract class AbstractRuleBasedPartitionScanner extends RuleBasedPartitionScanner {
	private ColorManager colorManager;

	public AbstractRuleBasedPartitionScanner(ColorManager colorManager) {
		super();
		this.colorManager = colorManager;
	}

	protected void init() {
		RulesBuilder builder = new RulesBuilder(this.colorManager);
		this.addRules(builder);
		this.setPredicateRules(builder.buildIPredicateRuleArray());
	}

	protected abstract void addRules(RulesBuilder rules);

}