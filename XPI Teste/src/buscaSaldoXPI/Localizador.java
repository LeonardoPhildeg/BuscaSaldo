package buscaSaldoXPI;

import org.openqa.selenium.By;

public class Localizador {
	private By by;
	private String locDescricao;

	private Localizador(By by, String descricao) {
		this.by = by;
		this.locDescricao = descricao;
	}

	public By getBy() {
		return this.by;
	}

	public static Localizador id(String id) {
		return xpath("//*[@id='" + id + "']");
	}

	public static Localizador xpath(String xpath) {
		return new Localizador(By.xpath(xpath), xpath);
	}

	public String getDescricaoDoLocalizador() {
		return this.locDescricao;
	}

}