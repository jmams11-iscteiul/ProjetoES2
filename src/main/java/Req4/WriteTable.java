package Req4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteTable {

    public static void main(String[] args) {
        ArrayList<Tag> tags = new ArrayList<>();
        Tag t1 = new Tag("Segunda", "Ficheiro", "Tag1", "O ramalho é gay", "wwww.google.com");
        Tag t2 = new Tag("Terça", "Ficheiro2", "Tag2", "O vasco é gay", "wwww.facebook.com");
        tags.add(t1);
        tags.add(t2);
        try {
            draw(tags);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void draw(ArrayList<Tag> tags) throws IOException {
        File page = new File("tabela.html");
        if(page.exists()){
            page.delete();
            page.createNewFile();
        }
        FileWriter myWriter;


        try {
            myWriter = new FileWriter("tabela.html");
            myWriter.write("<!DOCTYPE html>    <html>   <body> <h2>Basic HTML Table</h2><table style=\"widtd:600%\">");
            String str="<tr><td>Data</td><td>Nome do Ficheiro</td> <td>Tag</td><td>Mensagem</td><td>Link</td></tr>";
            myWriter.write(str);
            for(Tag tag :tags){
                str="  <tr>  \n  <td>"+ tag.getDate() +"</td> \n   <td>"+tag.getFileName() +"</td> \n    <td>"+ tag.getName()+"</td> \n    <td>"+tag.getMessage() +"</td> \n    <td><a href=\""+tag.getLink()+"\">"+ tag.getLink()+"</a></td> \n   </tr> \n ";
                myWriter.write(str);
            }
            myWriter.write("</table>");
            myWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}