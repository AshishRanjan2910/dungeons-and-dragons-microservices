package com.gamecharacter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import com.gamecharacter.Repository.GameCharacterDao;
import com.gamecharacter.entity.GameCharacter;
import com.gamecharacter.entity.GameCharacterDTO;

@RestController
@RequestMapping("/gamecharacter")
public class GameCharacterController {
	
	@Autowired
	private GameCharacterDao characterDao;
	
	@PostMapping("/")
	public ResponseEntity<String> postingData(@RequestBody GameCharacter character, ModelMap model){
		characterDao.save(character);
		return new ResponseEntity<String>("Created data...", HttpStatus.CREATED);
	}
	@GetMapping("/{characterId}")
	public ResponseEntity<GameCharacterDTO> getGameCharacter(@PathVariable("characterId") Integer characterId) {
		try{
			GameCharacter character = characterDao.getReferenceById(characterId);
			GameCharacterDTO dto = new GameCharacterDTO();
			BeanUtils.copyProperties(character, dto);
			dto.setSubClasses(this.getChilds(dto));
			return new ResponseEntity<GameCharacterDTO>(dto, HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println("Error: "+e);
			return null;
		}
		
	}
	
	@DeleteMapping("/{characterId}")
    public void deleteGameCharacter(@PathVariable("characterId") Integer characterId) {
		characterDao.deleteById(characterId);
    }
	
	public List<GameCharacterDTO> getChilds(GameCharacterDTO character) {
		return new ArrayList<>();
	}

	@GetMapping("/dndFamily")
	public List<GameCharacterDTO> getDndFamily() {
	    List<GameCharacter> allCharactersOfDnD = characterDao.findAll();
	    
	    // Create a list to store the DTOs
	    List<GameCharacterDTO> allCharacters = new ArrayList<>();

	    // Convert GameCharacter entities to GameCharacterDTO objects
	    for (GameCharacter gameCharacter : allCharactersOfDnD) {
	        GameCharacterDTO gameCharacterDTO = new GameCharacterDTO(gameCharacter);
	        allCharacters.add(gameCharacterDTO);
	    }

	    return allCharacters;
	}

}
