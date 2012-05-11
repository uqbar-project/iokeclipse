package org.uqbar.iokeclipse.views.doc;

import ioke.lang.IokeObject;
import ioke.lang.Method;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.uqbar.iokeclipse.IokeUtils;

/**
 * 
 * @author jfernandes
 */
public class DocumentationView extends ViewPart implements ISelectionListener {
	private Color documentationColor;
	private Color codeColor;
	public static final String ID = "org.uqbar.iokeclipse.views.doc.DocumentationView";
	private StyledText textArea;

	@Override
	public void init(IViewSite site) throws PartInitException {
		this.documentationColor = new Color(site.getShell().getDisplay(), 125, 125, 125);
		this.codeColor = new Color(site.getShell().getDisplay(), 125, 0, 0);
		super.init(site);
	}
	
	@Override
	public void dispose() {
		this.documentationColor.dispose();
		this.codeColor.dispose();
		super.dispose();
	}
	
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
		if (object != null && IokeUtils.isMethod(object)) {
			setText(object);
		}
		else {
			this.textArea.setText("");
		}
	}

	protected void setText(IokeObject object) {
		String code = ((Method) object.data(object)).getCodeString();
		String documentation = object.getDocumentation();
		
		StyledTextString textString = new StyledTextString();
		textString.addToken(new StyledToken(documentation, new TextStyle(null, documentationColor, null)));
		textString.addToken(new StyledToken(code, new TextStyle(null, codeColor, null)));
		textString.setTo(this.textArea);
	}

}