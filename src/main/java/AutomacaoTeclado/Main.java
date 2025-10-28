package AutomacaoTeclado;

import org.openqa.selenium.WebDriver;

import java.awt.*;

//07483697326 Ray102030
//60714652300 MA86322917

public class Main {
    public static void main(String[] args) throws java.awt.AWTException {
        LeituraEscritaPlanilha leitura = new LeituraEscritaPlanilha();
        Robot robot = new Robot();
        Automacao automacao = new Automacao(robot);
        Selenium tratamento = new Selenium();
        DriverSelerium driverS = new DriverSelerium();

        driverS.openSavi();
        WebDriver driver = driverS.getDriver();
        leitura.sheetValidation();
        automacao.prestadorDefinition();
        leitura.valorSteers(automacao, driver, tratamento);
    }
}