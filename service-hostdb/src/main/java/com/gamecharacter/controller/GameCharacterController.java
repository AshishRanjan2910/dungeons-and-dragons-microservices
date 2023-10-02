package com.gamecharacter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;
import com.gamecharacter.Repository.GameCharacterDao;
import com.gamecharacter.entity.GameCharacter;
import com.gamecharacter.entity.GameCharacterDTO;
import com.gamecharacter.entity.ParentChildPairDTO;
import com.gamecharacter.service.GameCharacterRestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/gamecharacter")
public class GameCharacterController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameCharacterController.class);
	
	@Autowired
	private GameCharacterDao characterDao;
	@Autowired
	private GameCharacterRestUtil gameCharacterRestUtil;
	
	@PostMapping("/")
	public ResponseEntity<String> postingData(@RequestBody GameCharacter character, ModelMap model){
		characterDao.save(character);
		return new ResponseEntity<String>("Created data...", HttpStatus.CREATED);
	}
	@GetMapping("/{characterId}")
	public ResponseEntity<GameCharacterDTO> getGameCharacter(@PathVariable("characterId") Integer characterId) {
		try {
			GameCharacterDTO dto = gameCharacterRestUtil.getCharacterById(characterId);
			return new ResponseEntity<GameCharacterDTO>(dto, HttpStatus.OK);
		} catch(Exception e) {
			LOGGER.error("Error: "+e);
			return null;
		}
	}
	
	@DeleteMapping("/{characterId}")
    public void deleteGameCharacter(@PathVariable("characterId") Integer characterId) {
		characterDao.deleteById(characterId);
    }

	@GetMapping("/dndFamily")
	public ResponseEntity<List<GameCharacterDTO>> getDndFamily() {
		List<GameCharacterDTO> allCharacters = gameCharacterRestUtil.getDndGraph();

	    return new ResponseEntity<List<GameCharacterDTO>>(allCharacters, HttpStatus.OK);
	}

}
