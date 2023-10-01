package com.gamecharacter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gamecharacter.entity.GameCharacter;

@Repository
public interface GameCharacterDao extends JpaRepository<GameCharacter, Integer>{	
	public List<GameCharacter> findAll();
//	Create a method to pass parentId and get List<children>
}
