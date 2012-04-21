package org.uqbar.iokeclipse.views.doc;

import ioke.lang.IokeObject;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.uqbar.iokeclipse.IokeUtils;

/**
 * 
 * @author jfernandes
 */
public class DocumentationView extends ViewPart implements ISelectionListener {
	public static final String ID = "org.uqbar.iokeclipse.views.doc.DocumentationView";
	private StyledText textArea;

	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		this.createTextArea(parent);
		this.getSite().getPage().addSelectionListener(this);
	}

	protected void createTextArea(Composite parent) {
		this.textArea = new StyledText(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		this.textArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		this.textArea.setEditable(false);
	}

	@Override
	public void setFocus() {
		this.textArea.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IokeObject object = IokeUtils.getSelectedIokeObjectOrNothing(selection);
		String text = object != null && IokeUtils.isMethod(object) ? object.getDocumentation() : "";
		this.textArea.setText(text != null ? text : "");
	}

}