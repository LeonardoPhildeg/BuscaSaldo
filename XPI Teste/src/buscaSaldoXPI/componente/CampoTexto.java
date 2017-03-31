package buscaSaldoXPI.componente;

import buscaSaldoXPI.Driver;
import buscaSaldoXPI.Localizador;

public class CampoTexto extends Componente {

	public CampoTexto(Localizador localizador) {
		super(localizador);
	}

	public void preencher(String texto) {
		Driver.getElementoComEspera(this.localizador).sendKeys(texto);
	}

}
