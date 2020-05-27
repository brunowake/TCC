/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlller;

import entity.Anuncio;
import java.util.List;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author bruno
 */
public class timeline {
    private Anuncio anuncio;

    private List<StreamedContent> imgList;
    private List<StreamedContent> minList;

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
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
    
            
            
}
