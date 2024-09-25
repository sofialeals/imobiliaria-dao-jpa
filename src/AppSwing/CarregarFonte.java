package AppSwing;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

public class CarregarFonte {
    public static void carregarFonte(String caminhoArquivo) {
        try {
            InputStream fonteStream = CarregarFonte.class.getResourceAsStream(caminhoArquivo);
            Font fonte = Font.createFont(Font.TRUETYPE_FONT, fonteStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fonte);
        } catch (Exception erroFonte) {
            System.out.println(erroFonte.getMessage());
        }
    }
}
