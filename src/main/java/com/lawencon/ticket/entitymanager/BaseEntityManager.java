package com.lawencon.ticket.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseEntityManager {

	protected EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
}