package Req4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteTable {
    static File  file;
    static FileWriter  myWriter;

    public static void draw(ArrayList<FileInfo> tags) throws IOException {
        createWriter();
        writeHead();
            for(FileInfo tag :tags){
                String str="  <tr>  \n  <td>"+ tag.getDate() +"</td> \n   <td>"+tag.getFileName() +"</td> \n    <td>"+ tag.getName()+"</td> \n    <td>"+tag.getMessage() +"</td> \n    <td><a href=\""+tag.getLink()+"\">"+ tag.getLink()+"</a></td> \n   </tr> \n ";
                myWriter.write(str);
            }
            myWriter.write("</table>");
            myWriter.close();
    }

    private static void createWriter() throws IOException {
        File page = new File("tabela.html");
        if(page.exists()){
            page.delete();
            page.createNewFile();
        }
        myWriter = new FileWriter("tabela.html");
    }


    private static void writeHead() throws IOException {
        myWriter.write("<!DOCTYPE html> <html> <head> <style> #files {font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;border-collapse: collapse; width: 100%;} #files td, #files th {border: 1px solid #ddd; padding: 8px;} #files tr:nth-child(even){background-color: #f2f2f2;}#files tr:hover {background-color: #ddd;}#files th {  padding-top: 12px;  padding-bottom: 12px;  text-align: left;  background-color: #4CAF50;  color: white;}</style></head><body><table id=\"files\">  ");
        String str="<tr><td>Data</td><td>Nome do Ficheiro</td> <td>Tag</td><td>Mensagem</td><td>Link</td></tr>";
        myWriter.write(str);
    }
}