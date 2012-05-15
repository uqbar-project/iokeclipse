package org.uqbar.iokeclipse.editor.actions;

import ioke.lang.exceptions.ControlFlow;

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.texteditor.ITextEditor;
import org.uqbar.iokeclipse.IokeActivator;

/**
 * 
 * @author jfernandes
 */
public abstract class AbstractEvaluateIokeActionDelegate extends ActionDelegate implements IEditorActionDelegate {
	private ITextEditor targetEditor;

	public AbstractEvaluateIokeActionDelegate() {
		super();
	}

	protected void evaluate(String text) {
		try {
			IokeActivator.getDefault().getConsole().evaluate(text);
		} catch (ControlFlow e) {
			throw new RuntimeException("Error while evaluating code: " + e.getMessage(), e);
		}
	}
	
	protected void evaluate(File file) {
		try {
			IokeActivator.getDefault().getConsole().evaluate(file);
		} catch (ControlFlow e) {
			throw new RuntimeException("Error while evaluating code: " + e.getMessage(), e);
		}
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		if (targetEditor instanceof ITextEditor) {
			this.targetEditor = (ITextEditor) targetEditor;
		}
	}
	
	protected ITextEditor getTargetEditor() {
		return this.targetEditor;
	}

}