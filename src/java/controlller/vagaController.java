/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import entity.Anuncio;
import entity.AnuncioVaga;
import entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import service.AnuncioSERVICE;
import service.AnuncioVagaSERVICE;

/**
 *
 * @author bruno
 */
@SessionScoped
@ManagedBean
public class vagaController implements Serializable {

    private AnuncioVagaSERVICE vagaservice;
    private Anuncio anuncioselecionado;
    private AnuncioSERVICE anunservice;
    private AnuncioVaga vaga;
    private AnuncioVaga vagaselecionada;
    private Usuario logado;
    private List<AnuncioVaga> listavaga;
    private List<AnuncioVaga> listainteressados;
    private String obs;
    private String status;

    @PostConstruct
    public void inicializar() {
        anuncioselecionado= new Anuncio();
        vagaservice = new AnuncioVagaSERVICE();
        anunservice = new AnuncioSERVICE();
        vaga = new AnuncioVaga();
        obs = new String();
        logado = JsfSessionContext.getInstance().getPessoaLogado();
        System.out.println("logado sexo inicializar: " + JsfSessionContext.getInstance().getPessoaLogado().getCpf());
        listarporDono();
        listarporinteressados();
    }
    
    public void listarporDono(){
         listavaga = vagaservice.listar(logado);
    }
        public void listarporinteressados(){
            System.out.println("id logado " + logado.getIdUsuario());                 
            for(AnuncioVaga a: vagaservice.listarinteressados(logado))
            System.out.println("nome: "+ a.getIdAnuncioVaga());
         listainteressados = vagaservice.listarinteressados(logado);

    }
  public String zerarSelecionado(){
      String nome = new String();
      nome = "teste";
      return nome;
  }
    public void salvarVAga() {
        try {
        System.out.println("teste vaga controller" + logado.getIdUsuario());
                logado.setSexo("m");
        vaga.setUsuario(logado);
            System.out.println("logado sexo: " + logado.getNome());
        anuncioselecionado.setHits(anuncioselecionado.getHits()+1);
            
        vaga.setAnuncio(anuncioselecionado);
        
        vaga.setAceite(false);
        anunservice.alterarAnuncio(anuncioselecionado);
        vagaservice.salvarVaga(vaga);
        System.out.println("teste vaga controller 2: " + logado.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void deletarVaga(){
        vagaselecionada.setObsAceite(obs);
        System.out.println("valor obs: " + obs);
        vagaselecionada.setAceite(false);
        vagaservice.alterarVaga(vagaselecionada);
        navegalistaaceite();
    }
    
    public void aceitar(){
         System.out.println("valor obs: " + obs);
        vagaselecionada.setObsAceite(obs);
        vagaselecionada.setAceite(true);
        vagaservice.alterarVaga(vagaselecionada);
        navegalistaaceite();
    }
    
    
        public void navegalistaaceite(){
            inicializar();
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
    
    public Anuncio getAnuncioselecionado() {
        return anuncioselecionado;
    }

    public void setAnuncioselecionado(Anuncio anuncioselecionado) {
        this.anuncioselecionado = anuncioselecionado;
    }

    public AnuncioVaga getVaga() {
        return vaga;
    }

    public void setVaga(AnuncioVaga vaga) {
        this.vaga = vaga;
    }

    public List<AnuncioVaga> getListavaga() {
        return listavaga;
    }

    public void setListavaga(List<AnuncioVaga> listavaga) {
        this.listavaga = listavaga;
    }

    public AnuncioVaga getVagaselecionada() {
        return vagaselecionada;
    }

    public void setVagaselecionada(AnuncioVaga vagaselecionada) {
        this.vagaselecionada = vagaselecionada;
    }

    public List<AnuncioVaga> getListainteressados() {
        return listainteressados;
    }

    public void setListainteressados(List<AnuncioVaga> listainteressados) {
        this.listainteressados = listainteressados;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
