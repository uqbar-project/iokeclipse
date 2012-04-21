package org.uqbar.iokeclipse.views;

import ioke.lang.IokeObject;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.uqbar.iokeclipse.IokeUtils;
import org.uqbar.iokeclipse.model.IokeCell;

/**
 * 
 * @author jfernandes
 */
public class MimicsView extends ViewPart implements ISelectionListener {
    public static final String ID = "org.uqbar.iokeclipse.views.MimicsView";
    private TreeViewer viewer;
    
    class ViewLabelProvider extends LabelProvider {
	public String getText(Object obj) {
	    return ((IokeObject) obj).getKind();
	}

	public Image getImage(Object obj) {
	    String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
	    return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	}
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    public void createPartControl(Composite parent) {
	this.createTree(parent);
	this.getSite().getPage().addSelectionListener(this);
    }

    protected void createTree(Composite parent) {
	viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	viewer.setContentProvider(new MimicsContentProvider());
	viewer.setLabelProvider(new ViewLabelProvider());
    }

    public void setFocus() {
	this.viewer.getControl().setFocus();
    }
    
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (part != this) {
	    this.viewer.setInput(IokeUtils.getSelectedIokeObjectOrNothing(selection));
	}
    }
    
}