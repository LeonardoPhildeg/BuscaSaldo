package buscaSaldoXPI;
import java.io.IOException;
import java.text.ParseException;

public class BuscaSaldoTesteMain {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {		
		BuscaSaldoTeste b = new BuscaSaldoTeste();
		ExcellTeste e = new ExcellTeste();
		
		b.mataOsProcessoTudo();
		
		b.setSaldo();
		e.escrever(b.getSaldo(), b.getSaldoHistorico(), b.getSaldoHistoricoAntigo());
		e.abrirArquivo();
	}

}


