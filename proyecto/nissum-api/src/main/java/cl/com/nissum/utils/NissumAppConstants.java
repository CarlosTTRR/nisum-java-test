package cl.com.nissum.utils;

public class NissumAppConstants {

	//Patterns
	// (aaaaaaa@dominio.cl)
	public static final String REGEX_EMAIL_VALIDATION = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[c,l,C,L]{2})$";

	// (Una Mayuscula, letras minúsculas, y dos numeros)
	public static final String REGEX_PASSWORD_VALIDATION = 
			"^(?!(?:[^0-9]*[0-9]){3})(?!(?:[^A-Z]*[A-Z]){2})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,16}$";
	

	//Error messages
	//Http 500
	public static final String USER_EMAIL_EXISTS_ERROR = "El correo ingresado ya está registrado.";
	
	public static final String USER_EMAIL_FORMAT = "El correo no cumple con el formato 'aaaaa@domain.cl'.";
	
	public static final String USER_PASSWORD_FORMAT = "El password debe cumplir con formato \nDos números, Una mayúscula y minúsculas.";
	
	public static final String ERROR_GENERAL = "Ocurrió un Error General en el Sistema.";
	
	
}
