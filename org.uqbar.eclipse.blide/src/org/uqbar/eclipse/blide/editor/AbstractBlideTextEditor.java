package org.uqbar.eclipse.blide.editor;

import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * Abstract base class for all text editors.
 * Here we will try to abstract reusable code to make it easy to create
 * a new editor.
 * 
 * NOTE: subclasses need to call the init() method after construction
 * 
 * @author jfernandes
 */
public abstract class AbstractBlideTextEditor extends TextEditor {
	private ColorManager colorManager;

	public AbstractBlideTextEditor() {
		this.colorManager = new ColorManager();
	}

	protected void init() {
		this.setSourceViewerConfiguration(this.createSourceViewerConfiguration());
		this.setDocumentProvider(this.createDocumentProvider());
	}
	
	protected abstract SourceViewerConfiguration createSourceViewerConfiguration();

	protected abstract IDocumentProvider createDocumentProvider();

	public ColorManager getColorManager() {
		return this.colorManager;
	}
	
	public void dispose() {
		this.colorManager.dispose();
		super.dispose();
	}

}