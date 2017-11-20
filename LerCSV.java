import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LerCSV {
        String csvArq = "/home/narcizo/Documents/TrabalhoPPIOO 2017/TabelaDeEspecies.txt";

        BufferedReader conteudoCsv = null;

        String linha;

        String separador = "\t";

        public void ler() {
            int i = 0;
            int j = 0;

            try {
                conteudoCsv = new BufferedReader(new FileReader(csvArq));//abre arquivo e cria objeto
                while ((linha = conteudoCsv.readLine()) != null) {
                    String[] camposLidos = linha.split(separador);
                    //imprimindo os valores lidos
                    for (String s : camposLidos)
                        System.out.printf("%s\t", s);
                    //tabela[i][j++] = s;
                    i++;
                    j = 0;
                    System.out.println();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo nao encontrado: \n" + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Indice fora do limite: \n" + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erro de IO: \n" + e.getMessage());
            } finally {
                //if (conteudoCsv);
            }
        }
}
