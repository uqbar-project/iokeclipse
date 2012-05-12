package org.uqbar.eclipse.blide.editor.text.partition;

import org.eclipse.swt.graphics.RGB;

/**
 * 
 * @author jfernandes
 */
public class LanguagePartition {
	private final String key;
	private final RGB color;
	private final PartitionRuleDirector ruleDirector;

	public LanguagePartition(String key, RGB color, PartitionRuleDirector ruleDirector) {
		this.key = key;
		this.color = color;
		this.ruleDirector = ruleDirector;
	}
	
	public String getKey() {
		return key;
	}
	
	public RGB getColor() {
		return color;
	}
	
	public PartitionRuleDirector getRuleDirector() {
		return ruleDirector;
	}

}