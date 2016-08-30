package com.altisource.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, Integer> {

	@Transactional
	void deleteBySourceAndTargetAndProtocol(String source, String target,
			String protocol);
}
