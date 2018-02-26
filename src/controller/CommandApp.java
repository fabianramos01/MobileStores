package controller;

import javax.swing.ImageIcon;

public enum CommandApp {

	COMMAND_ADD_STORE("COMMAND_ADD_STORE", "Agregar tienda", "/img/ADDSTORE.PNG"),
	COMMAND_ADD_BILL("COMMAND_ADD_BILL", "Agregar factura", ""),
	COMMAND_SELECT_FILE("COMMAND_SELECT_FILE", "Seleccionar imagen", ""), 
	COMMAND_SIGN_IN("COMMAND_SIGN_IN", "Registrarse", ""),
	COMMAND_LOAD_RECORD("COMMAND_LOAD_RECORD", "Generar registro", ""), 
	COMMAND_LOGN_IN("COMMAND_LOGN_IN", "Ingresar", ""),
	COMMAND_PANEL_LOGN_IN("COMMAND_PANEL_LOGN_IN", "Ingresar", ""),
	COMMAND_PRINCIPAL_PANEL("COMMAND_PRINCIPAL_PANEL", "Principal", ""),
	COMMAND_DOWNLOAD_RECORD("COMMAND_DOWNLOAD_RECORD", "Descargar registro", "/img/download.png"),
	COMMAND_DIALOG_BILL("COMMAND_DIALOG_BILL", "Nueva Factura", "");
	
	private String command;
	private String title;
	private String imagePath;
	
	private CommandApp(String command, String title, String imagePath) {
		this.command = command;
		this.title = title;
		this.imagePath = imagePath;
	}

	public String getCommand() {
		return command;
	}

	public String getTitle() {
		return title;
	}
	
	public ImageIcon getIcon() {
		return new ImageIcon(getClass().getResource(imagePath));
	}
}