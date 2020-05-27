/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import controlller.auxiliarFiltro;
import entity.Anuncio;
import entity.Perfil;
import entity.Usuario;
import entity.filtro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author bruno
 */
public class AnuncioDAO extends BasicDAO implements Serializable {
        public List<Object[]> listarAnuncioPerfil(auxiliarFiltro perfil, Double valor, String cidade, String bairro,String estado) {
        Perfil perfil2 = new Perfil();
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "From Anuncio a ";
        sql += "inner join a.perfil as p  where 1 = 1 and a.estado = :estado and a.status = true ";
    
        
        
            if (perfil.getBebe().equals("true") || perfil.getBebe().equals("false")) {
                sql += "and p.bebe =:bebe ";
            }  if(perfil.getFuma().equals("true") || perfil.getFuma().equals("false")){
                sql += "and p.fuma = :fuma ";
            }  if (perfil.getChegaMadrugada().equals("true") || perfil.getChegaMadrugada().equals("false")) {
                sql += "and p.chegaMadrugada =  :chegamadrugada";
            }  if (perfil.getCozinhaLimpaCasa().equals("true")  || perfil.getCozinhaLimpaCasa().equals("false")) {
                sql += " and p.cozinhaLimpaCasa = :cozinhalimpacasa";
            }  if (perfil.getSolteiro().equals("true") || perfil.getSolteiro().equals("false")){
                sql += " and p.solteiro = :solteiro ";
            }  if (perfil.getFazFestaCasa().equals("true") || perfil.getFazFestaCasa().equals("false")){
                sql += " and p.fazFestaCasa = :fazfestacasa";
            }  if (valor != 0){
                sql += "and  a.valorTotal = :valor ";
            }  if (cidade.equals("") != true){
                sql += "and  a.cidade = :cidade";
            }  if (bairro.equals("") != true){
                sql+= " and a.bairro = :bairro";
            }
            
                
            Query query = session.createQuery(sql);

            if (perfil.getBebe().equals("true") || perfil.getBebe().equals("false")) {
                query.setParameter("bebe", Boolean.valueOf(perfil.getBebe()));
            }  if(perfil.getFuma().equals("true") || perfil.getFuma().equals("false")){
                query.setParameter("fuma", Boolean.valueOf(perfil.getFuma()));
            }  if (perfil.getChegaMadrugada().equals("true") || perfil.getChegaMadrugada().equals("false")) {
                query.setParameter("chegamadrugada", Boolean.valueOf(perfil.getChegaMadrugada()));
            }  if (perfil.getCozinhaLimpaCasa().equals("true")  || perfil.getCozinhaLimpaCasa().equals("false")) {
                query.setParameter("cozinhalimpacasa", Boolean.valueOf(perfil.getCozinhaLimpaCasa()));
            }  if (perfil.getSolteiro().equals("true") || perfil.getSolteiro().equals("false")){
                query.setParameter("solteiro",Boolean.valueOf(perfil.getSolteiro()));
            }  if (perfil.getFazFestaCasa().equals("true") || perfil.getFazFestaCasa().equals("false")){
                query.setParameter("fazfestacasa", Boolean.valueOf(perfil.getFazFestaCasa()));
            }  if (valor != 0){
                query.setParameter("valor", valor);
            }  if (cidade.equals("") != true){
                query.setParameter("cidade", cidade);
            }  if (bairro.equals("") != true){
                query.setParameter("bairro", bairro);
            }
                query.setParameter("estado", estado);
                System.out.println("sql: " + sql);

        List<Object[]> list = query.list();
        
        transaction.commit();
        session.close();

        return list;
    }

    public List<Anuncio> listarAnuncioDono(Usuario usuario) {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("from Anuncio u where u.usuario = :usuario");
        query.setParameter("usuario", usuario);
        List<Anuncio> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Anuncio> listarAnuncio() {
        session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("From Anuncio u where u.status = 1");
        List<Anuncio> list = query.list();
        transaction.commit();
        session.close();

        return list;
    }
    public Anuncio listarAnuncio(int id) {
        session = getSessionFactory().openSession();
        Query query = session.createQuery("From Anuncio u where u.idAnuncio = :id");
        query.setParameter("id", id);
        Anuncio anuncio = (Anuncio) query.list().get(0);
        session.close();

        return anuncio;
    }
    public List<Anuncio> listarporcidade(String estado) {
        try {
            
            System.out.println(estado);
                    session = getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("From Anuncio u where u.estado = :estado and  u.status = true");
        query.setParameter("estado", estado);
        List<Anuncio> list = query.list();
        transaction.commit();
        session.close();
        return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;


        
    }
}
