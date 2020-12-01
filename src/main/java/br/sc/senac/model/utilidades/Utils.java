package br.sc.senac.model.utilidades;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Utils {

	/**
	 * @param cpf, texto com 11 digitos numéricos para formatar
	 * @return texto com a mascara de cpf
	 * */
	public static String formatarCpf(String cpf) {
		String formattedCpf = null;
		if (cpf != null) {
			formattedCpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		}
		return formattedCpf;
	}
	
	/**
	 * @param cpf, texto com a mascara de cpf (###.###.###-##)
	 * @return texto não formatado com 11 digitos numéricos
	 * */
	public static String desformatarCpf(String cpf) {
		String formattedCpf = null;
		if (cpf != null) {
			formattedCpf = cpf.replaceAll("(\\d{3})\\.(\\d{3})\\.(\\d{3})\\-(\\d{2})", "$1$2$3$4");
		}
		return formattedCpf;
	}
	
	/**
	 * @param dataTexto, texto com a mascara de data (dd/MM/yyyy)
	 * @return data em LocalDate
	 * @throws NumberFormatException Se o texto conter digitos não numéricos
	 * @throws ArrayIndexOutOfBoundsException Se tiver um ou menos '/' no texto
	 * @throws DateTimeException Se a data extraída não for válida
	 * */
	public static LocalDate gerarLocalDateDeString(String dataTexto)
			throws NumberFormatException, ArrayIndexOutOfBoundsException, DateTimeException {
		String[] partesDataS = dataTexto.split("/");
		Integer[] partesDataI = {Integer.parseInt(partesDataS[0]), Integer.parseInt(partesDataS[1]), Integer.parseInt(partesDataS[2])};
		LocalDate data = LocalDate.of(partesDataI[2],partesDataI[1],partesDataI[0]);
		return data;
	}
	
	/**
	 * @param texto para ser normalizado
	 * @return texto com o primeiro caracter de cada palavra em maiúsculo e o resto em minúsculo
	 * */
	public static String getNormalizedText(String texto) {
		StringBuffer sb = new StringBuffer();
		
		String[] palavras = texto != null ? texto.trim().split(" ") : null;
		for(String palavra : palavras) {
			int i = 0;
			for(Character ch : palavra.toCharArray() ) {
				if(i++ == 0) {
					sb.append(Character.toUpperCase(ch));
				} else {
					sb.append(Character.toLowerCase(ch));
				}
			}
			sb.append(" ");
		}
		return sb.toString();
	}

	public static Double stringToDouble(String text) {
		Double valor = null;
		try {
			valor = Double.parseDouble(text);
		} catch (Exception e) { }
		return valor;
	}
	
}
