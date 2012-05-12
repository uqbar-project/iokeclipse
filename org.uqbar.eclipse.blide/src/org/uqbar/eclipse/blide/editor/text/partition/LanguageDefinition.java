package org.uqbar.eclipse.blide.editor.text.partition;

import java.util.List;

import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;

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
			types[i++] = partition.getKey();
		}
		return types;
	}

}
