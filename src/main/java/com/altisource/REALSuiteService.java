package com.altisource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altisource.dao.LinkRepository;
import com.altisource.dao.NodeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class REALSuiteService {

	@Autowired
	LinkRepository linkRepo;

	@Autowired
	NodeRepository nodeRepo;

	@CrossOrigin
	@RequestMapping("/getArchitecture")
	public String getArchitecture() {
		Gson gson = new GsonBuilder().create();
		return "{\"nodes\":" + gson.toJson(nodeRepo.findAll()) + ",\"links\":"
				+ gson.toJson(linkRepo.findAll()) + "}";

	}

	@CrossOrigin
	@Transactional
	@RequestMapping("/deleteBySourceAndTargetAndProtocol")
	public void deleteBySourceAndTargetAndProtocol(
			 @RequestParam(value = "source", required = false) String source,
			 @RequestParam(value = "target", required = false) String target,
			 @RequestParam(value = "protocol", required = false) String protocol) {
		System.out.println(source + ":" + target + ":" + protocol);
		linkRepo.deleteBySourceAndTargetAndProtocol(source, target, protocol);
	}
	
	@Transactional
	@RequestMapping("/deleteByNameAndType")
	public void deleteByNameAndType(
			 @RequestParam(value = "name", required = false) String name,
			 @RequestParam(value = "type", required = false) String type) {
		nodeRepo.deleteByNameAndType(name, type);
	}
}
