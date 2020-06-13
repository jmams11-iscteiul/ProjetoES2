package Req4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ricar
 *
 */
public class WriteTable {
    static File  file;
    static FileWriter  myWriter;

    /**
     * @param filesInfo lista com a informação de todos os ficheiros para adicionar a tabela
     * @throws IOException  escreve num ficheiro html
     * inicia o writer
     * escreve a head do html (css e formato da tabels)
     * para cada fileInfo cria uma linha tabela
     * fecha o writer
     */
    public static void draw(ArrayList<FileInfo> filesInfo) throws IOException {
        createWriter();
        writeHead();
            for(FileInfo tag :filesInfo){
                String str="  <tr>  \n  <td>"+ tag.getDate() +"</td> \n   <td>"+tag.getFileName() +"</td> \n    <td>"+ tag.getName()+"</td> \n    <td>"+tag.getMessage() +"</td> \n    <td><a href=\""+tag.getLink()+"\">"+ tag.getLink()+"</a></td> \n   </tr> \n ";
                myWriter.write(str);
            }
            myWriter.write("</table>");
            myWriter.close();
    }

    
    /**
     * @throws IOException cria ou escreve por cima de um ficheiro
     * Verifica se o ficheito "tabela.html" existe,
     * se existir, escrever por cima
     * se nao existir, cria
     * cria um FileWriter associado a esse documento html
     */
    private static void createWriter() throws IOException {
        File page = new File("tabela.html");
        if(page.exists()){
            page.delete();
            page.createNewFile();
        }
        myWriter = new FileWriter("tabela.html");
    }


    /**
     * @throws IOException escre no ficheiro html
     * Escreve no ficheiro html a estrutura e o estilo da tabela
     * 
     * 
     */
    private static void writeHead() throws IOException {
        myWriter.write("<!DOCTYPE html> <html> <head> <style> #files {font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;border-collapse: collapse; width: 100%;} #files td, #files th {border: 1px solid #ddd; padding: 8px;} #files tr:nth-child(even){background-color: #f2f2f2;}#files tr:hover {background-color: #ddd;}#files th {  padding-top: 12px;  padding-bottom: 12px;  text-align: left;  background-color: #4CAF50;  color: white;}</style></head><body><table id=\"files\">  ");
        String str="<tr><td>Data</td><td>Nome do Ficheiro</td> <td>Tag</td><td>Mensagem</td><td>Link</td></tr>";
        myWriter.write(str);
    }
}