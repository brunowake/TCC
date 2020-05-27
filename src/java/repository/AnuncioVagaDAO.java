/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;
import entity.AnuncioVaga;
import entity.Usuario;
import java.util.List;
import org.hibernate.Query;
/**
 *
 * @author bruno
 */
public class AnuncioVagaDAO  extends BasicDAO<Object>{
    
    
        public List<AnuncioVaga> listarAnuncioDono(Usuario logado) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from AnuncioVaga u where u.usuario = :logado");
        query.setParameter("logado", logado);
        List<AnuncioVaga> list =query.list();
        transaction.commit();
        session.close();
        return list;
    }
    public List<AnuncioVaga> listarAnuncioInteressados(Usuario logado) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from AnuncioVaga u where u.anuncio.usuario = :logado");
        query.setParameter("logado", logado);
        List<AnuncioVaga> list =query.list();
        transaction.commit();
        session.close();
        return list;
    }

    
}
