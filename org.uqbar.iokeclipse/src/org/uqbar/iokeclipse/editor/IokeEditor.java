package org.uqbar.iokeclipse.editor;

import ioke.lang.exceptions.ControlFlow;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.uqbar.eclipse.blide.editor.AbstractBlideTextEditor;
import org.uqbar.eclipse.blide.editor.configuration.LanguageBasedSourceViewerConfiguration;
import org.uqbar.eclipse.blide.editor.text.document.LanguageBasedDocumentProvider;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;
import org.uqbar.iokeclipse.IokeActivator;

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
	
	// duplicated code with EvaluateTextFileAction
	
	public void evaluateContent() {
		IFile file = (IFile) this.getEditorInput().getAdapter(IFile.class);
		if (file != null) {
			this.evaluate(file.getRawLocation().toFile());
		}
	}
	
	protected void evaluate(File file) {
		try {
			IokeActivator.getDefault().getConsole().evaluate(file);
		} catch (ControlFlow e) {
			throw new RuntimeException("Error while evaluating code: " + e.getMessage(), e);
		}
	}

}