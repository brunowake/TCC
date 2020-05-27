/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author bruno
 */


@SessionScoped
@ManagedBean
public class navega  implements Serializable{
    
    
    
    public void cadastro(){
        navega("/pages/usuario/cadastro.xhtml");
    }
    public void voltarlogin (){
        navega("/login.xhtml");
    }
    public void alterarusuario(){
        navega("/pages/usuario/alterar.xhtml");
    }
    public void listaAnuncio(){
        navega("/pages/anuncio/lista.xhtml");
    }
    public void admin(){
        navega("/pages/admin/homeAdmin.xhtml");
    }
    public void listaaceite(){
        navega("/pages/anuncio/listaAceite.xhtml");
    }
    
    
    
    
        public void navega(String end) {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath()
                    + end);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
