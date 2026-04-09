package com.briomax.briobpm.business.helpers;

import org.springframework.stereotype.Service;

import com.briomax.briobpm.business.helpers.base.IAppMovilHelper;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AppMovilHelper implements IAppMovilHelper {

	/*
	 * Castea los datos de los campos de la pantalla
	 * @param tipoDato
	 * @param requerido
	 * @param tipoControl
	 * @param interaccion
	 * @return String
	 * 
	 */
	@Override
	public String casteaDatos(String tipoDato, String requerido, String tipoControl, String interaccion) {
		
		log.debug("-----------CASTEO DE DATOS--------------");
		
		String tipo = " ";
		String align = " ";
		String req = " ";
		String control = " ";
		String inter = " ";

		// Procesa tipo de dato
		switch (tipoDato) {
		case "ALFANUMERICO":
			tipo = "A";
			align = "I";
			break;
		case "DECIMAL":
			tipo = "D";
			align = "D";
			break;
		case "ENTERO":
			tipo = "E";
			align = "D";
			break;
		case "FECHA":
			tipo = "F";
			align = "C";		
			break;
		case "DOCUMENTO":
			tipo = "C";
			align = "I";
			break;
		default:
			tipo = "I"; // O algún valor predeterminado
			align = "I";
			break;
		}
	
		log.info("Requerido: " + requerido);
		// Procesa requerido
		switch (requerido) {
		case "REQUERIDA":
			req = "R";
			break;
		case "OPCIONAL":
			req = "O";
			break;
		default:
			req = "O"; // Valor predeterminado
			break;
		}

		// Procesa tipo de control
		switch (tipoControl) {
	
		// Se mapean los controles a dos caracteres
		case "TEXTBOX":
			control = "TB";
			break;
		case "COMBOBOX":
			control = "CB";
			break;
		case "LISTBOX":
			control = "LB"; // while
			break;
		case "RADIOBUTTON":
			control = "RB";
			break;
		case "CHECKBOX":
			control = "CX";
			break;
		case "TEXTAREA":
			control = "TA";
			break;
		case "CALENDAR":
			control = "CA";
			break;
		default:
			control = "D"; // Si es otro tipo de control no mapeado
			break;
		}
		
		log.info("Tipo de interaccion: " + interaccion);
		
		// Procesa tipo de interacción
		switch (interaccion) { 
		case "ENTRADA":
			inter = "E"; // Entrada
			break;
		case "SALIDA":
			inter = "S"; // Salida
			break;
		case "ENTRADA-SALIDA":
			inter = "ES";
			break;
		default:
			inter = "ES";
			break;

		}
		
		// Si la interacción es de salida, el campo no es requerido
		if(inter.equals("S")) {
			req = "N";
		}

		// Concatenar todos los valores con un espacio en blanco
		log.debug("Tipo: " + tipo + " | Align: " + align + " | Req: " + req + " | Control: " + control + " | Inter: " + inter);
		// Retornar los valores concatenados
		return tipo + "|" + align + "|" + req + "|" + control + "|" + inter;
	}

}
