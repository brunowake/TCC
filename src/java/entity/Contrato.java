package entity;
// Generated 01/11/2017 18:21:14 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Contrato generated by hbm2java
 */
public class Contrato  implements java.io.Serializable {


     private Integer idContrato;
     private Usuario usuario;
     private Date dataContratacao;
     private Date dataTerminoContrato;
     private double valorPago;

    public Contrato() {
    }

    public Contrato(Usuario usuario, Date dataContratacao, Date dataTerminoContrato, double valorPago) {
       this.usuario = usuario;
       this.dataContratacao = dataContratacao;
       this.dataTerminoContrato = dataTerminoContrato;
       this.valorPago = valorPago;
    }
   
    public Integer getIdContrato() {
        return this.idContrato;
    }
    
    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getDataContratacao() {
        return this.dataContratacao;
    }
    
    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
    public Date getDataTerminoContrato() {
        return this.dataTerminoContrato;
    }
    
    public void setDataTerminoContrato(Date dataTerminoContrato) {
        this.dataTerminoContrato = dataTerminoContrato;
    }
    public double getValorPago() {
        return this.valorPago;
    }
    
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }




}


