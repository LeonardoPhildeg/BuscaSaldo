package buscaSaldoXPI;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import buscaSaldoXPI.pagina.LoginPag;
import buscaSaldoXPI.pagina.PosConsolidadaHistoricoPag;
import buscaSaldoXPI.pagina.PrincipalPag;
import buscaSaldoXPI.pagina.SenhaPag;
import buscaSaldoXPI.pagina.UsuarioPag;

public class BuscaSaldoTeste {
	private String saldo = "000";
	private String saldoHistorico = null;
	private String saldoHistoricoAntigo = null;
	private String senha = "DIGITA A SENHA NESSA STRING";
	private String IdUsuario = "DIGITA O ID NESSA STRING";

	public void setSaldo() throws InterruptedException {
		//WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10000);

		//WebDriver driver = Driver.getInstance();
		
		LoginPag.acessar();

		LoginPag.campoLogin().preencher(IdUsuario);

		LoginPag.botaoLogin().clicar();
		
		Driver.delay(3000);
		UsuarioPag.botaoUsuario().clicar();
		
		Driver.delay(2000);
		
		clicarBotoesSenha();

		Driver.delay(1000);
		SenhaPag.botaoEntrar().clicar();
		
		Driver.delay(1000);
		saldo = PrincipalPag.labelPatrimonioTotal().getTexto();
		System.out.println(saldo);
		
		Driver.delay(3000);
		PrincipalPag.botaoPosicaoConsolidada().clicar();
		Driver.delay(5000);

		PosConsolidadaHistoricoPag.botaoHistorico().clicar();
		Driver.delay(2000);

		PosConsolidadaHistoricoPag.botaoGerarHistorico().clicar();
		Driver.delay(1000);

		saldoHistorico = PosConsolidadaHistoricoPag.labelHistoricoAtual().getTexto().split(" ")[3];
		System.out.println("Saldo atual: " + saldoHistorico);
		Thread.sleep(1000);
		
		PosConsolidadaHistoricoPag.ComboBoxdata().selecionarOpcao(2);
		Thread.sleep(1000);

		PosConsolidadaHistoricoPag.botaoGerarHistorico().clicar();
		Driver.delay(2000);

//		saldoHistoricoAntigo = driver.findElement(By.xpath("//*[contains(@class, 'dvSaldo')]")).getText().split(" ")[3];
		saldoHistoricoAntigo = PosConsolidadaHistoricoPag.labelHistoricoAtual().getTexto().split(" ")[3];
		System.out.println("Saldo anterior: " + saldoHistoricoAntigo);

		mataOsProcessoTudo();
	}

	public String getSaldo() {
		return this.saldo;
	}

	public String getSaldoHistorico() {
		return this.saldoHistorico;
	}

	public String getSaldoHistoricoAntigo() {
		return this.saldoHistoricoAntigo;
	}

	private void clicarBotoesSenha() {
		List<WebElement> list = Driver.getInstance().findElements(By.xpath("//*[contains(@class, 'btn btn-small secundario_cinza')]"));
		for (int i = 0; i < senha.length(); i++) {
			for (WebElement web : list) {
				if (web.getText().contains("" + senha.charAt(i))) {
					web.click();
					break;
				}
			}
		}
	}
	
	public void mataOsProcessoTudo(){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
