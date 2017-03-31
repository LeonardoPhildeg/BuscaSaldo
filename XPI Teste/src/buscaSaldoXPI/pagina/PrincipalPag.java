package buscaSaldoXPI.pagina;

import buscaSaldoXPI.Localizador;
import buscaSaldoXPI.componente.Botao;
import buscaSaldoXPI.componente.Label;

public class PrincipalPag {
	
	public static Label labelPatrimonioTotal() {
		return new Label(Localizador.id("cphMinhaConta_wcDefault1_lblPatrimonioTotal"));
	}
	
	public static Botao botaoPosicaoConsolidada(){
		return new Botao(Localizador.xpath("//*[contains(@class, 'DV_BotoesAplicacao')]"));
	}
}
