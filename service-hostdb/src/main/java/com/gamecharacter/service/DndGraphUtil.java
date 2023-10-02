package com.gamecharacter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamecharacter.entity.GameCharacterDTO;
import com.gamecharacter.entity.ParentChildPairDTO;

@Service
public class DndGraphUtil {
	@Autowired
	private GameCharacterRestUtil gameCharacterRestUtil;
	
	private GameCharacterDTO hierarchyBuilder(Map<Integer, ArrayList<Integer>> adj, int member, Queue<Integer> queue, Set<Integer> vis) {
		vis.add(member);
		
		GameCharacterDTO currMember = gameCharacterRestUtil.getCharacterById(member);
		List<GameCharacterDTO> childMembers = new ArrayList<>();
		
		for (int neighbor: adj.get(member)) {
			if (!vis.contains(neighbor)) {
				childMembers.add(hierarchyBuilder(adj, neighbor, queue, vis));
			}
		}
		
		currMember.setSubClasses(childMembers);
		
		return currMember;
	}
	
	public List<GameCharacterDTO> buildDndHierarchy(List<ParentChildPairDTO> flattenFamily) {
		List<GameCharacterDTO> dndHierarchy = new ArrayList<>();
		
		Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
		
		for (ParentChildPairDTO pair: flattenFamily) {
			if (pair.getParent()!=0)
				adj.put(pair.getParent(), new ArrayList<>());
			adj.put(pair.getChild(), new ArrayList<>());
		}
		
		for (ParentChildPairDTO pair: flattenFamily) {
			if (pair.getParent() != 0)
				adj.get(pair.getParent()).add(pair.getChild());
		}
		
		Set<Integer> visitedMemberTracker = new HashSet<>();
		
		for (int memberId: adj.keySet()) {
			Queue<Integer> queue = new LinkedList<Integer>();
			if (!visitedMemberTracker.contains(memberId)) {
				GameCharacterDTO topMember = hierarchyBuilder(adj, memberId, queue, visitedMemberTracker);
				dndHierarchy.add(topMember);
			}
		}
		
		return dndHierarchy;
	}
}
