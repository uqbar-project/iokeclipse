package org.uqbar.eclipse.blide.editor.text.document;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

/**
 * 
 * @author jfernandes
 */
public abstract class AbstractFileDocumentProvider extends FileDocumentProvider {

	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner = this.createPartitioner();
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}

	protected IDocumentPartitioner createPartitioner() {
		return new FastPartitioner(this.createTokenScanner(), this.getContentTypes());
	}
	
	protected abstract IPartitionTokenScanner createTokenScanner();

	protected abstract String[] getContentTypes();


}