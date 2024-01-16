/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sd_konser;

/**
 *
 * @author ASUS
 */
 import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Sd_konser {

    /**
     * @param args the command line arguments
     */
     public record peserta(String id_peserta, String nama,  int no_hp){};
     public record konser( String id_konserc, String namaBand, String genre, String tempat_konser,String kategori ,int harga , String[] jumlah_kursi ){};
     public record transaksi(String id_transaksi, String id_peserta, String id_konser, String genre){};
     
     
     public static void menu (){
          System.out.println("===========================");
          System.out.println("  List Konser Yang Tersedia ");
          System.out.println("1. Pop");
          System.out.println("2. Kpop ");
          System.out.println("==========================="); 
          System.out.print("Kamu mau konser apa Gendre Apa :");
          
     }
     
     public static void removeValueFromStack(Stack<String> stack, String valueToRemove) {
        Stack<String> tempStack = new Stack<>();

        // Pop elements from the original stack until finding the value to remove
        while (!stack.isEmpty() && !stack.peek().equals(valueToRemove)) {
            tempStack.push(stack.pop());
        }

        // Pop the value to remove (if found)
        if (!stack.isEmpty()) {
            stack.pop();
        }

        // Restore the remaining elements to the original stack
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
     
    public static void main(String[] args) {
        // TODO code application logic here
        
        peserta[] listPeserta = new peserta[2];
        listPeserta[0] = new peserta("PS01" , "Jeje" , 0766);
        listPeserta[1] = new peserta("PS02" , "Bebe" , 0226);
        
        konser[] listKonser = new konser [5];
        listKonser[0] = new konser ("C01","Coldplay", "Pop" ,"Jakarta", "Gold", 3000 ,new String []{"1A" , "2A" ,"3A"} );
        listKonser[1] = new konser ("C02" ,"Coldplay","Pop" , "Jakarta", "Silver", 2000, new String []{"1B" , "2B" ,"3B"});
        listKonser[2] = new konser ("C03" ,"Coldplay", "Pop" ,"Jakarta", "Bronze", 3000, new String []{"1C" , "2C" ,"3C"});
        listKonser[3] = new konser ("T01" ,"Twice", "Kpop" ,"Jakarta", "Gold", 5000, new String []{"1A" , "2A" ,"3A"});
        listKonser[4] = new konser("T02" ,"Twice", "Kpop" ,"Jakarta", "Bronze", 9000, new String []{"1B" , "2B" ,"3B"});
                
                    Scanner input = new Scanner(System.in);
                       
                    LinkedList<String> riwayatTrans = new LinkedList<>();
                    LinkedList<transaksi> listTransaksi = new LinkedList<>();
                    
            Stack<konser> sKonser = new Stack<>();
                for(int L =0; L<listKonser.length; L++) {
                    sKonser.push(listKonser[L]);
                }
              Queue<peserta> pesertaQueue = new LinkedList<peserta>();
              for(int i=0; i<listPeserta.length; i++){
              pesertaQueue.add(listPeserta[i]);
              }
             
            
              while(!pesertaQueue.isEmpty()){ 
                   peserta p = pesertaQueue.poll();
                       menu();
                          String  pilih_genre;
                  pilih_genre = input.nextLine();
                  System.out.println("");
                  System.out.println("Nama Kamu " + p.nama());
                  System.out.println("");

                    boolean cari_genre = false;
                    konser firstPopConcert = null;

            for (konser concert : listKonser) {
            if (concert.genre().equalsIgnoreCase(pilih_genre)) {
                cari_genre = true;
                firstPopConcert = concert;
                break; // Stop searching once found
            }
        }
      
        if (cari_genre) {
            System.out.println("");
            System.out.println("==========================");
            System.out.println(" List Konser Yang Tersedia ");
            System.out.println("===========================");
            System.out.println(firstPopConcert != null ? firstPopConcert.namaBand() : "N/A");
            System.out.println("---------------------------");
        } else {
            System.out.println("Genre Tidak Tersedia");
        }
        
          System.out.print("Kamu Pilih Konser Apa ? :");
          String pilih_konser = input.nextLine();
           
            for (konser konsers : listKonser){
                     if(konsers.namaBand().equalsIgnoreCase(pilih_konser) ){
                         
                          System.out.println("==========================");
                          System.out.println(" List Konser Yang Tersedia ");
                          System.out.println("===========================");
                          System.out.println("No. " + konsers.id_konserc);
                          System.out.println("Band: " + konsers.namaBand());
                          System.out.println("Kategori " + konsers.kategori);
                          System.out.println("Harga Rp." + konsers.harga);
                          System.out.println("Kursi Yang Tersedia : " + Arrays.toString(konsers.jumlah_kursi()));
                          System.out.println("------------------------------");
                          
                     }
            }
                     
             System.out.println("Pilih Kategori Konser : ");
            String kategoriR = input.nextLine();
            
            for (konser c: sKonser) {
            if (c.id_konserc.equals(kategoriR)) {
                        System.out.println("==========================");
                          System.out.println(" List Konser Yang Tersedia ");
                          System.out.println("===========================");
                          System.out.println("No. " + c.id_konserc);
                          System.out.println("Band: " + c.namaBand());
                          System.out.println("Kategori " + c.kategori);
                          System.out.println("Harga Rp." + c.harga);
                          System.out.println("Kursi Yang Tersedia : " + Arrays.toString(c.jumlah_kursi()));
                          System.out.println("------------------------------");

            System.out.println("Harga Tiket yang Harus Anda Bayar : " + c.harga() + "" );
              break;  // Exit the loop once the correct konserc is found
            }
            
                System.out.print("Pilih Tempat Duduk Yang Aka Dipilih : ");
                 String tp = input.nextLine();
                 Stack<konser> tempStack = new Stack<>();
                 
                  int countToRemove = 0;
        for (String element : c.jumlah_kursi()) {
            if (element.equals(tp)) {
                countToRemove++;
            }
        }

        String[] newArray = new String[c.jumlah_kursi().length - countToRemove];
        int newIndex = 0;

        for (String element : c.jumlah_kursi()) {
            if (!element.equals(tp)) {
                newArray[newIndex++] = element;
            }
        }

            while (!sKonser.isEmpty()) {
            konser currentKonser = sKonser.pop();
            if (currentKonser.namaBand().equalsIgnoreCase(kategoriR)) {
                // Menghapus nilai dari array jika ditemukan
                currentKonser = currentKonser.jumlah_kursi(newArray);
            }
            tempStack.push(currentKonser);
        }
                   
            while (!tempStack.isEmpty()) {
    sKonser.push(tempStack.pop());
}
            LocalDate tanggal_pembuatan = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tanggal_pemesanan = tanggal_pembuatan.format(formatter);
                
            String verAdmin = "Jojo";
              
            System.out.println("============================");
            System.out.println("Riwayat Transaksi Anda Sebagai Berikut");
            System.out.println("=============================");
            System.out.println("Total Pesanan anda " + c.harga());
            System.out.println("");
            System.out.println("Tanggal Pemesanan yang Dibuat : "+ tanggal_pemesanan);
            System.out.println("Di verifikasi oleh admin : " + verAdmin);
            String id_transaksi = "T01";
            transaksi t = new transaksi(id_transaksi, p.id_peserta(), kategoriR, pilih_genre);
            listTransaksi.add(t);
              }
              
             
              
//            for (konser k : listKonser) {
//            if (k.namaBand().equalsIgnoreCase(pilih_konser) && k.jumlah_kursi() > 0) {
//            System.out.println("==========================");
//            System.out.println(" List Konser Yang Tersedia ");
//            System.out.println("===========================");
//              System.out.println("No. " + k.id_konserc);
//              System.out.println("Band: " + k.namaBand());
//              System.out.println("Kategori " + k.kategori);
//              System.out.println("Harga Rp." + k.harga);
//              System.out.println("------------------------------");
//            }
//        }
           
         
    }
                           
              
             

   // FIX BAWAAN            
       }
              }
                            
                         
    
    

