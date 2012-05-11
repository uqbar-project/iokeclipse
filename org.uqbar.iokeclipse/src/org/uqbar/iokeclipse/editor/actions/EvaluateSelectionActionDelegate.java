package org.uqbar.iokeclipse.editor.actions;

import ioke.lang.exceptions.ControlFlow;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;
import org.uqbar.iokeclipse.IokeActivator;

/**
 * 
 * @author jfernandes
 */
public class EvaluateSelectionActionDelegate extends ActionDelegate implements IEditorActionDelegate {
	private ITextEditor targetEditor;

	public void run(IAction action) {
		ISelection selection = this.targetEditor.getSelectionProvider().getSelection();
		if (selection instanceof TextSelection) {
			TextSelection selectedText = (TextSelection) selection;
			try {
				IokeActivator.getDefault().getConsole().evaluate(selectedText.getText());
			} catch (ControlFlow e) {
				throw new RuntimeException("Error while evaluating code: " + e.getMessage(), e);
			}
		}
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		if (targetEditor instanceof ITextEditor) {
			this.targetEditor = (ITextEditor) targetEditor;
		}
	}

}
