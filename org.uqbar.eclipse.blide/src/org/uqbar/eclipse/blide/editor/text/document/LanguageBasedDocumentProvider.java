package org.uqbar.eclipse.blide.editor.text.document;

import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.uqbar.eclipse.blide.editor.text.document.AbstractFileDocumentProvider;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageBasedPartitionerScanner;
import org.uqbar.eclipse.blide.editor.text.partition.LanguageDefinition;

/**
 * 
 * @author jfernandes
 */
public class LanguageBasedDocumentProvider extends AbstractFileDocumentProvider implements IDocumentProvider {
	private final LanguageDefinition language;

	public LanguageBasedDocumentProvider(LanguageDefinition language) {
		this.language = language;
	}

	@Override
	protected IPartitionTokenScanner createTokenScanner() {
		return new LanguageBasedPartitionerScanner(this.getLanguage());
	}

	@Override
	protected String[] getContentTypes() {
		return this.getLanguage().getContentTypes();
	}
	
	public LanguageDefinition getLanguage() {
		return this.language;
	}

}