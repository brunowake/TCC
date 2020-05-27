/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;


import controlller.anuncioController;
import controlller.usuarioController;
import entity.Anuncio;
import entity.filtro;
import entity.Perfil;
import entity.Usuario;
import entity.filtro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.kohsuke.rngom.digested.Main;
import repository.AnuncioDAO;
import repository.AnuncioVagaDAO;
import repository.PerfilDAO;
import repository.UsuarioDAO;
import service.AnuncioSERVICE;
import service.PerfilSERVICE;
import service.UsuarioSERVICE;
import util.HibernateUtil;

/**
 *
 * @author bruno
 */
public class teste {
        
    public Session session;
    public Transaction transaction;
    private Perfil p;
    
    public void salvar(Usuario  usuario,Perfil perfil){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(perfil);
            p = (Perfil) session.merge(perfil);
            session.save(usuario);
            transaction.commit();
            session.close();
            System.out.println("pegou");
        } catch (Exception e) {
            e.printStackTrace();
        }
         
    }
    
    public List<Usuario> listarUsuariooooo(){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Query query = session.createQuery("From Usuario");
        List<Usuario> list = query.list();
        transaction.commit();
        session.close();
        
        return list;
    }
    
    public static void main(String[] args) {
        teste aa = new teste();
        //PerfilSERVICE perfservice = new PerfilSERVICE();
        //PerfilDAO perfdao = new PerfilDAO();
        //UsuarioSERVICE service = new UsuarioSERVICE();
       // UsuarioDAO dao = new UsuarioDAO();
       // AnuncioVagaDAO vagadao = new AnuncioVagaDAO();
       // AnuncioDAO dao1 = new AnuncioDAO();
       // AnuncioSERVICE anuncioservice = new AnuncioSERVICE();
       // Perfil perf = new Perfil();
       // Anuncio anun = new Anuncio();
       // Usuario user = new Usuario();
       // user.setIdUsuario(36);
       // anun.setIdAnuncio(1);
      //  perf.setBebe(true);
     //   perf.setChegaMadrugada(true);
     //   perf.setCozinhaLimpaCasa(true);
       // perf.setFazFestaCasa(true);
        //perf.setFuma(false);
        //perf.setSolteiro(true);
      //  perf.setIdPerfil(75);
        //for(filtro a: dao1.listarAnuncioPerfil(perf))
          //  System.out.println("nome: "+ a.getAnuncio().getNomeProprietario());
        //service.salvarUsuario(user);

        
		//for(Object[] arr : anuncioservice.listaPorPerfil(perf)){
		//	System.out.println( ((Anuncio)arr[0]).getIdAnuncio() );
                  //  System.out.println("");
                //}
                

        List<Usuario> list = aa.listarUsuariooooo();
        
        for(Usuario u: list)
            System.out.println(u.getNome());
 
        


    }
}
