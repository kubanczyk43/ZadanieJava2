import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Gracz {
    
    private String nick ;
    
    public Gracz(String nick)
    {
        this.nick = nick;
    }
    
    
    ///metoda zapisywanie do pliku danych 
    
             
    public static void ZapisywanieDoPlikuProgram(int i, int licznikZ, int licznikP) throws IOException
    {
        File plikProgram = new File("C:\\Users\\jakub\\Desktop\\Program.txt");   
        if(plikProgram.exists())
    {
        try (
                FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\Program.txt")) {
                fileWriter.write("Program" + "\r\n" + i + "\r\n" + licznikZ + "\r\n" + licznikP);
                
            } 
        catch (IOException e) 
            {
                System.out.println("Wystąpił błąd.");
                e.printStackTrace();
            }
    }
    else
    {
        plikProgram.createNewFile();
    }
    }
    public static void ZapisywanieDoPliku(String nick, int i, int licznikZ, int licznikP) throws IOException {  
    File plik = new File("C:\\Users\\jakub\\Desktop\\"+nick+".txt");
    int wynikPlik =0;
    
    if(plik.exists()) //sprawdzanie czy istnieje plik
    {
        
        try  {
            Path path = Paths.get("C:\\Users\\jakub\\Desktop\\"+nick+".txt");
            List<String> strings = Files.readAllLines(path);
            wynikPlik = Integer.parseInt(strings.get(1));
                    if(wynikPlik<i)
                    {
                        try (
            
                            FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\"+nick+".txt")) {
                            fileWriter.write(nick + "\r\n" + wynikPlik + "\r\n" + licznikZ + "\r\n" +licznikP );
                            System.out.println("Nie udało ci się pobić najlepszego wyniku");
                            } 
                        catch (IOException e) 
                        {
                            System.out.println("Wystąpił błąd.");
                            e.printStackTrace();
                        }
                    }
                    else if(wynikPlik>i)
                    {
                        try (
            
                            FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\"+nick+".txt")) {
                            fileWriter.write(nick + "\r\n" + i + "\r\n" + licznikZ + "\r\n" +licznikP );
                            System.out.println("Pomyślnie zapisano gre z nowym najlepszym wynikiem.");
                            } 
                        catch (IOException e) 
                        {
                            System.out.println("Wystąpił błąd.");
                            e.printStackTrace();
                        }
                    }
                   
                
            
        } 
        catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
        } 
        catch (NumberFormatException e) {
            System.out.println("Błąd konwersji liczby: " + e.getMessage());
        }
        
    }
    else
    {
        
        plik.createNewFile(); ///tworzenie nowego pliku
        try (
            
            FileWriter fileWriter = new FileWriter("C:\\Users\\jakub\\Desktop\\"+nick+".txt")) {
            
            fileWriter.write(nick + "\r\n" + wynikPlik + "\r\n" + licznikZ + "\r\n" +licznikP );
            System.out.println("Pomyślnie zapisano gre.");
        } 
        catch (IOException e) {
            System.out.println("Wystąpił błąd." + e.getMessage());
            e.printStackTrace();
        }
    }      
}
    public static String odczytDanych(String nick) throws IOException
    {
        File plik = new File("C:\\Users\\jakub\\Desktop\\"+nick+".txt");
        String sciezka = "C:\\Users\\jakub\\Desktop\\"+nick+".txt";
        String nazwa = "";
        int wynikN = 0;
        int licznikZ =0;
        int licznikP =0;
        if(plik.exists())
        {
            /*try (BufferedReader odczyt = new BufferedReader(new FileReader(sciezka))) {
            String dane;
            while ((dane = odczyt.readLine()) != null) {
                System.out.println(dane); // Wyświetla odczytane dane nick oraz najlepszy wynik
            }
            }*/
            try
            {
                Path path = Paths.get(sciezka);
                List<String> strings = Files.readAllLines(path);
                nazwa = strings.get(0);
                wynikN = Integer.parseInt(strings.get(1));
                licznikZ = Integer.parseInt(strings.get(2));
                licznikP = Integer.parseInt(strings.get(3));
                System.out.println("Nazwa gracza:" + nazwa);
                System.out.println("Najlepszy wynik: " +wynikN);
                System.out.println("Liczba zwycięstw: " +licznikZ);
                System.out.println("Liczba porażek: " +licznikP);
                
            }
            catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
        }
        
        }
        return "";
    }
    ///losowanie liczby oraz zgadywanie
    public static int Gra(String nick) throws IOException
    {
        
        Path path = Paths.get("C:\\Users\\jakub\\Desktop\\"+nick+".txt");
        List<String> strings = Files.readAllLines(path);
        int licznikZ = Integer.parseInt(strings.get(2));
        int licznikP = Integer.parseInt(strings.get(3));
        
        Path path2 = Paths.get("C:\\Users\\jakub\\Desktop\\Program.txt");
        List<String> strings2 = Files.readAllLines(path2);
        int licznikZprogram = Integer.parseInt(strings2.get(2));
        int licznikPprogram = Integer.parseInt(strings2.get(3));
        int licznikWynikProgram = 0;
        int liczbaPoziomTrudnosci=0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Wybierz poziom trudności easy/medium/hard");
        String poziom = scan.nextLine();
        if(poziom.equalsIgnoreCase("easy"))
        {
            liczbaPoziomTrudnosci = 101;
        }
        else if(poziom.equalsIgnoreCase("medium"))
        {
            liczbaPoziomTrudnosci = 1001;
        }
        else if(poziom.equalsIgnoreCase("hard"))
        {
            liczbaPoziomTrudnosci = 10001;
        }
        else
        {
            System.out.println("Błędnie wybrałeś poziom trudności wybierz ponownie");
            poziom = scan.nextLine();
        }
        
        System.out.println("Podaj liczbę jaką mam zgadnąć");
        String liczba2 = scan.nextLine();
        int liczbaBot = Integer.valueOf(liczba2);
        Random liczbyLosowe = new Random();
        boolean moneta = liczbyLosowe.nextBoolean();
        System.out.println(moneta);
        int liczba = liczbyLosowe.nextInt(liczbaPoziomTrudnosci);
        //System.out.println(liczba); ///// wylosowana liczba
        List<Integer> listaLiczb = new ArrayList<Integer>();
        System.out.println(liczba);
        System.out.println(liczbaPoziomTrudnosci);
            if(moneta == true)
            {
                System.out.println("Zaczynasz rozgrywkę");
                for(int i=1; i<liczbaPoziomTrudnosci;i++)
            {
                    
                    System.out.println("Zgadnij jaką wylosowało liczbę");
                    String liczbaUzytkownika  = scan.nextLine();
                    int liczbaUz = Integer.valueOf(liczbaUzytkownika);
                if(liczbaUz == liczba)
                {
                    licznikZ++;
                    licznikPprogram++;
                    ZapisywanieDoPliku(nick,i,licznikZ,licznikP);
                    ZapisywanieDoPlikuProgram(licznikWynikProgram,licznikZprogram,licznikPprogram);
                    System.out.println("Udało ci się odgadnąć");
                    System.out.println("Chcesz zagrać ponownie (tak/nie) ");
                    String odpowiedz = scan.next();
                    if (odpowiedz.equalsIgnoreCase("tak")) {
                        Gra(nick);
                    }
                    else if(odpowiedz.equalsIgnoreCase("nie"))
                    {
                        System.out.println("Dziękujemy za grę!");
                        scan.close();
                        break;
                    }
                }
                else
                {
                     System.out.println("Próbuj dalej");

                }
                int liczbaOdp = liczbyLosowe.nextInt(101);
                    System.out.println("Mój strzał to: " + liczbaOdp + " ");
                    if(listaLiczb.contains(liczbaOdp))
                    {

                    }
                    else
                    {
                            if(liczbaBot == liczbaOdp)
                        {
                            licznikP++;
                            licznikZprogram++;
                            System.out.println("Udało mi się odgadnąć");
                        }
                        else
                        {
                            licznikWynikProgram++;
                            listaLiczb.add(liczbaOdp);
                            System.out.println("Spróbuje jeszcze raz");
                        }
                    }
            }  
            }
            else if(moneta == false)
            {
                System.out.println("Ja zaczynam rozgrywkę");
                for(int i=1; i<liczbaPoziomTrudnosci;i++)
            {
                                int liczbaOdp = liczbyLosowe.nextInt(liczbaPoziomTrudnosci);
                    System.out.println("Mój strzał to: " + liczbaOdp + " ");
                    if(listaLiczb.contains(liczbaOdp))
                    {

                    }
                    else
                    {
                            if(liczbaBot == liczbaOdp)
                        {
                            licznikP++;
                            licznikZprogram++;
                            System.out.println("Udało mi się odgadnąć");
                        }
                        else
                        {
                            licznikWynikProgram++;
                            listaLiczb.add(liczbaOdp);
                            System.out.println("Spróbuje jeszcze raz");
                        }
                    }
                    System.out.println("Zgadnij jaką wylosowało liczbę");
                    String liczbaUzytkownika  = scan.nextLine();
                    int liczbaUz = Integer.valueOf(liczbaUzytkownika);
                    
                if(liczbaUz == liczba)
                {
                    licznikZ++;
                    licznikPprogram++;
                    ZapisywanieDoPliku(nick,i,licznikZ,licznikP);
                    ZapisywanieDoPlikuProgram(licznikWynikProgram,licznikZprogram,licznikPprogram);
                    System.out.println("Chcesz zagrać ponownie (tak/nie) ");
                    String odpowiedz = scan.next();
                    if (odpowiedz.equalsIgnoreCase("tak")) {
                        Gra(nick);
                    }
                    else if(odpowiedz.equalsIgnoreCase("nie"))
                    {
                        System.out.println("Dziękujemy za grę!");
                        scan.close();
                        break;
                    }
                }
                else
                {
                     System.out.println("Próbuj dalej");

                }
                

            }  
            }  
        
        
        return liczba;
    }
    
    public static void main(String[] args) throws IOException
    {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj swój nickname: ");
        String nick = scan.nextLine();
        System.out.println(odczytDanych(nick));
        System.out.println(Gra(nick));
    }
    
}
