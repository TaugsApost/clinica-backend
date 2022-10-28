package br.com.clinica.utils;

public class Utils {

	public static String stringLike(String s) {
		String palavra = "";
		if (!(s == null)) {
			palavra = s;
		}
		return "%" + palavra.toUpperCase() + "%";
	}

}
