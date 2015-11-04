package com.mitchell.db.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	private static final EntityManagerProvider singleton = new EntityManagerProvider();

	private EntityManagerFactory emf;

	private EntityManagerProvider() {
	}

	public static EntityManagerProvider getInstance() {
		return singleton;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("databaseService");
		}

		return emf;
	}

	public void closeEmf() {
		if (emf.isOpen() || emf != null) {
			emf.close();
		}
		emf = null;

	}
}
