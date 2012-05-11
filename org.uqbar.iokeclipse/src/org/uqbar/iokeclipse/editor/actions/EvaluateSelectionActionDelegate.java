package org.uqbar.iokeclipse.editor.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;

/**
 * 
 * @author jfernandes
 */
public class EvaluateSelectionActionDelegate extends AbstractEvaluateIokeActionDelegate {
	
	public void run(IAction action) {
		ISelection selection = this.getTargetEditor().getSelectionProvider().getSelection();
		if (selection instanceof TextSelection) {
			TextSelection selectedText = (TextSelection) selection;
			String text = selectedText.getText();
			this.evaluate(text);
		}
	}

}
