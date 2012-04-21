package org.uqbar.iokeclipse.views.browser;

import ioke.lang.IokeObject;
import ioke.lang.exceptions.ControlFlow;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.uqbar.iokeclipse.IokeActivator;
import org.uqbar.iokeclipse.model.IokeCell;

/**
 * 
 * @author jfernandes
 */
public class KindLabelProvider extends LabelProvider {

    public String getText(Object obj) {
	if (obj instanceof IokeObject) {
	    try {
		return ((IokeObject) obj).getName();
	    } catch (ControlFlow e) {
		throw new RuntimeException("Error while getting the object's name for: " + obj);
	    }
	} else if (obj instanceof IokeCell) {
	    IokeCell cell = (IokeCell) obj;
	    return cell.getName();
	} else {
	    return obj.toString();
	}
    }

    public Image getImage(Object obj) {
	String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
	if (obj instanceof IokeObject) {
	    return IokeActivator.kindIcon();
	}
	if (obj instanceof IokeCell) {
	    return ((IokeCell) obj).isKind() ? IokeActivator.kindIcon() : IokeActivator.cellIcon();
	}
	return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
    }

}