package util;

import entity.Usuario;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



public class JsfSessionContext {
	private static JsfSessionContext instance; 

	public static JsfSessionContext getInstance(){ 
		if (instance == null){ 
			instance = new JsfSessionContext(); 
		} 
		return instance; 
	} 

	private JsfSessionContext(){ 

	} 

	private ExternalContext currentExternalContext(){ 

		if (FacesContext.getCurrentInstance() == null){ 
			throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP"); 
		} else { 
			return FacesContext.getCurrentInstance().getExternalContext(); 
		} 
	} 

	public Usuario getPessoaLogado(){ 
		return (Usuario) getAttribute("Logado"); 
	} 

	public void setPessoaLogado(Usuario pessoa){ 
		setAttribute("Logado", pessoa); 
	} 

	public void encerrarSessao(){ 
		currentExternalContext().invalidateSession(); 
	} 

	public Object getAttribute(String nome){ 
		return currentExternalContext().getSessionMap().get(nome); 
	} 

	public void setAttribute(String nome, Object valor){ 
		currentExternalContext().getSessionMap().put(nome, valor); 
	} 
	
	public Object getContext(){
		return FacesContext.getCurrentInstance().getExternalContext().getContext();
	}
}
