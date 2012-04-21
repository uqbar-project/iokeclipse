package org.uqbar.iokeclipse;

import ioke.lang.IokeObject;
import ioke.lang.IokeSystem;
import ioke.lang.Runtime;
import ioke.lang.exceptions.ControlFlow;
import ioke.lang.exceptions.ControlFlow.Exit;

import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.uqbar.iokeclipse.model.IokeConsole;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author jfernandes
 */
public class IokeActivator extends AbstractUIPlugin {
	private static final String IMG_KIND = "kind.png";
	private static final String IMG_CELL = "cell.png";
	public static final String PLUGIN_ID = "org.uqbar.iokeclipse"; //$NON-NLS-1$
	private static IokeActivator plugin;
	private ioke.lang.Runtime runtime;
	private IokeConsole console;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		try {
			this.console = new IokeConsole();
			this.runtime = this.createRuntime();
			this.runtime.init();
			this.setupStreams();
		} catch (Exception e) {
			throw new PartInitException("Error while initializing a new ioke Runtime!", e);
		} catch (ControlFlow e) {
			throw new PartInitException("Error while initializing a new ioke Runtime!", e);
		}
		plugin = this;
	}

	protected Runtime createRuntime() throws Exception {
		return new ioke.lang.Runtime(new PrintWriter(this.console, true),
				new InputStreamReader(System.in, "UTF-8"), new PrintWriter(
						this.console, true));
	}

	protected void setupStreams() {
		((IokeSystem) IokeObject.data(runtime.system)).setCurrentProgram("<stdin>");
	}

	public void stop(BundleContext context) throws Exception {
		try {
			this.runtime.tearDown();
		} catch (Exit e) {
			throw new RuntimeException(
					"Error while tearing-donw ioke Runtime!", e);
		}
		plugin = null;
		super.stop(context);
	}

	public static IokeActivator getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	// *****************************************
	// ** ioke behavior
	// *****************************************

	public ioke.lang.Runtime getRuntime() {
		return this.runtime;
	}

	public IokeConsole getConsole() {
		return console;
	}

	public static Image kindIcon() {
		return getImageDescriptor("icons/" + IMG_KIND).createImage();
	}

	public static Image cellIcon() {
		return getImageDescriptor("icons/" + IMG_CELL).createImage();
	}

}
