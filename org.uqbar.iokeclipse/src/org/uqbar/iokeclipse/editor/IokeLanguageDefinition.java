package org.uqbar.iokeclipse.editor;

import static org.uqbar.eclipse.blide.editor.utils.TokenUtils.bold;
import static org.uqbar.eclipse.blide.editor.utils.TokenUtils.color;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;
import org.uqbar.eclipse.blide.editor.text.partition.LanguagePartition;
import org.uqbar.eclipse.blide.editor.text.partition.PartitionRuleDirector;
import org.uqbar.eclipse.blide.editor.text.rule.BracketRule;
import org.uqbar.eclipse.blide.editor.text.rule.RulesBuilder;
import org.uqbar.eclipse.blide.editor.utils.TokenUtils;

/**
 * 
 * @author jfernandes
 */
public class IokeLanguageDefinition extends LanguageDefinition {
	public final static String PARTITION_COMMENT = "__ioke_comment";
		public final static RGB PARTITION_COMMENT_COLOR = new RGB(63, 127, 95);
	public final static String PARTITION_STRING = "__ioke_string";
		public final static RGB PARTITION_STRING_COLOR = new RGB(42, 0, 255);
	private List<LanguagePartition> partitions = new ArrayList<LanguagePartition>();

	public IokeLanguageDefinition() {
		this.declarePartitions();
	}
	
	protected void declarePartitions() {
		partition(PARTITION_COMMENT, PARTITION_COMMENT_COLOR, 
				new PartitionRuleDirector() {
				@Override
				public void describe(RulesBuilder builder) {
					builder.addEndOfLineRule(";", PARTITION_COMMENT);
				}
			},
			null
		);
		
//		partition(PARTITION_STRING,
//				color(PARTITION_STRING_COLOR),
//				parts(
//					part("expression",
//						rule(betweenRule("#{", "}"))
//						color()
//					)
//				)
//		)
		
		partition(PARTITION_STRING, PARTITION_STRING_COLOR,
				new PartitionRuleDirector() {
					@Override
					public void describe(RulesBuilder builder) {
						builder.addSingleLineDoubleQuoteStringRule(PARTITION_STRING);
					}
				},
				new PartitionRuleDirector() {
					@Override
					public void describe(RulesBuilder builder) {
						builder.addSingleLineRule("#{", "}", new RGB(127, 159, 191));
					}
				}
		);
	}
	
	protected void partition(String contentType, RGB color, PartitionRuleDirector director, PartitionRuleDirector contentDirector) {
		LanguagePartition partition = new LanguagePartition(contentType, color, director);
		partition.setContentRuleDirector(contentDirector);
		this.partitions.add(partition);
	}

	@Override
	public RGB getDefaultPartitionTokenColor() {
		return new RGB(125, 125, 125);
	}
	
	@Override
	protected void describeDefaultPartitionTokenRules(RulesBuilder builder) {
		builder.addWhitespaceRule();
		builder.addRule(new BracketRule(TokenUtils.createToken(SWT.BOLD)));
		builder.addWordRule(new KindWordDetector(), color(127, 0, 85));
		builder.addKeyWordsRules(color(16, 96, 3).and(bold()), "self");
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