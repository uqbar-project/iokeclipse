package org.uqbar.iokeclipse.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;
import org.uqbar.eclipse.blide.editor.text.partition.LanguagePartition;
import org.uqbar.eclipse.blide.editor.text.partition.PartitionRuleDirector;
import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;

/**
 * 
 * @author jfernandes
 */
public class IokeLanguageDefinition extends LanguageDefinition {
	public final static String PARTITION_COMMENT = "__ioke_comment";
		public final static RGB PARTITION_COMMENT_COLOR = new RGB(110, 225, 95);
	public final static String PARTITION_STRING = "__ioke_string";
		public final static RGB PARTITION_STRING_COLOR = new RGB(42, 0, 255);
	private List<LanguagePartition> partitions = new ArrayList<LanguagePartition>();

	public IokeLanguageDefinition() {
		this.declarePartitions();
	}
	
	protected void declarePartitions() {
		this.partitions.add(new LanguagePartition(PARTITION_COMMENT, PARTITION_COMMENT_COLOR, 
				new PartitionRuleDirector() {
					@Override
					public void describe(RulesBuilder builder) {
						builder.addEndOfLineRule(";", PARTITION_COMMENT);
					}
				}
		));
		this.partitions.add(new LanguagePartition(PARTITION_STRING, PARTITION_STRING_COLOR,
				new PartitionRuleDirector() {
					@Override
					public void describe(RulesBuilder builder) {
						builder.addSingleLineDoubleQuoteStringRule(PARTITION_STRING);
					}
				}
		));
	}

	@Override
	public void definePartitions(RulesBuilder rules) {
		for (LanguagePartition partition : this.partitions) {
			partition.getRuleDirector().describe(rules);
		}
	}
	
	@Override
	public List<LanguagePartition> getPartitions() {
		return this.partitions;
	}

}
