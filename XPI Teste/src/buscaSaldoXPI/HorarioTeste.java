package buscaSaldoXPI;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HorarioTeste {

	private Calendar amanha = Calendar.getInstance();
	private Calendar hoje = Calendar.getInstance();
	private long sleepTime;

	public void verificarHora() throws IOException, InterruptedException,ParseException {
		System.out.println("Começando");
		Calendar cal = Calendar.getInstance();
		BuscaSaldoTeste b = new BuscaSaldoTeste();
		ExcellTeste e = new ExcellTeste();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date ultimaData = formatter.parse(e.ultimaData());

		amanha.add(Calendar.DAY_OF_MONTH, 1);
		amanha.set(Calendar.HOUR, 9);
		amanha.set(Calendar.MINUTE, 0);
		amanha.set(Calendar.SECOND, 0);
		amanha.set(Calendar.MILLISECOND, 0);
		hoje.set(Calendar.HOUR, 0);
		hoje.set(Calendar.MINUTE, 0);
		hoje.set(Calendar.SECOND, 0);
		hoje.set(Calendar.MILLISECOND, 0);
		sleepTime = amanha.getTimeInMillis() - cal.getTimeInMillis();
		
		
		if (hoje.getTime().compareTo(ultimaData)==1 && hoje.getTime().before(amanha.getTime())) {
			b.setSaldo();
			e.escrever(b.getSaldo(), b.getSaldoHistorico(), b.getSaldoHistoricoAntigo());
			Thread.sleep(sleepTime);
		}
		Thread.sleep(5000);
		this.verificarHora();
		
	}
}
