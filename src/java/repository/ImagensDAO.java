/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entity.Anuncio;
import entity.Imagens;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author bruno
 */
public class ImagensDAO extends BasicDAO implements Serializable {

    List<Imagens> list;

    public List<Imagens> listarImagensAnuncio(Anuncio anuncio) {
        try {
            session = getSessionFactory().getCurrentSession();
        } catch (HibernateException ex) {
            session = getSessionFactory().openSession();
        }
        try {
            transaction = null;
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Imagens u where u.anuncio = :anuncio");
            query.setParameter("anuncio", anuncio);
            list = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }

        }

        return list;
    }
}
