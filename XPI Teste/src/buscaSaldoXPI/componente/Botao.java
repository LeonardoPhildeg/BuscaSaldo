package buscaSaldoXPI.componente;


import buscaSaldoXPI.Driver;
import buscaSaldoXPI.Localizador;

public class Botao extends Componente {
	
	
	public Botao(Localizador localizador){
		super(localizador);
	}
	
	public void clicar(){
		Driver.getElementoComEspera(this.localizador).click();
	}
	
}
