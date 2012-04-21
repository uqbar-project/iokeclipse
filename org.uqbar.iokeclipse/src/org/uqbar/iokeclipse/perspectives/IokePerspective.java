package org.uqbar.iokeclipse.perspectives;

import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.uqbar.iokeclipse.views.MimicsView;
import org.uqbar.iokeclipse.views.browser.KindView;
import org.uqbar.iokeclipse.views.cell.ObjectCellsView;
import org.uqbar.iokeclipse.views.console.IokeConsoleView;
import org.uqbar.iokeclipse.views.doc.DocumentationView;

/**
 *  This class is meant to serve as an example for how various contributions 
 *  are made to a perspective. Note that some of the extension point id's are
 *  referred to as API constants while others are hardcoded and may be subject 
 *  to change.
 *  
 *  @author jfernandes 
 */
public class IokePerspective implements IPerspectiveFactory {

	private IPageLayout factory;

	public IokePerspective() {
		super();
	}

	public void createInitialLayout(IPageLayout factory) {
		this.factory = factory;
		this.addViews();
		this.addActionSets();
		this.addNewWizardShortcuts();
		this.addPerspectiveShortcuts();
		this.addViewShortcuts();
	}

	private void addViews() {
		this.createBottom();
		this.createTopLeft();
		this.createTop();
		this.createRight();
		this.addFastView();
	}

	private void addFastView() {
		this.factory.addFastView("org.eclipse.team.ccvs.ui.RepositoriesView",0.50f);
		this.factory.addFastView("org.eclipse.team.sync.views.SynchronizeView", 0.50f);
	}
	
	private void createTopLeft() {
		IFolderLayout topLeft =
			factory.createFolder(
				"topLeft", 
				IPageLayout.LEFT,
				0.25f,
				factory.getEditorArea());
		topLeft.addView(KindView.ID);
	}
	
	private void createTop() {
		IFolderLayout topLeft =
				factory.createFolder(
					"topMiddle",
					IPageLayout.TOP,
					0.3f,
					factory.getEditorArea());
		topLeft.addView(ObjectCellsView.ID);
		
		// documentation
		IFolderLayout doc =
				factory.createFolder(
					"topRight",
					IPageLayout.RIGHT,
					0.25f,
					"topMiddle");
		doc.addView(DocumentationView.ID);
	}
	
	private void createRight() {
		IFolderLayout topLeft =
				factory.createFolder(
					"right", 
					IPageLayout.RIGHT,
					0.75f,
					factory.getEditorArea());
		topLeft.addView(MimicsView.ID);
	}

	private void createBottom() {
		IFolderLayout bottom =
			factory.createFolder(
				"bottomRight", //NON-NLS-1
				IPageLayout.BOTTOM,
				0.75f,
				factory.getEditorArea());
		
		bottom.addView(IokeConsoleView.ID);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
	}

	private void addActionSets() {
		factory.addActionSet("org.eclipse.debug.ui.launchActionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.debug.ui.debugActionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.debug.ui.profileActionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.jdt.debug.ui.JDTDebugActionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.jdt.junit.JUnitActionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.team.ui.actionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.team.cvs.ui.CVSActionSet"); //NON-NLS-1
		factory.addActionSet("org.eclipse.ant.ui.actionSet.presentation"); //NON-NLS-1
		factory.addActionSet(JavaUI.ID_ACTION_SET);
		factory.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
		factory.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET); //NON-NLS-1
	}

	private void addPerspectiveShortcuts() {
		factory.addPerspectiveShortcut("org.eclipse.team.ui.TeamSynchronizingPerspective"); //NON-NLS-1
		factory.addPerspectiveShortcut("org.eclipse.team.cvs.ui.cvsPerspective"); //NON-NLS-1
		factory.addPerspectiveShortcut("org.eclipse.ui.resourcePerspective"); //NON-NLS-1
	}

	private void addNewWizardShortcuts() {
		factory.addNewWizardShortcut("org.eclipse.team.cvs.ui.newProjectCheckout");//NON-NLS-1
		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//NON-NLS-1
		factory.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//NON-NLS-1
	}

	private void addViewShortcuts() {
		factory.addShowViewShortcut("org.eclipse.ant.ui.views.AntView"); //NON-NLS-1
		factory.addShowViewShortcut("org.eclipse.team.ccvs.ui.AnnotateView"); //NON-NLS-1
		factory.addShowViewShortcut("org.eclipse.pde.ui.DependenciesView"); //NON-NLS-1
		factory.addShowViewShortcut("org.eclipse.jdt.junit.ResultView"); //NON-NLS-1
		factory.addShowViewShortcut("org.eclipse.team.ui.GenericHistoryView"); //NON-NLS-1
		factory.addShowViewShortcut(IConsoleConstants.ID_CONSOLE_VIEW);
		factory.addShowViewShortcut(JavaUI.ID_PACKAGES);
		factory.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
	}

}
