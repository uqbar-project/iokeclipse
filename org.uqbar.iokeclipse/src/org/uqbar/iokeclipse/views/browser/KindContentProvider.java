package org.uqbar.iokeclipse.views.browser;

import java.util.ArrayList;
import java.util.List;

import ioke.lang.IokeObject;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.uqbar.iokeclipse.IokeUtils;
import org.uqbar.iokeclipse.model.IokeCell;

/**
 * 
 * @author jfernandes
 */
public class KindContentProvider implements
		IStructuredContentProvider, ITreeContentProvider {

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		IokeObject object = (IokeObject) (parent instanceof IokeObject ? parent : ((IokeCell) parent).getContent());
		
		List<IokeCell> cells = new ArrayList<IokeCell>();
		for (IokeCell iokeCell : IokeUtils.getCells(object)) {
			if (iokeCell.isKind()) {
				cells.add(iokeCell);
			}
		}
		return cells.toArray();
	}

	public Object getParent(Object child) {
		return ((IokeCell) child).getOwner();
	}

	public Object[] getChildren(Object parent) {
		return this.getElements(parent);
	}

	public boolean hasChildren(Object parent) {
		return this.getChildren(parent).length > 0;
	}

}