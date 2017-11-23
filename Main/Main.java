package Main;

public class Main {
    Main(){
        Batalha treta = new Batalha();
        treta.carregarTabelas("src/TabelaDeEspecies.txt", 1);
        treta.carregarTabelas("src/TabelaDeAtaques.txt", 2);
        treta.printaAtaque();
        System.out.println();
        treta.printaEspecie();
    }
}
