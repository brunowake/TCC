/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import entity.Anuncio;
import entity.Imagens;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import service.ImagensSERVICE;

/**
 *
 * @author bruno
 */

@SessionScoped
@ManagedBean
public class imagensController implements Serializable {
       private Imagens img;
       private Imagens imgselecionada;
       private StreamedContent imagem;
       private Anuncio anuncioselecionado;
       private ImagensSERVICE imagenservice;
       private List<String> listaImg;
       private Part file;
       private StreamedContent imagem2; 
       private List <StreamedContent> imgList;
       private List <StreamedContent> minList;
       private List <Imagens> lista;
       private List <StreamedContent> imgMiniaturaControle;
       
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
          
    public StreamedContent carregaimagem(Imagens img) {

        InputStream is = new ByteArrayInputStream(img.getImagem());
        imagem = new DefaultStreamedContent(is);
        
        return imagem;
    }
    public StreamedContent carregaimagem2(byte[] b) {

        InputStream is = new ByteArrayInputStream(b);
        StreamedContent imagem3 = new DefaultStreamedContent(is);
        
        return imagem3;
    }

    public void salvarImagem() {
        try {
            System.out.println("anuncio " + anuncioselecionado.getIdAnuncio());
            System.out.println(file.getContentType());
            InputStream is = file.getInputStream();
            img.setAnuncio(anuncioselecionado);
            img.setImagem(asString(is));
            imagenservice.salvarImg(img);
            navega("/pages/anuncio/admin/meusanuncios.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void listarPorAnuncio(){
        try {
            lista = imagenservice.listarImagensAnuncio(anuncioselecionado);
            for(int i = 0; i<lista.size();i++){
                lista.get(i).setImgprocessada(carregaimagem2(lista.get(i).getImagem()));

            }
        } catch (Exception e) {
        }
    }
    public void autalizaimg() throws IOException{
        anuncioselecionado.setIdAnuncio(1);
        listarimg(anuncioselecionado);
    }
    public void listarimg(Anuncio atual) throws IOException{
        
        List<Imagens> lista =  imagenservice.listarImagensAnuncio(atual);
        imgList = new ArrayList<>();
        minList = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
                    //BufferedImage img = ImageIO.read(new ByteArrayInputStream(lista.get(i).getImagem()));
                    
                    imgList.add(carregaimagem2(lista.get(i).getImagem()));
                    minList.add(carregaimagem2(lista.get(i).getImagem()));
                    //ImageIO.write(img, "JPG", new File("C:\\Users\\BrunoWake\\Documents\\tccatualizado\\moarida2\\web\\resources\\imagem\\imagem"+i+".jpg"));
                    System.out.println("teste? " + carregaimagem2(lista.get(i).getImagem()));
        }        
        System.out.println("size " + imgList.size());
    }
      @PostConstruct
      private void inicializar() {
          try { anuncioselecionado = new Anuncio();
          imagenservice = new ImagensSERVICE();
          img = new Imagens();
          //anuncioselecionado.setIdAnuncio(1);
          
         // listaImg = listaImg();
          //listarimg();
          //imagem2 = carregaimagem(imagenservice.listarImagensAnuncio(anuncioselecionado).get(5));
              
          } catch (Exception e) {
          }

      }
      

    
    public List<String> listaImg (){
        List<Imagens> lista;
        List<String> listaimg = null;
        lista =  imagenservice.listarImagensAnuncio(anuncioselecionado);
        for (int i =0; i< lista.size();i++){
            listaimg = new ArrayList<String>();
            listaimg.add(carregaimagem(lista.get(i)).toString());
            
        }
        
        
        return listaimg;
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
    public void deletar(){
        try { imagenservice.exlcuirImg(imgselecionada);
            navega("/pages/anuncio/admin/meusanuncios.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    

    public Anuncio getAnuncioselecionado() {
        return anuncioselecionado;
    }

    public void setAnuncioselecionado(Anuncio anuncioselecionado) {
        this.anuncioselecionado = anuncioselecionado;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public List<String> getListaImg() {
        return listaImg;
    }

    public void setListaImg(List<String> listaImg) {
        this.listaImg = listaImg;
    }

    public StreamedContent getImagem2() {
        return imagem2;
    }

    public void setImagem2(StreamedContent imagem2) {
        this.imagem2 = imagem2;
    }

    public List<StreamedContent> getImgList() {
        return imgList;
    }

    public void setImgList(List<StreamedContent> imgList) {
        this.imgList = imgList;
    }

    public List<StreamedContent> getMinList() {
        return minList;
    }

    public void setMinList(List<StreamedContent> minList) {
        this.minList = minList;
    }

    public List<Imagens> getLista() {
        return lista;
    }

    public void setLista(List<Imagens> lista) {
        this.lista = lista;
    }

    public Imagens getImgselecionada() {
        return imgselecionada;
    }

    public void setImgselecionada(Imagens imgselecionada) {
        this.imgselecionada = imgselecionada;
    }

    public List<StreamedContent> getImgMiniaturaControle() {
        return imgMiniaturaControle;
    }

    public void setImgMiniaturaControle(List<StreamedContent> imgMiniaturaControle) {
        this.imgMiniaturaControle = imgMiniaturaControle;
    }

    public Imagens getImg() {
        return img;
    }

    public void setImg(Imagens img) {
        this.img = img;
    }


      
    
}
