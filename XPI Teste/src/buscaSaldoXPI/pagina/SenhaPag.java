package buscaSaldoXPI.pagina;

import buscaSaldoXPI.Localizador;
import buscaSaldoXPI.componente.Botao;

public class SenhaPag {
	
	public static Botao botaoEntrar(){
		return new Botao(Localizador.id("btnEntrar"));
	}

}
