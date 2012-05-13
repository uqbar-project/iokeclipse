package org.uqbar.eclipse.blide.editor.configuration;

import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.uqbar.eclipse.blide.editor.text.hover.MultipleLinesTextHover;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public abstract class AbstractSourceViewerConfiguration extends SourceViewerConfiguration {
	private RuleBasedScanner scanner;
	private ColorManager colorManager;
	
	public AbstractSourceViewerConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	
	protected synchronized ITokenScanner getDefaultPartitionTokenScanner() {
		if (this.scanner == null) {
			this.scanner = this.createDefaultPartitionTokenScanner();
		}
		return this.scanner;
	}
	
	protected RuleBasedScanner createDefaultPartitionTokenScanner() {
		RuleBasedScanner scanner = this.instantiateDefaultPartitionTokenScanner();
		scanner.setDefaultReturnToken(this.createDefaultToken());
		return scanner;
	}
	
	protected abstract RuleBasedScanner instantiateDefaultPartitionTokenScanner();
	
	protected abstract Token createDefaultToken();
	
	public ColorManager getColorManager() {
		return this.colorManager;
	}
	
	public IAnnotationHover getAnnotationHover(ISourceViewer sourceViewer) {
		return new DefaultAnnotationHover(true);
    }
    
    public ITextHover getTextHover(ISourceViewer sourceViewer, String contentType) {
    	return new MultipleLinesTextHover(sourceViewer);
    }

}