package AutomacaoTeclado;

import org.openqa.selenium.WebDriver;

import java.awt.*;

//60714652300 MA86322917

public class Main {
    public static void main(String[] args) throws java.awt.AWTException {
        LeituraPlanilha leitura = new LeituraPlanilha();
        Robot robot = new Robot();
        Automacao automacao = new Automacao(robot);
        TratamentoPopUps tratamento = new TratamentoPopUps();
        DriverSelerium driverS = new DriverSelerium();

        driverS.openSavi();
        WebDriver driver = driverS.getDriver();
        leitura.sheetValidation();
        automacao.prestadorDefinition();
        leitura.valorSteers(automacao, driver, tratamento);
    }
}