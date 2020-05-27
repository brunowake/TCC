/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.Serializable;
import entity.Usuario;
import java.util.List;
import org.hibernate.Query;
/**
 *
 * @author bruno
 */
public class UsuarioDAO extends BasicDAO implements Serializable {
    
    public List<Usuario> listarUsuario(){
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("From Usuario");
        List<Usuario> list = query.list();
        transaction.commit();
        session.close();
        
        return list;
    }
    
    
    public Usuario userlogin(String email){
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Usuario u where u.email = :email");
        query.setParameter("email", email);
        List<Usuario> list = query.list();
        Usuario user = list.get(0);
        transaction.commit();
        session.close();
        return user;
    }
    
    public  List<Usuario>  listarNome(String nome){
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Usuario u where lower(u.nome) like lower(:nome) ");
        query.setParameter("nome", "%"+nome+"%");
        List<Usuario> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
    
        public  List<Usuario>  listarcpf(String cpf){
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Usuario u where u.cpf = :cpf");
        query.setParameter("cpf", cpf);
        List<Usuario> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
