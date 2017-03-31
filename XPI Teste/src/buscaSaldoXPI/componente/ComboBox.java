package buscaSaldoXPI.componente;

import org.openqa.selenium.support.ui.Select;

import buscaSaldoXPI.Driver;
import buscaSaldoXPI.Localizador;

public class ComboBox extends Componente{

	public ComboBox(Localizador localizador) {
		super(localizador);
	}
	
	public void selecionarOpcao(int posicao){
		new Select(Driver.getElementoComEspera(localizador)).selectByIndex(posicao-1);
	}
	
	
}
