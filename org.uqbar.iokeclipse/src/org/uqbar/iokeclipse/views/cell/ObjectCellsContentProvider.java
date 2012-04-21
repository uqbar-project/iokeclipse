package org.uqbar.iokeclipse.views.cell;

import ioke.lang.IokeObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.uqbar.iokeclipse.IokeUtils;
import org.uqbar.iokeclipse.model.IokeCell;

/**
 * 
 * @author jfernandes
 */
public class ObjectCellsContentProvider implements IStructuredContentProvider {

	private static final Object[] EMPTY_ARRAY = new Object[0];

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		if (parent == null) {
			return EMPTY_ARRAY;
		}
		Collection<IokeCell> cells = new ArrayList<IokeCell>();
		this.addCells(parent, cells);
		return cells.toArray();
	}

	protected void addCells(Object parent, Collection<IokeCell> cells) {
		IokeObject object = (IokeObject) parent;
		// own cells
		SortedSet<IokeCell> ownCells = new TreeSet<IokeCell>();
		for (IokeCell iokeCell : IokeUtils.getCells(object)) {
			if (!iokeCell.isKind()) {
				ownCells.add(iokeCell);
			}
		}
		cells.addAll(ownCells);
		// inherited
		for (IokeObject mimic : object.getMimics()) {
			this.addCells(mimic, cells);
		}
	}
}