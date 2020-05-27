/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Usuario;
import entity.Anuncio;
import controlller.auxiliarFiltro;
import repository.AnuncioDAO;
import entity.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import repository.PerfilDAO;

/**
 *
 * @author bruno
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AnuncioSERVICE implements Serializable{
    
    private AnuncioDAO anunciodao;
    private PerfilDAO perfildao;
    
    public AnuncioSERVICE(){
        anunciodao = new AnuncioDAO();
        perfildao = new PerfilDAO();
    }
    
    public void salvarAnuncio(Anuncio anuncio){
        anunciodao.salvar(anuncio);
    }
    
    public void alterarAnuncio(Anuncio anuncio){
        anunciodao.update(anuncio);
    }
    
    public void exlcuirAnuncio(Anuncio anuncio){
        anunciodao.deletar(anuncio);
        perfildao.deletar(anuncio.getPerfil());
    }
    
    public List<Anuncio> listarTodos() {
        List<Anuncio> list = anunciodao.listarAnuncio();

        return list;
    }
    
    public Anuncio listarpoid(int id){
        return anunciodao.listarAnuncio(id);
    }
    
    public  List<Anuncio> listaPorDono(Usuario id){
        List<Anuncio> list = anunciodao.listarAnuncioDono(id);
        return list;
    }
    
    public  List<Anuncio> listaPorEstado(String estado){
        List<Anuncio> list = anunciodao.listarporcidade(estado);
        return list;
    }
    
    public  List<Object[]> listaPorPerfil(auxiliarFiltro perfil,double valor, String cidade, String bairro,String estado){
        List<Object[]> list = anunciodao.listarAnuncioPerfil(perfil, valor, cidade, bairro,estado);

            
        return list;
    }
    
}
