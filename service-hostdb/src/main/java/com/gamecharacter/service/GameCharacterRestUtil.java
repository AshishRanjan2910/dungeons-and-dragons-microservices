package com.gamecharacter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamecharacter.Repository.GameCharacterDao;
import com.gamecharacter.entity.GameCharacter;
import com.gamecharacter.entity.GameCharacterDTO;
import com.gamecharacter.entity.ParentChildPairDTO;

@Service
public class GameCharacterRestUtil {
	@Autowired
	private GameCharacterDao gameCharacter;
	@Autowired
	private DndGraphUtil dndGraphUtil;
	
	public GameCharacterDTO getCharacterById(int id) {
		GameCharacter character = gameCharacter.getReferenceById(id);
		GameCharacterDTO gameCharacterDto = new GameCharacterDTO();
		BeanUtils.copyProperties(character, gameCharacterDto);
		return gameCharacterDto;
	}
	
	public List<GameCharacterDTO> getDndGraph() {
		List<GameCharacter> allCharactersOfDnD = gameCharacter.findAll();
	    List<ParentChildPairDTO> flattenFamily = new ArrayList<>();
	    
	    for (GameCharacter gameCharacter: allCharactersOfDnD) {
	    	flattenFamily.add(new ParentChildPairDTO(gameCharacter.getParentid(), gameCharacter.getId()));
	    }
	    
	    // Create a list to store the DTOs
	    List<GameCharacterDTO> allCharacters = dndGraphUtil.buildDndHierarchy(flattenFamily);
	    
	    return allCharacters;
	}
}
