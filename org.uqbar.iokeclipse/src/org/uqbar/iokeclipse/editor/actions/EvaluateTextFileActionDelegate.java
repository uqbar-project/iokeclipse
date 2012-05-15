package org.uqbar.iokeclipse.editor.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.editors.text.TextEditor;

/**
 * @author jfernandes
 */
public class EvaluateTextFileActionDelegate extends AbstractEvaluateIokeActionDelegate {
	
	@Override
	public void run(IAction action) {
		org.eclipse.ui.editors.text.TextEditor editor = (TextEditor) this.getTargetEditor();
		IFile file = (IFile) editor.getEditorInput().getAdapter(IFile.class);
		if (file != null) {
			this.evaluate(file.getRawLocation().toFile());
		}
	}

}
