/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import entity.Perfil;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.PerfilSERVICE;

/**
 *
 * @author bruno
 */
@SessionScoped
@ManagedBean
public class perfilController implements Serializable {
    private Perfil perfil;
    private PerfilSERVICE perfilservice;
    
    
        @PostConstruct
    private void inicializar (){
      perfil = new Perfil();
      perfilservice = new PerfilSERVICE();
    }

    
    
    public void salvar (ActionEvent event){
        try {
        perfilservice.salvarPerfilRetorno(perfil);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    
    
    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public PerfilSERVICE getPerfilservice() {
        return perfilservice;
    }

    public void setPerfilservice(PerfilSERVICE perfilservice) {
        this.perfilservice = perfilservice;
    }
    
    
    
    
    
}
