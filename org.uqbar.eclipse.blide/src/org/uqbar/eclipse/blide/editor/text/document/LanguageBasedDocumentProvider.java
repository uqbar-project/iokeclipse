package org.uqbar.eclipse.blide.editor.text.document;

import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.uqbar.eclipse.blide.editor.text.document.AbstractFileDocumentProvider;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageBasedPartitionerScanner;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;
import org.uqbar.eclipse.blide.ui.color.ColorManager;

/**
 * 
 * @author jfernandes
 */
public class LanguageBasedDocumentProvider extends AbstractFileDocumentProvider implements IDocumentProvider {
	private final LanguageDefinition language;
	private final ColorManager colorManager;

	public LanguageBasedDocumentProvider(LanguageDefinition language, ColorManager colorManager) {
		this.language = language;
		this.colorManager = colorManager;
	}

	@Override
	protected IPartitionTokenScanner createTokenScanner() {
		return new LanguageBasedPartitionerScanner(this.getLanguage(), this.colorManager);
	}

	@Override
	protected String[] getContentTypes() {
		return this.getLanguage().getContentTypes();
	}
	
	public LanguageDefinition getLanguage() {
		return this.language;
	}

}