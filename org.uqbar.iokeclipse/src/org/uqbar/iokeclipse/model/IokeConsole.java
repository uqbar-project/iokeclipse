package org.uqbar.iokeclipse.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jfernandes
 */
public class IokeConsole extends OutputStream {
    private int maxSize = 80000;
    private int clearOnMaxFactor = 2;
    private StringBuffer buffer = new StringBuffer();
    private List<IokeConsoleListener> listeners = new ArrayList<IokeConsoleListener>(); 

    @Override
    public void write(int b) throws IOException {
	this.checkMaxToCut();
	buffer.append(Character.toString((char) b));
	this.fireChanged();
    }

    protected void fireChanged() {
	for (IokeConsoleListener listener : this.listeners) {
	    listener.consoleChanged(this.buffer);
	}
    }
    
    private void checkMaxToCut() {
	if (this.buffer.length() >= maxSize) {
	    this.buffer.delete(0, this.maxSize / this.clearOnMaxFactor);
	}
    }

    public void clear() {
	this.buffer.setLength(0);
	this.fireChanged();
    }
    
    public void addListener(IokeConsoleListener listener) {
	this.listeners.add(listener);
    }
    
    public void removeListener(IokeConsoleListener listener) {
	this.listeners.remove(listener);
    }

}
