package com.altisource.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.experimental.Tolerate;

@Entity
@Builder
@Table(name="link",  uniqueConstraints={
		   @UniqueConstraint(columnNames={"source", "target","protocol"})
		})
public class Link {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String source;
	private String target;
	private int value;
	private String protocol;

	@Tolerate
	Link() {

	}

	public int getId() {
		return Id;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}

	public int getValue() {
		return value;
	}

	public String getProtocol() {
		return protocol;
	}

	@Override
	public String toString() {
		return "{source:" + getSource() + ",target:" + getTarget() + ",value:"
				+ getValue() + ",protocol:" + getProtocol() + "}";
	}

}
