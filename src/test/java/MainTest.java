import AutomacaoTeclado.*;
import org.openqa.selenium.WebDriver;
import java.awt.*;

//60714652300 MA86322917

public class MainTest {
    public static void main(String[] args) throws AWTException {
        LeituraEscritaPlanilha leitura = new LeituraEscritaPlanilha();
        Robot robot = new Robot();
        Automacao automacao = new Automacao(robot);
        Selenium selenium = new Selenium();
        DriverSelerium driverS = new DriverSelerium();
        VerItensCadastrados verItensCadastrados = new VerItensCadastrados();

        driverS.openSavi();
        WebDriver driver = driverS.getDriver();
        verItensCadastrados.soma(driver);

    }
}