package com.altisource.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NodeRepository extends CrudRepository<Node, Integer> {

	 @Query("select n.name from Node n")
	public List<String> getAllName();
	 
	 @Transactional
		void deleteByNameAndType(String name, String type);
}
 