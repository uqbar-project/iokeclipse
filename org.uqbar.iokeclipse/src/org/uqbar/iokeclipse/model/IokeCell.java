package org.uqbar.iokeclipse.model;

import ioke.lang.AssociatedCode;
import ioke.lang.IokeData;
import ioke.lang.IokeObject;
import ioke.lang.exceptions.ControlFlow;

import java.util.List;

import org.uqbar.iokeclipse.IokeUtils;

/**
 * 
 * @author jfernandes
 */
public class IokeCell implements Comparable<IokeCell> {
	private final IokeObject owner;
	private final String name;
	// this should be removed and calculated with "object.getCell(name)" or
	// something like that.
	private Object content;

	public IokeCell(IokeObject iokeObject, String name, Object content) {
		this.owner = iokeObject;
		this.name = name;
		this.content = content;
	}

	public IokeObject getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public Object getContent() {
		return this.content;
	}

	public void addYourSelfIfMethod(List<IokeCell> cells) {
		// hack! volar esto con scala
		if (this.content != null && this.content instanceof IokeObject
				&& IokeUtils.isMethod(getContentAsIokeObject())) {
			cells.add(this);
		}
	}

	public void addYourSelfIfNotMethod(List<IokeCell> cells) {
		// hack! volar esto con scala
		if (this.content != null && this.content instanceof IokeObject
				&& !(IokeUtils.isMethod(this.getContentAsIokeObject()))) {
			cells.add(this);
		}
	}

	public IokeObject getContentAsIokeObject() {
		return (IokeObject) this.content;
	}

	@Override
	public int compareTo(IokeCell o) {
		return this.getName().compareTo(o.getName());
	}

	public String getShortDescription() {
		return this.getName() + this.getArgumentDescriptor() + " (" + this.getOwner().getKind() + ")";
	}

	private String getArgumentDescriptor() {
		if (IokeUtils.isMethod(this.content)) {
			try {
				IokeData theData = IokeObject.data(this.content);
				return theData instanceof AssociatedCode ? ((AssociatedCode) theData)
						.getArgumentsCode() : "";
			} catch (Exception e) {
				// ugly but ioke simply fails with NPE sometimes because
				// "arguments" is null but I don't have any way to test before
				// asking :( At least I cannot see one right now
				return "";
			}
		} else {
			return "";
		}
	}

	public boolean isKind() {
		// IOKE convention ! check if there's another way to evaluate this
		// (maybe looking up the 'kind' cell ?)
		// there's a IokeObject.hasKind()
		return Character.isUpperCase(this.name.charAt(0));
	}

	public void remove() {
		try {
			this.getOwner().removeCell((IokeObject) this.content, this.owner, this.getName());
		} catch (ControlFlow e) {
			throw new RuntimeException("Error while trying to remove the cell", e);
		}
	}

}
