package org.uqbar.iokeclipse.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.jface.dialogs.MessageDialog;
import org.uqbar.iokeclipse.editor.IokeEditor;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 * 
 * @author jfernandes
 */
public class EvaluateEditorContentAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		IWorkbenchPage activePage = this.window.getActivePage();
		IokeEditor activeEditor = (IokeEditor) activePage.getActiveEditor();
		activeEditor.evaluateContent();
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}
	
}