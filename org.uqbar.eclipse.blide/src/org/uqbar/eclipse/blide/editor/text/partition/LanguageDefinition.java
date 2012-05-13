package org.uqbar.eclipse.blide.editor.text.partition;

import java.util.List;

import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public abstract class LanguageDefinition {

	public abstract void definePartitions(RulesBuilder rules);

	public abstract List<LanguagePartition> getPartitions();

	public String[] getContentTypes() {
		List<LanguagePartition> partitions = this.getPartitions();
		String[] types = new String[partitions.size()];
		int i = 0;
		for (LanguagePartition partition : partitions) {
			types[i++] = partition.getContentType();
		}
		return types;
	}

	public abstract RGB getDefaultPartitionTokenColor();

	public RuleBasedScanner createDefaultPartionTokenScanner(ColorManager colorManager) {
		return new RulesBuilderScanner(new PartitionRuleDirector() {
			@Override
			public void describe(RulesBuilder builder) {
				describeDefaultPartitionTokenRules(builder);
			}
		}, colorManager);
	}

	protected abstract void describeDefaultPartitionTokenRules(RulesBuilder builder);

}
