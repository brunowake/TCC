/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import entity.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import service.PerfilSERVICE;
import service.UsuarioSERVICE;
import entity.Perfil;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.DatePicker;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sun.nio.ch.IOUtil;
import util.JsfSessionContext;

/**
 *
 * @author bruno
 */
@SessionScoped
@ManagedBean
public class usuarioController implements Serializable {

    private Usuario usuario;
    private Usuario usuarioselecionado;
    private UsuarioSERVICE usuarioservice;
    private Perfil perfil;
    private PerfilSERVICE perfilservice;
    private Part file;
    private byte[] array;
    private String fileContent;
     private List<Usuario> lista;
    private String nomebusca;
    private String cpf;
    private StreamedContent imagem;
    private List<String> pt;
    private int listatamanho;
    private String senha1,senha2,senha3,senha4;

    private OutputStream  img;
    private Usuario logado, altera;
    private String email ;
    private String senha ;

    public static byte[] asString(InputStream is) throws IOException {
        
        
        int nRead;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[16777215];
        while ((nRead = is.read(bytes, 0, bytes.length)) != -1) {
            baos.write(bytes, 0, nRead);
        }
        baos.flush();
        bytes = baos.toByteArray();
        return bytes;
    }
    @PostConstruct
    private void inicializar() {
        usuarioservice = new UsuarioSERVICE();
        perfilservice = new PerfilSERVICE();
        usuario = new Usuario();
        perfil = new Perfil();
       // if (logado != null) {
          //  logado.setSexo(logado.getSexo());
        //    System.out.println("Logado sexo usuariocontroller: " + logado.getSexo());
      //      JsfSessionContext.getInstance().setAttribute("Logado", usuario);
      //  }
        lista = listar();
        //validaLogin();
        usuarioselecionado = new Usuario();
        //logado =  JsfSessionContext.getInstance().getPessoaLogado();
        //logado = null;
        

        // listar();
    }
    public void carregaimagem() throws IOException {
        if (logado != null) {
            System.out.println("teste entrou 1234");
             InputStream is = new ByteArrayInputStream(logado.getFoto());
             imagem = new DefaultStreamedContent(is);
        }
        else{
            System.out.println("teste entrou");
        }
      
       
    }


    public void salvarUsuario() {
        
        try { 
            

            System.out.println("data:" + nomebusca);
            System.out.println("entrou");
            InputStream is = file.getInputStream();
            usuario.setPerfil(perfilservice.salvarPerfilRetorno(perfil));
            usuario.setFoto(asString(is));
            
            
            System.out.println("data:" + usuario.getDataNascimento());
            usuario.setPermissao(false);
            usuarioservice.salvarUsuario(usuario);
            perfil = new Perfil();
            navega("/login.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void alterarsenha(){
        System.out.println("entrou1");
        if (senha1.equals( logado.getSenha())) {
            System.out.println("entrou2");
            if (senha2.equals(senha3)) {
                System.out.println("entrou3");
                logado.setSenha(senha2);
                usuarioservice.alterarUsuario(logado);
                senha1 =null;
                senha2 =null;
                senha3 =null;
                listar();
               navega("/pages/admin/homeAdmin.xhtml");
            }
        }
    }
    
    public void alterarUsuariologado(){
        try {       
            System.out.println("entrou logado");
            System.out.println("logado nome " + altera.getNome());
        usuarioservice.alterarUsuario(logado);
        perfilservice.alterarPerfil(logado.getPerfil());
                listar();
        navega("/pages/admin/homeAdmin.xhtml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void alterarUsuario(){
        this.perfil = perfilservice.buscarID(usuarioselecionado.getPerfil(), usuarioselecionado.getPerfil().getIdPerfil());
       
        usuarioservice.alterarUsuario(usuarioselecionado);
        perfilservice.alterarPerfil(usuarioselecionado.getPerfil());
                listar();
        navega("/pages/admin/homeAdmin.xhtml");
    }
    
    public void deletarUsuario(){
        usuarioservice.excluirUsuairo(usuarioselecionado);
        listar();
        navega("/pages/admin/homeAdmin.xhtml");
    }

    public List<Usuario> listar() {
        try {
            lista = usuarioservice.listarTodos();

            listatamanho = lista.size();
            //teste = lista.get(19).getFoto().toString();
            //InputStream is = new ByteArrayInputStream(lista.get(21).getFoto());
            //imagem = new DefaultStreamedContent(is);
            
        } catch (Exception e) {
        }

        return lista;
    }
    
    public List<Usuario> listarnome() {
        try {
            lista = usuarioservice.listarnome(nomebusca);
            nomebusca = "";

        } catch (Exception e) {
        }

        return lista;
    }
    public List<Usuario> listarcpf() {
        try {
            lista = usuarioservice.listarcpf(cpf);
           
            cpf = null;

        } catch (Exception e) {
        }

        return lista;
    }


 
    
    public boolean validaLogin() {
        boolean val;
        if (logado != null) {
            val = true;
        } else {
            val = false;
            navega("/login.xhtml");
        }
        return val;
    }
    public boolean validaadm(){
        boolean val;
        if (logado.isPermissao() == true) {
            val = true;
        } else {
            val = false;
            navega("/login.xhtml");
        }
        return val;
    }
    public void logout(){
        System.out.println("teste");
        this.logado =  null;
        navega("/login.xhtml");
    }

    public void login() throws IOException {
        Usuario aux = usuarioservice.userlogin(email);
        altera = aux;
        if (aux != null) {
            System.out.println("valor sexo login: "+ aux.getSexo());
            JsfSessionContext.getInstance().setAttribute("Logado", aux);
        }
        if (aux.getEmail().equals(email) && aux.getSenha().equals(senha)) {
            System.out.println("senha correta");
            logado = aux;

            carregaimagem();
                        System.out.println(imagem);
                        
            if (logado.isPermissao() == true) {
                navega("/pages/admin/homeAdmin.xhtml");
            } else {
                navega("/pages/anuncio/lista.xhtml");
            }

            aux = new Usuario();
        } else {
            FacesContext.getCurrentInstance().addMessage("codigoMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,"E-mail ou senha incorretos", ""));

        }
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

    public Usuario getUsuario() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioSERVICE getUsuarioservice() {
        return usuarioservice;
    }

    public void setUsuarioservice(UsuarioSERVICE usuarioservice) {
        this.usuarioservice = usuarioservice;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public int getListatamanho() {
        return listatamanho;
    }

    public List<Usuario> getLista() {
        return lista;
    }

  

    public StreamedContent getImagem() {
        return imagem;
    }

    public Usuario getLogado() {
        return logado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioselecionado() {
        return usuarioselecionado;
    }

    public void setUsuarioselecionado(Usuario usuarioselecionado) {
        this.usuarioselecionado = usuarioselecionado;
    }

    public String getNomebusca() {
        return nomebusca;
    }

    public void setNomebusca(String nomebusca) {
        this.nomebusca = nomebusca;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public OutputStream  getImg() {
        return img;
    }

    public void setImg(OutputStream  img) {
        this.img = img;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }

    public String getSenha3() {
        return senha3;
    }

    public void setSenha3(String senha3) {
        this.senha3 = senha3;
    }

    public Usuario getAltera() {
        return altera;
    }

    public void setAltera(Usuario altera) {
        this.altera = altera;
    }

    public String getSenha4() {
        return senha4;
    }

    public void setSenha4(String senha4) {
        this.senha4 = senha4;
    }

    
    
    
}
