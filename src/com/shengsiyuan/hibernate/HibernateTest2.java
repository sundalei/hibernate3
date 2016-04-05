package com.shengsiyuan.hibernate;

import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTest2 {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		    sessionFactory = configuration.buildSessionFactory(registry);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Category category1 = new Category("level1", null, new HashSet<Category>());
			Category category2 = new Category("level2", null, new HashSet<Category>());
			Category category3 = new Category("level2", null, new HashSet<Category>());
			Category category4 = new Category("level3", null, new HashSet<Category>());
			Category category5 = new Category("level3", null, new HashSet<Category>());
			Category category6 = new Category("level3", null, new HashSet<Category>());
			Category category7 = new Category("level3", null, new HashSet<Category>());
			
			category2.setParentCategory(category1);
			category3.setParentCategory(category1);
			
			category1.getChildCategories().add(category2);
			category1.getChildCategories().add(category3);
			
			category4.setParentCategory(category2);
			category5.setParentCategory(category2);
			
			category2.getChildCategories().add(category4);
			category2.getChildCategories().add(category5);
			
			category6.setParentCategory(category3);
			category7.setParentCategory(category3);
			
			category3.getChildCategories().add(category6);
			category3.getChildCategories().add(category7);
			
			session.save(category1);
			
			tx.commit();
		} catch(Exception ex) {
			if(null != tx) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			if(null != session) {
				session.close();
			}
		}
		
		sessionFactory.close();
	}
}
