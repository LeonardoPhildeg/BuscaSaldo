package buscaSaldoXPI;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcellTeste {

	private XSSFWorkbook wb;
	private String path = "C:\\Users\\Vitor\\Downloads\\Saldos_Diarios.xlsx";
	private Calendar calendario = Calendar.getInstance();
	private int dia;
	private double vAdicional = 0;
	BuscaSaldoTeste b = new BuscaSaldoTeste();
	private File src = new File(path);

	public ExcellTeste() throws IOException {
		//File src = new File(path);
		if (!src.exists()) {
			this.wb = new XSSFWorkbook();
			this.wb.createSheet();
		} else {
			FileInputStream fis = new FileInputStream(src);
			this.wb = new XSSFWorkbook(fis);
		}

	}

	public static boolean isCellNotEmpty(int i, int j, XSSFSheet sheet1) {

		if (sheet1.getRow(i) != null) {
			if (sheet1.getRow(i).getCell(j) == null || sheet1.getRow(i).getCell(j).getStringCellValue().trim().equals("")) {

				return false;
			}
		} else
			return false;
		return true;
	}

	@SuppressWarnings("deprecation")
	public void escrever(String saldo, String saldoHistorico, String saldoHistoricoAntigo) throws IOException {

		XSSFSheet sheet1 = this.wb.getSheetAt(0);

		Cell cellF1 = sheet1.getRow(0).createCell(5);
		Cell cellF2 = sheet1.getRow(1).createCell(5);
		cellF2.setCellType(CellType.FORMULA);
		Cell cellG1 = sheet1.getRow(0).createCell(6);
		Cell cellG2 = sheet1.getRow(1).createCell(6);
		cellG2.setCellType(CellType.FORMULA);
		Cell cellH1 = sheet1.getRow(0).createCell(7);
		Cell cellH2 = sheet1.getRow(1).getCell(7);
		cellH2.setCellType(CellType.NUMERIC);
		Cell cellI1 = sheet1.getRow(0).createCell(8);
		Cell cellI2 = sheet1.getRow(1).getCell(8);
		cellI2.setCellType(CellType.NUMERIC);
		Cell cellJ1 = sheet1.getRow(0).createCell(9);
		Cell cellJ2 = sheet1.getRow(1).createCell(9);
		cellJ2.setCellType(CellType.FORMULA);
		

		boolean cheio = true;
		int i = 0, j = 1, l = 0;
		while (cheio) {
			cheio = isCellNotEmpty(i, j, sheet1);
			if (cheio) {
				i++;
			} else
				l = i;
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		dia = calendario.get(Calendar.DAY_OF_WEEK);
		sheet1.createRow(l).createCell(1).setCellValue(dateFormat.format(date));//B: Data da pesquisa

		switch (dia) {
		case 1:
			sheet1.getRow(l).createCell(0).setCellValue("Domingo");
			break;
		//		case 2: sheet1.getRow(l).createCell(0).setCellValue("Segunda");
		//		break;
		//		case 3: sheet1.getRow(l).createCell(0).setCellValue("Terça");
		//		break;
		//		case 4: sheet1.getRow(l).createCell(0).setCellValue("Quarta");
		//		break;
		//		case 5: sheet1.getRow(l).createCell(0).setCellValue("Quinta");
		//		break;
		//		case 6: sheet1.getRow(l).createCell(0).setCellValue("Sexta");
		//		break;
		case 7:
			sheet1.getRow(l).createCell(0).setCellValue("Sábado");
			break;
		}

		sheet1.getRow(l).createCell(2).setCellType(CellType.NUMERIC);
		if (l > 0) {
			sheet1.getRow(l).createCell(3).setCellType(CellType.FORMULA);
			sheet1.getRow(l).getCell(3).setCellFormula("C" + (l + 1) + "-C" + l); //D: Diferença entre saldo anterior e atual
		}

		int ultimaCelulaMaisUm = sheet1.getLastRowNum() + 1;
		String formula = "(";

		for (int p = 0; p <= ultimaCelulaMaisUm; p++) {
			if (p > 0) {
				formula = formula + "+ D" + p;
			} else
				formula = formula + "D" + (p + 1);

		}

		//Labels
		cellF1.setCellValue("Média diária");
		CellUtil.setAlignment(cellF1, wb, CellStyle.ALIGN_CENTER);

		cellG1.setCellValue("Média Mensal");
		CellUtil.setAlignment(cellG1, wb, CellStyle.ALIGN_CENTER);

		cellH1.setCellValue("Valor adicional (Mês)");
		CellUtil.setAlignment(cellH1, wb, CellStyle.ALIGN_CENTER);

		cellF2.setCellFormula(formula + ")/(B" + ultimaCelulaMaisUm + " - B1)");//F: Média do lucro diário
		CellUtil.setAlignment(cellF2, wb, CellStyle.ALIGN_CENTER);

		// /Labels

		saldoHistorico = saldoHistorico.replace(".", "").replace(",", ".");
		double dSaldoHistorico = Double.parseDouble(saldoHistorico);

		saldoHistoricoAntigo = saldoHistoricoAntigo.replace(".", "").replace(",", ".");
		double dSaldoHistoricoAntigo = Double.parseDouble(saldoHistoricoAntigo);

		
		//vAdicional = Integer.parseInt(cellH2.getStringCellValue());
		vAdicional = cellH2.getNumericCellValue();
		YearMonth yearMonthObject = YearMonth.of(Calendar.YEAR, Calendar.MONTH);
		
		System.out.println("Saldo atual: "+dSaldoHistorico);
		System.out.println("Saldo antigo: "+dSaldoHistoricoAntigo);
		System.out.println("vAdicional: "+vAdicional);
		cellG2.setCellFormula("" + (dSaldoHistorico - dSaldoHistoricoAntigo - vAdicional) / yearMonthObject.lengthOfMonth());//G: Histórico mës atual
		CellUtil.setAlignment(sheet1.getRow(1).getCell(6), wb, CellStyle.ALIGN_CENTER);
		
		cellI1.setCellValue("Total adicionado");
		
		
		cellJ1.setCellValue("Total arrecadado");
		cellJ2.setCellFormula(""+(dSaldoHistorico-7400-cellI2.getNumericCellValue()));

		saldo = saldo.replace(".", "").replace(",", ".");

		double dSaldo = Double.parseDouble(saldo);

		sheet1.getRow(l).getCell(2).setCellValue(dSaldo); //C: Saldo do dia
		FileOutputStream fout = new FileOutputStream(path);
		wb.write(fout);
		fout.close();
		wb.close();
		System.exit(0);
	}

	public String ultimaData() {
		XSSFSheet sheet1 = this.wb.getSheetAt(0);
		return sheet1.getRow(sheet1.getLastRowNum()).getCell(1).getStringCellValue();

	}

	public void abrirArquivo() throws IOException {
		Desktop dt = Desktop.getDesktop();
		dt.open(new File(path));

	}

}
