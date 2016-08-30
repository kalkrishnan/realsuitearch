package com.altisource.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.experimental.Tolerate;

@Entity
@Builder
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(unique = true)
	private String name;
	private String type;

	@Tolerate
	Node() {

	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "{name:" + getName() + "," + "type:" + getType() + "}";
	}

	public int getId() {
		return Id;
	}
}
