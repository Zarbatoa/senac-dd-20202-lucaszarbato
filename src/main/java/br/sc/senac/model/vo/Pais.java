package br.sc.senac.model.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import br.sc.senac.model.utilidades.Constantes;

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
	
	public static List<Pais> createCountryListGeral() {
	    String[] countryCodes = Locale.getISOCountries();
	    List<Pais> listCountry = new ArrayList<Pais>();
	 
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale locale = new Locale("", countryCodes[i]);
	        String code = locale.getCountry();
	        String name = locale.getDisplayCountry();
	 
	        listCountry.add(new Pais(code, name));
	    }
	 
	    listCountry.sort(null);
	 
	    listCountry.add(0, Constantes.OPCAO_PAISES_TODOS);
	    
	    return listCountry;
	}
	
	public static Pais[] createCountryListEdicaoCadastro() {
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
