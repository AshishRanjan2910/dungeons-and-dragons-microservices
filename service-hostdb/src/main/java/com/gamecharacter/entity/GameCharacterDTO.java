package com.gamecharacter.entity;

import java.util.ArrayList;
import java.util.List;

public class GameCharacterDTO {
	private int id;
	private int parentid;
	private String name;
	private String color;
	
	List<GameCharacterDTO> subClasses = new ArrayList<>();
	
	public GameCharacterDTO(GameCharacter gameCharacter) {
		// TODO Auto-generated constructor stub
	}
	public GameCharacterDTO() {
		// TODO Auto-generated constructor stub
	}
	public List<GameCharacterDTO> getSubClasses() {
		return subClasses;
	}
	public void setSubClasses(List<GameCharacterDTO> subClasses) {
		this.subClasses = subClasses;
	}
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
}
