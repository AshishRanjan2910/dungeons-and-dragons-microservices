package com.gamecharacter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamecharacter.Repository.GameCharacterDao;
import com.gamecharacter.entity.GameCharacter;
import com.gamecharacter.entity.GameCharacterDTO;

@RestController
@RequestMapping("/gamecharacter")
public class GameCharacterController {
	
	@Autowired
	private GameCharacterDao characterDao;
	
	@PostMapping("/")
	public ResponseEntity<String> postingData(@RequestBody GameCharacter character){
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

	public List<GameCharacterDTO> getChilds(GameCharacterDTO character){
		List<GameCharacterDTO> children = new ArrayList<>();
//		while(character = characterDao.getReferenceById(character.getReferenceById()))
//		GameCharacter character = characterDao.getReferenceById(null)
//		GameCharacterDTO dto = new GameCharacterDTO();
//		BeanUtils.copyProperties(character, dto);
//		dto.setSubClasses(this.getChilds(dto));
//		if(character.getParentid()) {
//			pass;
//		}
//		else {
////			children = <callNewMethood>
//			pass;
//		}
		return children;
	}
}

//Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: com.gamecharacter.entity.GameCharacter$HibernateProxy$zfKzNQi1["hibernateLazyInitializer"])] with root cause
//
//com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hiber
