/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Perfil;
import repository.PerfilDAO;

/**
 *
 * @author bruno
 */
public class PerfilSERVICE {

    private PerfilDAO perfdao;
    private Perfil p;

    public PerfilSERVICE() {
        this.perfdao = new PerfilDAO();
    }

    public Perfil buscarID(Perfil perfil, Integer id) {

        return (Perfil) perfdao.listarID(perfil, id);
    }

    public Perfil salvarPerfilRetorno(Perfil perfil) {
        try {
            p = (Perfil) perfdao.salvarRetorno(perfil);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }
    
    
        public void alterarPerfil(Perfil perfil){
        perfdao.update(perfil);
    }
    
    
    
}
