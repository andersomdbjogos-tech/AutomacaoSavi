package AutoTest;

//import AutomacaoTeclado.LeituraPlanilha;
import AutomacaoTeclado.DriverSelerium;
import org.openqa.selenium.WebDriver;

        import java.awt.*;

//60714652300 MA86322917

public class MainTest {
    public static void main(String[] args) throws java.awt.AWTException {
        LeituraTest01 leitura = new LeituraTest01();
        Robot robot = new Robot();
        AutomacaoTest01 automacao = new AutomacaoTest01(robot);
        TratamentoTest01 tratamento = new TratamentoTest01();
        DriverSelerium01 driverS = new DriverSelerium01();

        driverS.openSavi();
        WebDriver driver = driverS.getDriver();
        leitura.sheetValidation();
        automacao.prestadorDefinition();
        leitura.valorSteers(automacao, driver, tratamento);
    }
}