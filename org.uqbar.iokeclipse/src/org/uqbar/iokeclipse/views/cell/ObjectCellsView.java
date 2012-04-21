package org.uqbar.iokeclipse.views.cell;

import ioke.lang.AssociatedCode;
import ioke.lang.IokeData;
import ioke.lang.IokeObject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.uqbar.iokeclipse.IokeUtils;
import org.uqbar.iokeclipse.model.IokeCell;
import org.uqbar.iokeclipse.views.browser.KindView;

/**
 * @author jfernandes
 */
// TODO: filter Kind's, on
public class ObjectCellsView extends ViewPart implements ISelectionListener {
	public static final String ID = "org.uqbar.iokeclipse.views.ObjectCellsView";
	private TableViewer viewer;

	public void createPartControl(Composite parent) {
		this.createTable(parent);
		this.hookContextMenu();

		this.getSite().getPage().addSelectionListener(this);
		this.getSite().setSelectionProvider(this.viewer);
	}

	protected void createTable(Composite parent) {
		this.viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		this.viewer.setContentProvider(new ObjectCellsContentProvider());
		this.viewer.setLabelProvider(new ObjectCellLabelProvider());
//		this.viewer.setComparator(new ViewerComparator());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part != this) {
			this.viewer.setInput(IokeUtils.getSelectedIokeObjectOrNothing(selection));
		}
	}

	// *****************************************
	// ** context menu (this should be refactored to be composed by extension
	// points)
	// *****************************************

	protected void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ObjectCellsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(this.viewer.getControl());
		this.viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, this.viewer);
	}

	protected void fillContextMenu(IMenuManager manager) {
		manager.add(this.deleteCellAction());
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	protected IAction deleteCellAction() {
		Action action = new Action() {
			public void run() {
				deleteSelectedCells();
			}
		};
		action.setText("Remove");
		action.setToolTipText("Remove Cell");
		action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		return action;
	}

	protected void deleteSelectedCells() {
		ISelection selection = viewer.getSelection();
		if (!(selection instanceof IStructuredSelection)) {
			return;
		}
		IStructuredSelection cellSeleciton = (IStructuredSelection) selection;
		for (Object cell : cellSeleciton.toList()) {
			((IokeCell) cell).remove();
			this.viewer.refresh();
		}
	}

}