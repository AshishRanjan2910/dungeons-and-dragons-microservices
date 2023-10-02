package com.gamecharacter.entity;

public class ParentChildPairDTO {
	private int parent;
	private int child;
	public ParentChildPairDTO (int parent, int child) {
		this.setParent(parent);
		this.setChild(child);
	}
	public int getParent() {
		return parent;
	}
	void setParent(int parent) {
		this.parent = parent;
	}
	public int getChild() {
		return child;
	}
	void setChild(int child) {
		this.child = child;
	}
}
