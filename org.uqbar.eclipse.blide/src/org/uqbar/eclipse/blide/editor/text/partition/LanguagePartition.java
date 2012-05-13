package org.uqbar.eclipse.blide.editor.text.partition;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.text.presentation.NonRuleBasedDamagerRepairer;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public class LanguagePartition {
	private final String contentType;
	private final RGB color;
	private final PartitionRuleDirector ruleDirector;
	/** an object that describes the partitioning of the inner elements of this partition */
	private PartitionRuleDirector contentRuleDirector;

	public LanguagePartition(String key, RGB color, PartitionRuleDirector ruleDirector) {
		this.contentType = key;
		this.color = color;
		this.ruleDirector = ruleDirector;
	}
	
	public String getContentType() {
		return this.contentType;
	}
	
	public RGB getColor() {
		return this.color;
	}
	
	public PartitionRuleDirector getRuleDirector() {
		return this.ruleDirector;
	}
	
	public void setContentRuleDirector(PartitionRuleDirector contentRuleDirector) {
		this.contentRuleDirector = contentRuleDirector;
	}

	public void addDamagerAndRepairer(PresentationReconciler reconciler, ColorManager colorManager) {
		TextAttribute defaultTextColor = this.getTextAttribute(colorManager);
		Object damagerAndRepairer = this.contentRuleDirector == null ? 
				new NonRuleBasedDamagerRepairer(defaultTextColor)
			:	new DefaultDamagerRepairer(this.createInnerContentTokenScanner(colorManager), defaultTextColor);
				
		reconciler.setDamager((IPresentationDamager) damagerAndRepairer, this.getContentType());
		reconciler.setRepairer((IPresentationRepairer) damagerAndRepairer, this.getContentType());
	}

	protected TextAttribute getTextAttribute(ColorManager colorManager) {
		return new TextAttribute(colorManager.getColor(this.getColor()));
	}

	protected ITokenScanner createInnerContentTokenScanner(ColorManager colorManager) {
		return new RulesBuilderScanner(this.contentRuleDirector, colorManager);
	}

}