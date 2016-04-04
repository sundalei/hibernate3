package com.shengsiyuan.hibernate;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
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
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Customer customer = new Customer();
			customer.setName("zhangsan");
			customer.setOrders(new HashSet<Order>());
			
			Order order1 = new Order();
			order1.setOrderNumber("order1");
			
			Order order2 = new Order();
			order2.setOrderNumber("order2");
			
			Order order3 = new Order();
			order3.setOrderNumber("order3");
			
			order1.setCustomer(customer);
			order2.setCustomer(customer);
			order3.setCustomer(customer);
			
			customer.getOrders().add(order1);
			customer.getOrders().add(order2);
			customer.getOrders().add(order3);
			
			session.save(customer);
			
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
		*/
		
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Customer customer = null;
		
		try {
			tx = session.beginTransaction();
			
			customer = session.get(Customer.class, new Long(1));
			System.out.println(customer.getName());
			
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
		
		Set<Order> set = customer.getOrders();
		for(Iterator<Order> iter = set.iterator(); iter.hasNext();) {
			Order order = iter.next();
			System.out.println(order.getOrderNumber());
		}
		*/
		
		/*
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Order order = null;
		
		try {
			tx = session.beginTransaction();
			
			order = session.get(Order.class, new Long(1));
			System.out.println(order.getOrderNumber());
			
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
		
		System.out.println(order.getCustomer().getName());
		*/
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			Customer customer = session.get(Customer.class, new Long(1));
			session.delete(customer);
			
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
