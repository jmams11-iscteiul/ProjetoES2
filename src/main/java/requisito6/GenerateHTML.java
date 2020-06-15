package requisito6;

/**
 * 
 * GenerateHTML generates a HTML String containing a table with 2 divisions of HTML text side by side.
 * 
 * 
 * Date: Jun 10-2020
 * 
 * @author Filipe Cruz
 * @version 1.0
 * 
 * 
 *
 */
public class GenerateHTML {
	/**
	 * Prints content with both texts to present to the HTML file into a String.
	 * @param rightSideString is the String shown on the right side of the page.
	 * @param leftSideString is the String shown on the left side of the page.
	 */
	public String printToString(String rightSideString, String leftSideString){
		String str = "Content-type: text/html\n\n" +
				"<head>\r\n" + 
				"<title>Covid Evolution Diff</title>\r\n"+
				"<style>\r\n" + 
				"table {\r\n" + 
				"  font-family: arial, sans-serif;\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"  width: 100%;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"th {\r\n" + 
				"  border: 1px solid #000000;\r\n" + 
				"  text-align: center;\r\n" + 
				"  padding: 8px;\r\n" + 
				"}\r\n" + "h2 {\r\n" + 
						"  text-align: center;\r\n" + 
						"  padding: 8px;\r\n" + 
						"}" +
				"\r\n" + 
				"td{\r\n" + 
				"  border: 1px solid #000000;\r\n" + 
				"  text-align: left;\r\n" + 
				"  padding: 8px;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"tr:nth-child(even) {\r\n" + 
				"  background-color: #ffffff;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body style=\"background-color:LightGray;\">\r\n" + 
				"\r\n" + 
				"<h2>Covid Evolution Diff</h2>\r\n" + 
				"\r\n" + 
				"<table>\r\n" + 
				"  <tr>\r\n" + 
				"    <th>Versao anterior</th>\r\n" + 
				"    <th>Versao atual</th>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td>" + leftSideString
				+ "</td>\r\n" + 
				"    <td>" + rightSideString
				+ "</td>\r\n" + 
				"  </tr>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"</body>\r\n" + 
				"</body>\n</html>\n";
		str = str.replaceAll("&para;", "");
		return str;
	}
}
