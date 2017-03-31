package buscaSaldoXPI.pagina;

import buscaSaldoXPI.Driver;
import buscaSaldoXPI.Localizador;
import buscaSaldoXPI.componente.Botao;
import buscaSaldoXPI.componente.CampoTexto;

public class LoginPag {

	public static CampoTexto campoLogin() {
		return new CampoTexto(Localizador.id("txtLogin"));
	}

	public static Botao botaoLogin() {
		return new Botao(Localizador.id("btnOkLogin"));
	}

	public static void acessar() {
		Driver.getInstance().get("https://portal.xpi.com.br/default.aspx");
	}
}
