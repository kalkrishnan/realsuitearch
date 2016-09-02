package com.altisource.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.experimental.Tolerate;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

@Entity
@Builder
public class Node {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose(serialize = false)
	private int Id;
	@Expose
	@Column(unique = true)
	private String name;
	@Expose
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
		return new GsonBuilder().create().toJson(this);
	}

	public int getId() {
		return Id;
	}
}
