package com.altisource.dao;

import javax.websocket.OnError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.google.gson.Gson;

@RepositoryEventHandler
public class ArchRepositoryEventHandler {

	@Autowired
	private SimpMessagingTemplate messageSender;
	private Gson gson = new Gson();
	private static final Logger logger = LoggerFactory
			.getLogger(ArchRepositoryEventHandler.class);

	@HandleAfterCreate(Link.class)
	public void handleLinkSave(Link p) {
		String json = gson.toJson(p);
		messageSender.convertAndSend("/topic/arch/linksave", json);
		logger.info("Saving Link: ********************** " + json);
	}

	@HandleAfterCreate(Node.class)
	public void handleNodeSave(Node p) {
		String json = gson.toJson(p);
		logger.info("Saving Node: ********************** " + json);
		messageSender.convertAndSend("/topic/arch/nodesave", json);

	}

	@HandleAfterDelete(Node.class)
	public void handleNodeDelete(Node p) {
		String json = gson.toJson(p);
		System.out.println("Node Deleted: "+json);
		

	}

	@OnError
	public void onError(Throwable error) {

	}

}
