package Req4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteTable {

    public static void main(String[] args) {
        ArrayList<FileInfo> tags = new ArrayList<>();
        FileInfo t1 = new FileInfo("Segunda", "Ficheiro", "Tag1", "Teste", "wwww.google.com");
        FileInfo t2 = new FileInfo("Terca", "Ficheiro2", "Tag2", "Teste1", "wwww.facebook.com");
        tags.add(t1);
        tags.add(t2);
        WriteTable wt= new WriteTable();
        try {
            wt.draw(tags);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void draw(ArrayList<FileInfo> tags) throws IOException {
        File page = new File("tabela.html");
        if(page.exists()){
            page.delete();
            page.createNewFile();
        }
        FileWriter myWriter;
            myWriter = new FileWriter("tabela.html");
            myWriter.write("<!DOCTYPE html> <html> <head> <style> #files {font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;border-collapse: collapse; width: 100%;} #files td, #files th {border: 1px solid #ddd; padding: 8px;} #files tr:nth-child(even){background-color: #f2f2f2;}#files tr:hover {background-color: #ddd;}#files th {  padding-top: 12px;  padding-bottom: 12px;  text-align: left;  background-color: #4CAF50;  color: white;}</style></head><body><table id=\"files\">  ");
            String str="<tr><td>Data</td><td>Nome do Ficheiro</td> <td>Tag</td><td>Mensagem</td><td>Link</td></tr>";
            myWriter.write(str);
            for(FileInfo tag :tags){
                str="  <tr>  \n  <td>"+ tag.getDate() +"</td> \n   <td>"+tag.getFileName() +"</td> \n    <td>"+ tag.getName()+"</td> \n    <td>"+tag.getMessage() +"</td> \n    <td><a href=\""+tag.getLink()+"\">"+ tag.getLink()+"</a></td> \n   </tr> \n ";
                myWriter.write(str);
            }
            myWriter.write("</table>");
            myWriter.close();
    }

}