package org.uqbar.iokeclipse.editor;

import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.uqbar.eclipse.blide.editor.AbstractBlideTextEditor;
import org.uqbar.eclipse.blide.editor.configuration.LanguageBasedSourceViewerConfiguration;
import org.uqbar.eclipse.blide.editor.text.document.LanguageBasedDocumentProvider;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;

/**
 * 
 * @author jfernandes
 */
public class IokeEditor extends AbstractBlideTextEditor {
	private LanguageDefinition language;
	
	public IokeEditor() {
		this.language = new IokeLanguageDefinition();
		this.init();
	}
	
	@Override
	protected SourceViewerConfiguration createSourceViewerConfiguration() {
		return new LanguageBasedSourceViewerConfiguration(this.getLanguage(), this.getColorManager());
	}

	@Override
	protected IDocumentProvider createDocumentProvider() {
		return new LanguageBasedDocumentProvider(this.getLanguage(), this.getColorManager());
	}
	
	public LanguageDefinition getLanguage() {
		return this.language;
	}

}