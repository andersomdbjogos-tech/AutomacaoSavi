import AutomacaoTeclado.Automacao;
import AutomacaoTeclado.Planilha;

import java.awt.*;

public class Teste01 {
    public static void main(String[] args) throws java.awt.AWTException {
        Robot robot = new Robot();
        Automacao automacao = new Automacao(robot);
        Planilha planilha = new Planilha();

        planilha.setSenha("M98798774");

    }
}