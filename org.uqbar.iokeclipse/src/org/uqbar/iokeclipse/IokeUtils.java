package org.uqbar.iokeclipse;

import ioke.lang.IokeObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.uqbar.iokeclipse.model.IokeCell;

/**
 * @author jfernandes
 */
public class IokeUtils {

	public static IokeCell[] getCells(IokeObject iokeObject) {
		Map<String, Object> cells = iokeObject.getCells();
		List<IokeCell> children = new ArrayList<IokeCell>(cells.size());
		for (Entry<String, Object> cell : cells.entrySet()) {
			String name = cell.getKey();
			if (name.length() > 0) {
				children.add(new IokeCell(iokeObject, name, cell.getValue()));
			}
		}
		return children.toArray(new IokeCell[children.size()]);
	}

	public static boolean isMethod(Object content) {
		return content instanceof IokeObject
				&& IokeObject.isMimic((IokeObject) content,
						((IokeObject) content).runtime.method);
	}

	// *****************************************
	// ** UI
	// *****************************************

	public static IokeObject getSelectedIokeObjectOrNothing(ISelection selection) {
		IokeObject object = null;
		if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection)
					.getFirstElement();
			if (element instanceof IokeCell) {
				object = (IokeObject) ((IokeCell) element).getContent();
			} else if (element instanceof IokeObject) {
				object = (IokeObject) element;
			}
		}
		return object;
	}

}
