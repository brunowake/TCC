/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author carlos
 */
public abstract class BasicDAO<T> {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public Session session;
    public Transaction transaction;
    
    public void salvar(T classe){
        Session session;
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = getSessionFactory().openSession();
        }
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(classe);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Erro salvar interesse: " + ex);
            ex.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
    }
    public T salvarRetorno(T classe){
 Session session;
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = getSessionFactory().openSession();
        }
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(classe);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error deleting car: " + ex);
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
        
        return classe;
    }
    
    public void deletar (T classe){
 Session session;
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = getSessionFactory().openSession();
        }
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(classe);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error deleting car: " + ex);
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
        
    }
    
    public void update(T classe) {
        Session session;
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = getSessionFactory().openSession();
        }
        Transaction transaction = null;

        try {   
          
            transaction = session.beginTransaction();
            session.merge(classe);
            
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error deleting car: " + ex.getStackTrace());
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }
        
    }
    
    public T listarID (T classe, Integer id){
         Session session;
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = getSessionFactory().openSession();
        }
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.load(classe, id);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error deleting car: " + ex);
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()){
                session.close();
            }
        }

        return  classe;
    }
    
}
