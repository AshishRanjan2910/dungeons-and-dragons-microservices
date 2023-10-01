package com.gamecharacter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GameCharacter")
public class GameCharacter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="parentid")
	private int parentid;
	@Column(name="name")
	private String name;
	@Column(name="color")
	private String color;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public GameCharacter(int id, int parentid, String name, String color) {
		super();
		this.id = id;
		this.parentid = parentid;
		this.name = name;
		this.color = color;
	}
	public GameCharacter() {
		super();
	}
	
}
