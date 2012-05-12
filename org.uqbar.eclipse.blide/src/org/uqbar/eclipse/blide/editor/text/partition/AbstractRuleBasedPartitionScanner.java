package org.uqbar.eclipse.blide.editor.text.partition;

import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;

/**
 * NOTE: subclasses must directly call the init method on their constructor
 * this allow them to assign instance variables that will be used later in the
 * template methods.
 * 
 * @author jfernandes
 */
public abstract class AbstractRuleBasedPartitionScanner extends RuleBasedPartitionScanner {

	public AbstractRuleBasedPartitionScanner() {
		super();
	}

	protected void init() {
		RulesBuilder builder = new RulesBuilder();
		this.addRules(builder);
		this.setPredicateRules(builder.buildIPredicateRuleArray());
	}

	protected abstract void addRules(RulesBuilder rules);

}