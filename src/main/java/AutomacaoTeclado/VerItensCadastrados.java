package AutomacaoTeclado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerItensCadastrados {
    public void soma(WebDriver driver){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pressione ENTER para iniciar a extração...");
        scanner.nextLine(); // Espera o usuário pressionar Enter

        try {
            // Localiza todos os <td> com o estilo e classe específicos
            List<WebElement> elementos = driver.findElements(By.xpath(
                    "//td[contains(@style,'background-color:#cdffcd') and contains(@class,'text-center')]"
            ));

            // Armazena os valores extraídos
            List<Double> valores = new ArrayList<>();

            for (WebElement elemento : elementos) {
                String texto = elemento.getText().trim();

                if (!texto.isEmpty()) {
                    // Substitui vírgula por nada e ponto por separador decimal
                    // Exemplo: "1,240.80" → "1240.80"
                    String valorFormatado = texto.replace(",", "");
                    try {
                        double numero = Double.parseDouble(valorFormatado);
                        valores.add(numero);
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido ignorado: " + texto);
                    }
                }
            }

            // Soma todos os valores
            double somaTotal = valores.stream().mapToDouble(Double::doubleValue).sum();

            // Exibe resultados
            System.out.println("\nValores encontrados: " + valores);
            System.out.println("Soma total: " + somaTotal);

        } catch (Exception e) {
            System.out.println("Erro ao extrair valores: " + e.getMessage());
        }
    }
}
