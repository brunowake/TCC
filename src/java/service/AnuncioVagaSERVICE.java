/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import entity.AnuncioVaga;
import entity.Usuario;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import repository.AnuncioVagaDAO;

/**
 *
 * @author bruno
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AnuncioVagaSERVICE implements Serializable{
    
    private AnuncioVaga vaga;
    private AnuncioVagaDAO anunciovagadao;
    
    public AnuncioVagaSERVICE(){
        vaga = new AnuncioVaga();
        anunciovagadao = new AnuncioVagaDAO();
    }    
    
    public List<AnuncioVaga> listar (Usuario logado){
        return anunciovagadao.listarAnuncioDono(logado);
    }
    public List<AnuncioVaga> listarinteressados (Usuario logado){
        return anunciovagadao.listarAnuncioInteressados(logado);
    }
    public void salvarVaga(AnuncioVaga vaga){
        anunciovagadao.salvar(vaga);
    }
    
    public void alterarVaga(AnuncioVaga vaga){
        anunciovagadao.update(vaga);
    }
    
    public void exlcuirVaga(AnuncioVaga vaga){
        anunciovagadao.deletar(vaga);

    }
    
    
}
