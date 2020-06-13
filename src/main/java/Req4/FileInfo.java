package Req4;

/**
 * @author ricardo
 *
 */
public class FileInfo {
	/**
	 * Class que tem a informação sobre um ficheiro
	 */
	private String date;
	private String fileName;
	private String name;
	private String message;
	private String link;

	/**
	 * Contrutor sem parametros
	 */
	FileInfo(){}
	/**
	 * @param date String com a data formatada
	 * @param fileName Nome do Ficheiro
	 * @param name Tag
	 * @param message Mensagem associada a tag
	 * @param link
	 */
	FileInfo(String date, String fileName, String name, String message, String link){
		this.date = date;
		this.fileName = fileName;
		this.name = name;
		this.message = message;
		this.link = link;
	}

	/**
	 * @return String data
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * @param date String com a data formatada
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return nome do ficheiro
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @param fileName nome do ficheiro
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return nome da tag
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name nome da tag
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return messagem associada a tag
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param message mensagem associada a tag
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return link url para visualizar o gráfico
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * @param link url para visualizar o gráfico
	 */
	public void setLink(String link) {
		this.link = link;
	}




}