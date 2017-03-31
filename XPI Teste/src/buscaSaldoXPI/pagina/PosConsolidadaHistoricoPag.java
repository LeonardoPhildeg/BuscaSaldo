package buscaSaldoXPI.pagina;

import buscaSaldoXPI.Localizador;
import buscaSaldoXPI.componente.Botao;
import buscaSaldoXPI.componente.ComboBox;
import buscaSaldoXPI.componente.Label;

public class PosConsolidadaHistoricoPag {
	
	public static Botao botaoHistorico() {
		return new Botao(Localizador.id("stepHist"));
	}
	
	public static Botao botaoGerarHistorico(){
		return new Botao(Localizador.xpath("//*[contains(@class, 'dvCombo form-inline-group')]//button"));
	}
	
	public static Label labelHistoricoAtual() {
		return new Label(Localizador.xpath("//*[contains(@class, 'dvSaldo')]"));
	}

	public static ComboBox ComboBoxdata(){
		return new ComboBox(Localizador.id("Data"));
	}
}
