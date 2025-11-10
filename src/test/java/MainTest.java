import AutomacaoSavi.AutoData.AutomacaoData;
import AutomacaoSavi.Automacao.AutoMaster;
import AutomacaoSavi.Automacao.Automacao;
import AutomacaoSavi.Planilha.LeituraPlanilha;
import AutomacaoSavi.Planilha.Planilha;
import AutomacaoSavi.SeleniumDriver.DriverSelerium;
import AutomacaoSavi.SeleniumDriver.Selenium;
import org.openqa.selenium.WebDriver;

import java.awt.*;

//60714652300 MA86322917

public class MainTest {
    public static void main(String[] args) throws AWTException {
        Planilha planilha = new Planilha();
        LeituraPlanilha leituraPlanilha = new LeituraPlanilha();
        AutomacaoData automacaoData = new AutomacaoData();
        Robot robot = new Robot();
        Automacao automacao = new Automacao(robot);
        DriverSelerium driverS = new DriverSelerium();
//        WebDriver driver = driverS.getDriver();
        Selenium selenium = new Selenium();

        AutoMaster autoMaster = new AutoMaster(planilha, leituraPlanilha, automacaoData, automacao, driverS, selenium);

        autoMaster.autoStart();
    }
}