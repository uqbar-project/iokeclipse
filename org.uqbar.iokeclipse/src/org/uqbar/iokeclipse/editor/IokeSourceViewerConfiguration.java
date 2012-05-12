package org.uqbar.iokeclipse.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.RGB;
import org.uqbar.eclipse.blide.editor.configuration.AbstractSourceViewerConfiguration;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageBasedPartitionerScanner;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;
import org.uqbar.eclipse.blide.editor.text.partition.LanguagePartition;
import org.uqbar.eclipse.blide.editor.utils.TokenUtils;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public class IokeSourceViewerConfiguration extends AbstractSourceViewerConfiguration {
	private LanguageDefinition language;

	public IokeSourceViewerConfiguration(LanguageDefinition language, ColorManager colorManager) {
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
	protected RuleBasedScanner instantiateTokenScanner() {
		return new LanguageBasedPartitionerScanner(this.getLanguage());
	}
	
	@Override
	protected Token createDefaultToken() {
		return TokenUtils.createToken(this.getColorManager(), new RGB(125, 125, 125));
	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(this.getTokenScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		for (LanguagePartition partition : this.getLanguage().getPartitions()) {
			this.configureContentTypeColor(reconciler, partition.getKey(), partition.getColor());
		}
		return reconciler;
	}

	
}
