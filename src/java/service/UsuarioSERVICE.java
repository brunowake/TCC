/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Usuario;
import repository.UsuarioDAO;
import entity.*;
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
public class UsuarioSERVICE {

    private UsuarioDAO usuariodao;
    private PerfilDAO perfilDAO;

    public UsuarioSERVICE() {
        usuariodao = new UsuarioDAO();
        perfilDAO = new PerfilDAO();
    }
    
    public Usuario buscarID(Usuario user, Integer id){
        
        return (Usuario) usuariodao.listarID(user, id);
    }

    public void salvarUsuario(Usuario user) {
        try {
            usuariodao.salvar(user);
        } catch (Exception e) {
        }
    }
    
    public void alterarUsuario(Usuario user){
        System.out.println("id " + user.getIdUsuario());
        usuariodao.update(user);
    }
    
    public void excluirUsuairo(Usuario user){
        usuariodao.deletar(user);
        perfilDAO.deletar(user.getPerfil());
        
        
    }

    public List<Usuario> listarTodos() {
        List<Usuario> list = usuariodao.listarUsuario();

        return list;
    }

    public Usuario userlogin(String email){
        return usuariodao.userlogin(email);
    }
    
    public  List<Usuario> listarnome(String nome){
        List<Usuario> list = usuariodao.listarNome(nome);
        return list;
    }
    
    public  List<Usuario> listarcpf(String cpf){
        List<Usuario> list = usuariodao.listarcpf(cpf);
        return list;
    }
}
