package org.uqbar.iokeclipse.model;

import ioke.lang.Runtime;
import ioke.lang.exceptions.ControlFlow;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.iokeclipse.IokeActivator;

/**
 * 
 * @author jfernandes
 */
public class IokeConsole extends OutputStream {
	private int maxSize = 80000;
	private int clearOnMaxFactor = 2;
	private StringBuffer buffer = new StringBuffer();
	private List<IokeConsoleListener> listeners = new ArrayList<IokeConsoleListener>();
	private List<String> commandsHistory = new LinkedList<String>();

	@Override
	public void write(int b) throws IOException {
		this.checkMaxToCut();
		buffer.append(Character.toString((char) b));
		this.fireChanged();
	}
	
	public Object evaluate(File file) throws ControlFlow {
		try {
			Object result = this.getRuntime().evaluateFile(file, this.getRuntime().message, this.getRuntime().ground);
			this.write((result + "\n").getBytes());
			return result;
		} catch (IOException e) {
			throw new RuntimeException("Error while writing the result to console", e);
		}
	}
	
	public Object evaluate(String text) throws ControlFlow {
		try {
			Object result = this.getRuntime().evaluateString(text + "\n");
			this.commandsHistory.add(text);
			this.write((result + "\n").getBytes());
			return result;
		} catch (IOException e) {
			throw new RuntimeException("Error while writing the result to console", e);
		}
	}
	
	public List<String> getCommandsHistory() {
		return this.commandsHistory;
	}
	
	protected Runtime getRuntime() {
		return IokeActivator.getDefault().getRuntime();
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
	
	// listeners
	
	protected void fireChanged() {
		for (IokeConsoleListener listener : this.listeners) {
			listener.consoleChanged(this.buffer);
		}
	}

	public void addListener(IokeConsoleListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(IokeConsoleListener listener) {
		this.listeners.remove(listener);
	}

	private int historyIndex = 0;
	
	public String moveHistoryUp() {
		return moveHistory(1);
	}

	public String moveHistoryDown() {
		return moveHistory(-1);
	}
	
	private String moveHistory(int delta) {
		String historyItem = this.commandsHistory.get(this.commandsHistory.size() - 1 - this.historyIndex);
		this.historyIndex += delta;
		this.historyIndex = Math.max(this.historyIndex, 0);
		this.historyIndex = Math.min(this.historyIndex, this.commandsHistory.size() - 1);
		return historyItem;
	}

}
