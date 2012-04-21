package org.uqbar.iokeclipse.views;

import ioke.lang.IokeObject;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author jfernandes
 *
 */
public class MimicsContentProvider implements IStructuredContentProvider, ITreeContentProvider {

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
    
    @Override
    public Object[] getElements(Object inputElement) {
	return ((IokeObject) inputElement).getMimics().toArray();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
	return this.getElements(parentElement);
    }

    @Override
    public Object getParent(Object element) {
	return null;
    }

    @Override
    public boolean hasChildren(Object element) {
	return !((IokeObject) element).getMimics().isEmpty();
    }

}
