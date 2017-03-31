package buscaSaldoXPI.pagina;

import buscaSaldoXPI.Localizador;
import buscaSaldoXPI.componente.Botao;

public class UsuarioPag {
	
	public static Botao botaoUsuario(){
		return new Botao(Localizador.id("lnkNomeUsuario"));
	}

}
