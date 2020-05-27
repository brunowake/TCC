/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Anuncio;
import entity.Imagens;
import repository.AnuncioDAO;
import entity.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import repository.ImagensDAO;
import repository.PerfilDAO;

/**
 *
 * @author bruno
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ImagensSERVICE implements Serializable{
    
    private ImagensDAO imgdao;
    
    public ImagensSERVICE(){
        imgdao = new ImagensDAO();
    }
    
    public void salvarImg (Imagens img){
        imgdao.salvar(img);
    }
    
    public void alterarImg(Imagens img){
        imgdao.update(img);
    }
    
    public void exlcuirImg(Imagens img){
        imgdao.deletar(img);

    }
    
    public List<Imagens> listarImagensAnuncio(Anuncio anuncio) {
        List<Imagens> list = imgdao.listarImagensAnuncio(anuncio);

        return list;
    }
    

    
    
}
