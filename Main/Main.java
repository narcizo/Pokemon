package Main;

public class Main {
    Main(){
        LerCSV ler = new LerCSV();
        ler.lerArq("/home/narcizo/Documents/TrabalhoPPIOO 2017/TabelaDeEspecies.txt", 1);
        ler.lerArq("/home/narcizo/Documents/TrabalhoPPIOO 2017/TabelaDeAtaques.txt", 2);
        ler.printaAtaque();
        System.out.println();
        //ler.printaEspecie();
    }
}
