/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import entity.Anuncio;
import entity.Imagens;
import entity.Perfil;
import entity.Usuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javassist.compiler.TokenId;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.AnuncioSERVICE;
import service.ImagensSERVICE;
import service.PerfilSERVICE;

/**
 *
 * @author bruno
 */
@SessionScoped
@ManagedBean
public class anuncioController implements Serializable{
    private Usuario logado = JsfSessionContext.getInstance().getPessoaLogado();
    private Perfil perfil;
    private Anuncio anuncio;
    private Anuncio anuncioteste;
    private List <Anuncio> lista;
    private Anuncio anuncioselecionado;
    private Usuario usuarioselecionado;
    private AnuncioSERVICE anuncioservice;
    private PerfilSERVICE perfilservice;
    private ImagensSERVICE imagenservice;
    private List<List<Imagens>> imgListvet ;
    private List<List<Imagens>> minListvet ;
    private List <Imagens> imgList;
    private List <Imagens> minList;
    private Perfil perfilbusca;
    private String cidade, bairro;
    private Double valor = 0.0;
    private List<String> listaestado;
    private String estadobusca;
    private List<timeline> time;
    private int linhaatual,linhaatualmini;
    private auxiliarFiltro auxfriltro;
    List<Imagens> listafoto;
        @PostConstruct
    private void inicializar() {
        anuncio = new Anuncio();
        perfil = new Perfil();
        perfilbusca = new Perfil();
        anuncioservice = new AnuncioSERVICE();
        perfilservice = new PerfilSERVICE();
        imagenservice = new ImagensSERVICE();
        auxfriltro = new auxiliarFiltro();
        lista = listarPorestado();
    
        listaestado = new ArrayList<>();
        listaestado.add("AC");
        listaestado.add("AL");
        listaestado.add("AP");
        listaestado.add("AM");
        listaestado.add("BA");
        listaestado.add("CE");
        listaestado.add("DF");
        listaestado.add("ES");
        listaestado.add("GO");
        listaestado.add("MA");
        listaestado.add("MT");
        listaestado.add("MS");
        listaestado.add("MG");
        listaestado.add("PA");
        listaestado.add("PB");
        listaestado.add("pPR");
        listaestado.add("PE");
        listaestado.add("PI");
        listaestado.add("RJ");
        listaestado.add("RN");
        listaestado.add("RS");
        listaestado.add("RO");
        listaestado.add("RR");
        listaestado.add("SC");
        listaestado.add("SP");
        listaestado.add("SE");
        listaestado.add("TO");
        
    }
    
    
    public void excluiranuncio(){
        try {
            anuncioservice.exlcuirAnuncio(anuncioselecionado);
            navega("/pages/admin/homeAdmin.xhtml");
        } catch (Exception e) {
        }
    }
    
    
     public void alteraranuncio(){
        try {
            anuncioservice.alterarAnuncio(anuncioselecionado);
            perfilservice.alterarPerfil(anuncioselecionado.getPerfil());
            anuncioservice.listarTodos();
            navega("/pages/anuncio/lista.xhtml");
        } catch (Exception e) {
        }
    }   
    
    public void montartimeline() {
        time = new ArrayList<>();
        timeline mont;
        lista = anuncioservice.listaPorEstado(estadobusca);
            minListvet= new ArrayList<List<Imagens>>();
            imgListvet= new ArrayList<List<Imagens>>();
        for (int i = 0; i < lista.size(); i++) {
            List<Imagens> listaimg = imagenservice.listarImagensAnuncio(this.lista.get(i));
            imgList = new ArrayList<>();
            minList = new ArrayList<>();
            mont    = new timeline();
            for (int x = 0; x < listaimg.size(); x++) {
                carregaimagem2(listaimg.get(x).getImagem());
                imgList.add(listaimg.get(x));
                minList.add(listaimg.get(x));

            }
            minListvet.add(minList);
            imgListvet.add(imgList);


            
        }
     
    }
        public void montartimelinefiltro() {
        time = new ArrayList<>();
        timeline mont;
        listarPerfil();
            minListvet= new ArrayList<List<Imagens>>();
            imgListvet= new ArrayList<List<Imagens>>();
        for (int i = 0; i < lista.size(); i++) {
            List<Imagens> listaimg = imagenservice.listarImagensAnuncio(this.lista.get(i));
            imgList = new ArrayList<>();
            minList = new ArrayList<>();
            mont    = new timeline();
            for (int x = 0; x < listaimg.size(); x++) {
                carregaimagem2(listaimg.get(x).getImagem());
                imgList.add(listaimg.get(x));
                minList.add(listaimg.get(x));

            }
            minListvet.add(minList);
            imgListvet.add(imgList);

            
        }

    }
    public StreamedContent carregaimg(int id, int foto) {
        /*
                System.out.println("valor raiz linha img " + linha);

                 FacesContext context = FacesContext.getCurrentInstance();
                 if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                     System.out.println("img new");
                     return new DefaultStreamedContent();
                 }
                 else{
                     String id = context.getExternalContext().getRequestParameterMap().get("id");
                     System.out.println("img img "+ linha + " / " +foto);
                     return  new DefaultStreamedContent(new ByteArrayInputStream(minListvet.get(linha).get(foto).getImagem()));
                 }
         */

        FacesContext context = FacesContext.getCurrentInstance();
        //if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
        //System.out.println("img new");
        //return new DefaultStreamedContent();
        if (id != 0) {
            List<Imagens> listaimg = imagenservice.listarImagensAnuncio(anuncio);
            for (int i = 0; i < 2; i++) {
                System.out.println("carregaimg " + id + " foto " + foto);
                Anuncio anuncio = anuncioservice.listarpoid(id);

                imgList = new ArrayList<>();
                minList = new ArrayList<>();

            }
            return new DefaultStreamedContent(new ByteArrayInputStream(listaimg.get(foto).getImagem()));
        } else if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            System.out.println("new img");
            return new DefaultStreamedContent();
        } else {
            return null;
        }

        //}
        //  else{
        //     return null;
        //String id = context.getExternalContext().getRequestParameterMap().get("id");
        //System.out.println("img img "+ linha + " / " +foto);
        //return  new DefaultStreamedContent(new ByteArrayInputStream(minListvet.get(linha).get(foto).getImagem()));
        // }
    }

    public StreamedContent carregaimgmin(int id, int foto) {
        /*  System.out.println("valor raiz linha min " + linha);
                 FacesContext context = FacesContext.getCurrentInstance();
                 if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                     System.out.println("min new ");
                     return new DefaultStreamedContent();
                 }
                 else{
                     String id = context.getExternalContext().getRequestParameterMap().get("id");
                     System.out.println("img min "+ linha + " / " +foto);
                     return  new DefaultStreamedContent(new ByteArrayInputStream(imgListvet.get(linha).get(foto).getImagem()));
                 }*/
        FacesContext context = FacesContext.getCurrentInstance();
        // if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
        //System.out.println("img new");
        //return new DefaultStreamedContent();
        if (id != 0) {
            List<Imagens> listaimg = imagenservice.listarImagensAnuncio(anuncio);
            for (int i = 0; i < 2; i++) {
                System.out.println("carregamin " + id + " foto " + foto);
                Anuncio anuncio = anuncioservice.listarpoid(id);

                imgList = new ArrayList<>();
                minList = new ArrayList<>();

            }
            return new DefaultStreamedContent(new ByteArrayInputStream(listaimg.get(foto).getImagem()));

        } else if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            System.out.println("new imgmin");
            return new DefaultStreamedContent();
        } else {
            return null;
        }
        //}
        // else{
        //   return null;
        //String id = context.getExternalContext().getRequestParameterMap().get("id");
        //System.out.println("img img "+ linha + " / " +foto);
        //return  new DefaultStreamedContent(new ByteArrayInputStream(minListvet.get(linha).get(foto).getImagem()));

        //}
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
    public void salvarAnuncio(){
        try {
            logado = JsfSessionContext.getInstance().getPessoaLogado();  
            perfil = logado.getPerfil();
            anuncio.setPerfil(perfilservice.salvarPerfilRetorno(perfil));
            anuncio.setUsuario(logado);
            anuncio.setNomeProprietario(logado.getNome());
            
            anuncioservice.salvarAnuncio(anuncio);
            navega("/pages/anuncio/lista.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public List<Anuncio> listar() {
        try {
            lista = anuncioservice.listarTodos();


        } catch (Exception e) {
        }

        return lista;
    }
        public StreamedContent carregaimagem2(byte[] b) {

        InputStream is = new ByteArrayInputStream(b);
        StreamedContent imagem3 = new DefaultStreamedContent(is);
        
        return imagem3;
    }

    public List<Anuncio> listarPorestado(){
        try {
            
            montartimeline();
            
            //lista = anuncioservice.listaPorEstado(estadobusca);
       
            navega("/pages/anuncio/lista.xhtml");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
   
    }
    
    public List<Anuncio> listarPorDono(){
        try {
            System.out.println("Entoru" + usuarioselecionado.getIdUsuario());
            lista = anuncioservice.listaPorDono(usuarioselecionado);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
   
    }
    public List<Anuncio> listarPorDonoLogado(){
        logado = JsfSessionContext.getInstance().getPessoaLogado();        
        try {
                    
            System.out.println("Entoru logado" + logado.getIdUsuario());
            lista = anuncioservice.listaPorDono(logado);
                    navega("/pages/anuncio/admin/meusanuncios.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void listarPerfil(){
        try {

        List<Anuncio> lista2 = new ArrayList<Anuncio>();
        int i = 0;
            for(Object[] arr : anuncioservice.listaPorPerfil(auxfriltro, valor,cidade, bairro,estadobusca)){
              
                lista2.add((Anuncio)arr[0]);
                  i += 1;
            }
           lista = lista2;
           lista2 = null;
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

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public List<Anuncio> getLista() {
        return lista;
    }

    public void setLista(List<Anuncio> lista) {
        this.lista = lista;
    }

    public Usuario getUsuarioselecionado() {
        return usuarioselecionado;
    }

    public void setUsuarioselecionado(Usuario usuarioselecionado) {
        this.usuarioselecionado = usuarioselecionado;
    }

    public Anuncio getAnuncioselecionado() {
        return anuncioselecionado;
    }

    public void setAnuncioselecionado(Anuncio anuncioselecionado) {
        this.anuncioselecionado = anuncioselecionado;
    }

    public Perfil getPerfilbusca() {
        return perfilbusca;
    }

    public void setPerfilbusca(Perfil perfilbusca) {
        this.perfilbusca = perfilbusca;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<String> getListaestado() {
        return listaestado;
    }

    public void setListaestado(List<String> listaestado) {
        this.listaestado = listaestado;
    }

    public String getEstadobusca() {
        return estadobusca;
    }

    public void setEstadobusca(String estadobusca) {
        this.estadobusca = estadobusca;
    }



    public List<timeline> getTime() {
        return time;
    }

    public void setTime(List<timeline> time) {
        this.time = time;
    }


    public auxiliarFiltro getAuxfriltro() {
        return auxfriltro;
    }

    public void setAuxfriltro(auxiliarFiltro auxfriltro) {
        this.auxfriltro = auxfriltro;
    }

    public List<List<Imagens>> getImgListvet() {
        return imgListvet;
    }

    public void setImgListvet(List<List<Imagens>> imgListvet) {
        this.imgListvet = imgListvet;
    }

    public List<List<Imagens>> getMinListvet() {
        return minListvet;
    }

    public void setMinListvet(List<List<Imagens>> minListvet) {
        this.minListvet = minListvet;
    }

    public List<Imagens> getImgList() {
        return imgList;
    }

    public void setImgList(List<Imagens> imgList) {
        this.imgList = imgList;
    }

    public List<Imagens> getMinList() {
        return minList;
    }

    public void setMinList(List<Imagens> minList) {
        this.minList = minList;
    }

    public List<Imagens> getListafoto() {
        return listafoto;
    }

    public void setListafoto(List<Imagens> listafoto) {
        this.listafoto = listafoto;
    }




    
    
    
    
}
