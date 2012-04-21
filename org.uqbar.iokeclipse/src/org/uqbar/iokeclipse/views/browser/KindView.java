package org.uqbar.iokeclipse.views.browser;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.uqbar.iokeclipse.IokeActivator;
import org.uqbar.iokeclipse.views.utils.NameSorter;

/**
 * 
 * @author jfernandes
 */
public class KindView extends ViewPart {
	private static final String IMG_REFRESH = "refresh.gif";
	public static final String ID = "org.uqbar.iokeclipse.views.ObjectBrowser";
	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action refreshAction;
	private Action doubleClickAction;

	public void createPartControl(Composite parent) {
		this.createTree(parent);
		this.drillDownAdapter = new DrillDownAdapter(viewer);
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.uqbar.iokeclipse.viewer");
		this.makeActions();
		this.hookContextMenu();
		this.hookDoubleClickAction();
		this.contributeToActionBars();
	}

	protected void createTree(Composite parent) {
		this.viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		this.viewer.setContentProvider(new KindContentProvider());
		this.viewer.setLabelProvider(new KindLabelProvider());
		this.viewer.setSorter(new NameSorter());
		this.viewer.setInput(IokeActivator.getDefault().getRuntime().iokeGround);
		this.getSite().setSelectionProvider(this.viewer);
	}

	protected void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				KindView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	protected void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		this.fillLocalPullDown(bars.getMenuManager());
		this.fillLocalToolBar(bars.getToolBarManager());
	}

	protected void fillLocalPullDown(IMenuManager manager) {
		manager.add(refreshAction);
	}

	protected void fillContextMenu(IMenuManager manager) {
		manager.add(this.refreshAction);
		manager.add(new Separator());
		this.drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	protected void fillLocalToolBar(IToolBarManager manager) {
		manager.add(this.refreshAction);
		manager.add(new Separator());
		this.drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		this.refreshAction = new Action() {
			public void run() {
				viewer.refresh();
			}
		};
		this.refreshAction.setText("Refresh");
		this.refreshAction.setToolTipText("Refresh Tree");
		this.refreshAction.setImageDescriptor(IokeActivator.getImageDescriptor("icons/" + IMG_REFRESH));

		this.doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				showMessage("Double-click detected on " + obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"Object Browser", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}