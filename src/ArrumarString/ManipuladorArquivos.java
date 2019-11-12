/*package ArrumarString;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
 
public class ManipuladorArquivos {
 
    public static String leitor() throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader("C:\\Projetos Netbeans\\AlmanahSystem\\conf\\conf.txt"));
        String linha = "";
        while (true) {
            if (linha != null) {
                //System.out.println(linha);
                if (linha.contains("cod")){
                    return linha;
                }
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return linha;
    }
 
    public static void escritor(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("C:\\Projetos Netbeans\\AlmanahSystem\\conf\\conf.txt"));
        String linha = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Escreva algo: ");
        linha = in.nextLine();
        buffWrite.append(linha + "\n");
        buffWrite.close();
    }
 
}*/

