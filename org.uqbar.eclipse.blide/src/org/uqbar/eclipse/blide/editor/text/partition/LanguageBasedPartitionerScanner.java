package org.uqbar.eclipse.blide.editor.text.partition;

import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;

/**
 * Default {@link AbstractRuleBasedPartitionScanner} implementation
 * that delegates the behavior to the LanguageDefinition object
 * allowing to compose back the behavior into a cohesive single object
 * since eclipse has modeled a lot of the low level concepts splitted into
 * small single-purpose objects, this class converges it back into leveraging
 * the abstraction level.
 * 
 * @author jfernandes
 */
public class LanguageBasedPartitionerScanner extends AbstractRuleBasedPartitionScanner {
	private LanguageDefinition language;

	public LanguageBasedPartitionerScanner(LanguageDefinition language) {
		this.language = language;
		this.init();
	}

	@Override
	protected void addRules(RulesBuilder rules) {
		this.language.definePartitions(rules);
	}

}
