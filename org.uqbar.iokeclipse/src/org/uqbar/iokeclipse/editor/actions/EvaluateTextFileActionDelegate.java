package org.uqbar.iokeclipse.editor.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
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
			this.evaluate(file);
		}
	}

	protected void evaluate(IFile file) {
		BufferedReader reader = null;
		try {
			InputStream stream = file.getContents();
			reader = new BufferedReader(new InputStreamReader(stream));
			String line;
			while ((line = reader.readLine()) != null)   {
				this.evaluate(line);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error while reading file", e);
		} catch (CoreException e) {
			throw new RuntimeException("Error while reading file", e);
		}
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
