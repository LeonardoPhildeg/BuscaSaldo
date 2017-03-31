package buscaSaldoXPI.componente;

import buscaSaldoXPI.Driver;
import buscaSaldoXPI.Localizador;

public class Componente {
	protected Localizador localizador;
	
	public Componente(Localizador localizador){
		this.localizador = localizador;
	}
	
	public String getTexto(){
		return Driver.getElementoComEspera(this.localizador).getText();
	}
	
}
