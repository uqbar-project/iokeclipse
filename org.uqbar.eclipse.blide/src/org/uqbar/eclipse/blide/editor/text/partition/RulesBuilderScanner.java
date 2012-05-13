package org.uqbar.eclipse.blide.editor.text.partition;

import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;
import org.uqbar.eclipse.blide.editor.text.scanner.AbstractRuleBasedTokenScanner;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * Delegates the rules configuration to a {@link PartitionRuleDirector} object.
 * 
 * @author jfernandes
 */
public class RulesBuilderScanner extends AbstractRuleBasedTokenScanner {
	private final PartitionRuleDirector director;

	public RulesBuilderScanner(PartitionRuleDirector director, ColorManager colorManager) {
		super(colorManager);
		this.director = director;
		this.init();
	}

	@Override
	protected void addRules(ColorManager manager, RulesBuilder rules) {
		this.director.describe(rules);
	}

}
