package manual;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


public class Manual {
    public static void abrirManual(String path){
        try {
            Desktop.getDesktop().open(new File("D:\\Manual\\"+path+".html"));
        } catch (IOException ex) {
            System.err.println("Erro ao abrir arquivo: "+ex);
        }
    }
}
