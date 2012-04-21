package org.uqbar.iokeclipse.views.cell;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.uqbar.iokeclipse.IokeActivator;
import org.uqbar.iokeclipse.model.IokeCell;

/**
 * @author jfernandes
 */
public class ObjectCellLabelProvider extends LabelProvider implements ITableLabelProvider {
    
    public String getColumnText(Object obj, int index) {
	if (obj instanceof IokeCell) {
	    return ((IokeCell) obj).getShortDescription();
	}
        return getText(obj);
    }

    public Image getColumnImage(Object obj, int index) {
	if (obj instanceof IokeCell) {
	    return IokeActivator.cellIcon();
	}
        return getImage(obj);
    }

    public Image getImage(Object obj) {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
}