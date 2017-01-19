package com.altisource;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altisource.dao.LinkRepository;
import com.altisource.dao.NodeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The main Service class which provides REST endpoints to do the following:
 * 1) Retrieve the current architecture
 * 2) Delete a link between two products using the Source, Product and Protocol
 * 3) Delete a product
 * @author krishkal
 *
 */
@RestController
public class REALSuiteService {

	@Autowired
	LinkRepository linkRepo;

	@Autowired
	NodeRepository nodeRepo;
	
	@Autowired
	private SimpMessagingTemplate messageSender;

	private static final Logger logger = LoggerFactory
			.getLogger(REALSuiteService.class);
/**
 * Retrieves the current architecture and returns it as a JSON.
 * @return String
 */
	@CrossOrigin
	@RequestMapping("/getArchitecture")
	public String getArchitecture() {
		Gson gson = new GsonBuilder().create();
		return "{\"nodes\":" + gson.toJson(nodeRepo.findAll()) + ",\"links\":"
				+ gson.toJson(linkRepo.findAll()) + "}";

	}

	/**
	 * Deletes a link between two products 
	 * @param source -- The source Product
	 * @param target -- The Target Product
	 * @param protocol -- The protocol this link uses(HTTP, JDBC)
	 */
	@CrossOrigin
	@Transactional
	@RequestMapping("/deleteLinkBySourceAndTargetAndProtocol")
	public void deleteByLinkSourceAndTargetAndProtocol(
			@RequestParam(value = "source", required = false) String source,
			@RequestParam(value = "target", required = false) String target,
			@RequestParam(value = "protocol", required = false) String protocol) {
		logger.info(REALSuiteService.class.getCanonicalName(), "Deleting Link: ********************** " + source + "-->"
				+ target + ":" + protocol);
		linkRepo.deleteBySourceAndTargetAndProtocol(source, target, protocol);
	}

	/**
	 * Deletes a Node using it's name and Type
	 * @param name
	 * @param type
	 */
	@Transactional
	@RequestMapping("/deleteNodeByNameAndType")
	public void deleteNodeByNameAndType(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "type", required = false) String type) {
		logger.info("Deleting Node: ********************** " + name + ":"
				+ type);
		nodeRepo.deleteByNameAndType(name, type);
		messageSender.convertAndSend("/topic/arch/nodedelete", "{\"name\": \""+name+"\",\"type\": \""+type+"\"}");
	}
}
