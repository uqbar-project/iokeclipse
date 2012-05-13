package org.uqbar.eclipse.blide.editor.configuration;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;
import org.uqbar.eclipse.blide.editor.text.partition.LanguagePartition;
import org.uqbar.eclipse.blide.editor.utils.TokenUtils;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public class LanguageBasedSourceViewerConfiguration extends AbstractSourceViewerConfiguration {
	private LanguageDefinition language;

	public LanguageBasedSourceViewerConfiguration(LanguageDefinition language, ColorManager colorManager) {
		super(colorManager);
		this.language = language;
	}
	
	public LanguageDefinition getLanguage() {
		return this.language;
	}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return this.getLanguage().getContentTypes();
	}
	
	@Override
	protected RuleBasedScanner instantiateDefaultPartitionTokenScanner() {
		return this.getLanguage().createDefaultPartionTokenScanner(this.getColorManager());
	}
	
	@Override
	protected Token createDefaultToken() {
		return TokenUtils.createToken(this.getColorManager(), this.getLanguage().getDefaultPartitionTokenColor());
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		// default partition
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(this.getDefaultPartitionTokenScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		// custom partitions
		for (LanguagePartition partition : this.getLanguage().getPartitions()) {
			partition.addDamagerAndRepairer(reconciler, this.getColorManager());
		}
		return reconciler;
	}
	
}
