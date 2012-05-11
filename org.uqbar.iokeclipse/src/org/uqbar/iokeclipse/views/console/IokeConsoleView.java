package org.uqbar.iokeclipse.views.console;

import ioke.lang.exceptions.ControlFlow;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.uqbar.iokeclipse.IokeActivator;
import org.uqbar.iokeclipse.model.IokeConsole;
import org.uqbar.iokeclipse.model.IokeConsoleListener;

/**
 * 
 * @author jfernandes
 */
public class IokeConsoleView extends ViewPart implements IokeConsoleListener {
	public static final String ID = "org.uqbar.iokeclipse.views.IokeConsoleView";
	private StyledText inputTextArea;
	private StyledText consoleTextArea;

	public void createPartControl(Composite parent) {
		parent.setLayout(new org.eclipse.swt.layout.GridLayout(1, true));
		this.createInputTextArea(parent);
		this.createTextArea(parent);
		this.contributeToActionBars();
	}

	protected void createInputTextArea(Composite parent) {
		this.inputTextArea = new StyledText(parent, SWT.BORDER | SWT.SINGLE);
		this.inputTextArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		this.inputTextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP) {
					inputTextArea.setText(getConsole().moveHistoryUp());
				}
				else if (e.keyCode == SWT.ARROW_DOWN) {
					inputTextArea.setText(getConsole().moveHistoryDown());
				}
				e.doit = true;
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		this.inputTextArea.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN) {
					evaluateInput();
				}
			}
		});
	}
	
	protected void evaluateInput() {
		try {
			getConsole().evaluate(inputTextArea.getText());
			this.inputTextArea.setText("");
			this.inputTextArea.setSelection(0);
		} catch (ControlFlow e) {
			this.consoleTextArea.setText(this.consoleTextArea.getText() + "\n" + e.getMessage());
		}
	}

	protected void createTextArea(Composite parent) {
		this.consoleTextArea = new StyledText(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		this.consoleTextArea.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));
		this.consoleTextArea.setEditable(false);

		this.getConsole().addListener(this);
	}

	protected IokeConsole getConsole() {
		return IokeActivator.getDefault().getConsole();
	}

	@Override
	public void setFocus() {
		this.inputTextArea.setFocus();
	}

	@Override
	public void consoleChanged(StringBuffer buffer) {
		// this is totally non-performant. We should connecto the stream
		// directly to the StyledTextContent
		// but just for now, as a prototype
		this.consoleTextArea.setText(buffer.toString());
	}

	@Override
	public void dispose() {
		this.getConsole().removeListener(this);
		super.dispose();
	}

	// *****************************************
	// ** menus and actions
	// *****************************************

	protected void contributeToActionBars() {
		this.fillLocalToolBar(getViewSite().getActionBars().getToolBarManager());
	}

	protected void fillLocalToolBar(IToolBarManager manager) {
		manager.add(this.createClearConsoleAction());
	}

	protected IAction createClearConsoleAction() {
		Action action = new Action() {
			public void run() {
				getConsole().clear();
			}
		};
		action.setText("Clear");
		action.setToolTipText("Clear Console Text");
		action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		return action;
	}

}