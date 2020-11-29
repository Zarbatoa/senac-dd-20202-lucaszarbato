package br.sc.senac.model.vo;

import java.util.Arrays;
import java.util.Locale;

public class Pais implements Comparable<Pais> {

	private String codigo;
	private String nome;
	
	public Pais(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Pais outroPais) {
		return this.nome.compareTo(outroPais.getNome());
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
	
	public static Pais[] createCountryList() {
	    String[] countryCodes = Locale.getISOCountries();
	    Pais[] listCountry = new Pais[countryCodes.length];
	 
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale locale = new Locale("", countryCodes[i]);
	        String code = locale.getCountry();
	        String name = locale.getDisplayCountry();
	 
	        listCountry[i] = new Pais(code, name);
	    }
	 
	    Arrays.sort(listCountry);
	 
	    return listCountry;
	}
	
}
