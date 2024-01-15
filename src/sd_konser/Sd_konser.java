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
     public record konser( String id_konserc, String namaBand, String genre, String tempat_konser,String kategori ,int harga  ){};
     public record transaksi(String id_transaksi, String id_peserta, String id_konser, String genre){};
     
     public static void menu (){
          System.out.println("===========================");
          System.out.println("  List Konser Yang Tersedia ");
          System.out.println("1. Pop");
          System.out.println("2. Kpop ");
          System.out.println("==========================="); 
          System.out.print("Kamu mau konser apa Gendre Apa :");
          
     }
     
     
     
    public static void main(String[] args) {
        // TODO code application logic here
        
        peserta[] listPeserta = new peserta[2];
        listPeserta[0] = new peserta("PS01" , "Jeje" , 0766);
        listPeserta[1] = new peserta("PS02" , "Bebe" , 0226);
        
        konser[] listKonser = new konser [5];
        listKonser[0] = new konser ("C01","Coldplay", "Pop" ,"Jakarta", "Gold", 3000);
        listKonser[1] = new konser ("C02" ,"Coldplay","Pop" , "Jakarta", "Silver", 2000);
        listKonser[2] = new konser ("C03" ,"Coldplay", "Pop" ,"Jakarta", "Bronze", 3000);
        listKonser[3] = new konser ("T01" ,"Twice", "Kpop" ,"Jakarta", "Gold", 5000);
        listKonser[4] = new konser("T02" ,"Twice", "Kpop" ,"Jakarta", "Bronze", 9000);
                
                    Scanner input = new Scanner(System.in);
                       
                    LinkedList<String> riwayatTrans = new LinkedList<>();
                    LinkedList<transaksi> listTransaksi = new LinkedList<>();
            
              Queue<peserta> pesertaQueue = new LinkedList<peserta>();
              for(int i=0; i<listPeserta.length; i++){
              pesertaQueue.add(listPeserta[i]);
              }
             
              
              String pilih_konser = null;
    
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
          pilih_konser = input.nextLine();
      
            for (konser k : listKonser) {
            if (k.namaBand().equalsIgnoreCase(pilih_konser)) {
            System.out.println("==========================");
            System.out.println(" List Konser Yang Tersedia ");
            System.out.println("===========================");
              System.out.println("No. " + k.id_konserc);
              System.out.println("Band: " + k.namaBand());
              System.out.println("Kategori " + k.kategori);
              System.out.println("Harga Rp." + k.harga);
              System.out.println("------------------------------");
            }
        }
           
              System.out.println("Pilih Kategori Konser : ");
              String kategoriR = input.nextLine();
            for (konser c: listKonser) {
            if (c.id_konserc.equals(kategoriR)) {
            System.out.println("Harga Tiket yang Harus Anda Bayar : " + c.harga() + "" );
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
              break;  // Exit the loop once the correct konserc is found
          
        } 
    }
              }
                

   // FIX BAWAAN            
       }
              }
                            
                         
    
    

