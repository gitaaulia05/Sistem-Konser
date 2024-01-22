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
     public record peserta(String id_peserta, String nama,  String no_hp){};
     public record konser( String id_konserc, String namaBand, String genre, String tempat_konser,String kategori ,int harga , String[] jumlah_kursi ){};
     public record transaksi(String id_transaksi,String id_riwayat_transaksi , String id_peserta, String tanggal_pemesanan, String verAdmin, int Total_bayar){};
     public record riwayat_transaksi ( String id_riwayat_transaksi , String id_konser,  String genre, String kursi_dipesan){};
     
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
        listPeserta[0] = new peserta("PS01" , "Jeje" , "085643451123");
        listPeserta[1] = new peserta("PS02" , "Bebe" , "087865453315");
        
        konser[] listKonser = new konser [5];
        listKonser[0] = new konser ("C01","Coldplay", "Pop" ,"Jakarta", "Gold", 3000 ,new String []{"1A" , "2A" ,"3A"} );
        listKonser[1] = new konser ("C02" ,"Coldplay","Pop" , "Jakarta", "Silver", 2000, new String []{"1B" , "2B" ,"3B"});
        listKonser[2] = new konser ("C03" ,"Coldplay", "Pop" ,"Jakarta", "Bronze", 3000, new String []{"1C" , "2C" ,"3C"});
        listKonser[3] = new konser ("T01" ,"Twice", "Kpop" ,"Jakarta", "Gold", 5000, new String []{"1A" , "2A" ,"3A"});
        listKonser[4] = new konser("T02" ,"Twice", "Kpop" ,"Jakarta", "Bronze", 9000, new String []{"1B" , "2B" ,"3B"});
                
                    Scanner input = new Scanner(System.in);
                       
                    LinkedList<riwayat_transaksi> riwayatTrans = new LinkedList<>();
                    LinkedList<transaksi> listTransaksi = new LinkedList<>();
                  
                    Stack<konser> sKonser = new Stack<>();
                for(int i = 0; i< listKonser.length; i++) {
                    sKonser.push(listKonser[i]);
                }
                
                
              Queue<peserta> pesertaQueue = new LinkedList<peserta>();
              for(int i=0; i<listPeserta.length; i++){
              pesertaQueue.add(listPeserta[i]);
              }
              while(!pesertaQueue.isEmpty()){ 
                       menu();
                  String  pilih_genre;
                  pilih_genre = input.nextLine();
                  System.out.println("");
                  peserta p = pesertaQueue.poll();
                  System.out.println("Nama Kamu " + p.nama());
                  System.out.println("");

                    boolean cari_genre = false;
                    konser firstPopConcert = null;

            for (konser concert : sKonser) {
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
                  System.out.println("");
          System.out.print("Kamu Pilih Konser Apa ? :");
          String pilih_konser = input.nextLine();
           
                       
                    List<konser> konserList = new ArrayList<>(sKonser);
        konserList.sort(Comparator.comparing(konser::id_konserc));
            for (konser konsers : konserList){
             
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
                   System.out.println("");  
             System.out.print("Pilih Kategori Konser : ");
             String kategoriR = input.nextLine();
            
             int harga = 0;
             konser selectedKonser = null;
            for (konser c: sKonser) {
            if (c.id_konserc.equalsIgnoreCase(kategoriR)) {
                        System.out.println("==========================");
                          System.out.println(" List Konser Yang Tersedia ");
                          System.out.println("===========================");
                          System.out.println("No. " + c.id_konserc);
                          System.out.println("Band: " + c.namaBand());
                          harga = c.harga;
                          System.out.println("Kategori " + c.kategori);
                          System.out.println("Harga Rp." + c.harga);
                          System.out.println("Kursi Yang Tersedia : " + Arrays.toString(c.jumlah_kursi()));
                           selectedKonser = c;
                          System.out.println("------------------------------");

            System.out.println("Harga Tiket yang Harus Anda Bayar : " + c.harga() + "" );
              break;  // Exit the loop once the correct konserc is found
            }                       
            }
                  System.out.println("");
              System.out.print("Pilih Kursi Kamu : ");
              String tp = input.nextLine();
            
            Stack<konser> temporaryStack = new Stack<>();
        konser removedKonser = null;
        while (!sKonser.isEmpty()) {
            konser currentKonser = sKonser.pop();
            if (!currentKonser.id_konserc().equals(kategoriR)) {
                temporaryStack.push(currentKonser);
            } else {
                removedKonser = currentKonser;
            }
        }
        
         if(removedKonser != null) {
             for (konser kons : listKonser) {
            boolean kursiDitemukan = false;                 
               for (String kursiValue : kons.jumlah_kursi()) {
                if (kursiValue.equals(tp) ) {
                    if(kons.id_konserc().equals(kategoriR)){

                    ArrayList<String> kursiList = new ArrayList<>(List.of(kons.jumlah_kursi()));
                    kursiList.remove(tp);

                    // Update the konser object with the modified array
                   konser k = new konser(kons.id_konserc(), kons.namaBand(), kons.genre(), kons.tempat_konser(), kons.kategori(), kons.harga(), kursiList.toArray(new String[0]));

                    kursiDitemukan = true;
                    temporaryStack.push(k);
                    break;  // Exit the loop if kursi is found
                    }
                }  
             } 
                        
              }
           
          }

        while (!temporaryStack.isEmpty()) {
            sKonser.push(temporaryStack.pop());
        }
         
            
             for(konser r :sKonser){
                 System.out.println("hm" + Arrays.toString(r.jumlah_kursi)+ " " + r.id_konserc);
             }
             
            LocalDate tanggal_pembuatan = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tanggal_pemesanan = tanggal_pembuatan.format(formatter);
                
            String verAdmin = "Jojo";
              
            System.out.println("============================");
            System.out.println("Riwayat Transaksi Anda Sebagai Berikut");
            System.out.println("=============================");
            System.out.println("Total Pesanan anda " + harga);
            System.out.println("");
            System.out.println("Tanggal Pemesanan yang Dibuat : "+ tanggal_pemesanan);
            System.out.println("Di verifikasi oleh admin : " + verAdmin);
            
               String id_transaksi = null;
            if(listTransaksi.isEmpty()){
                id_transaksi = "TR" + 1;
            } else {
                int panjangList = listTransaksi.size();
                int nextLength = panjangList + 1;
                id_transaksi = "TR" + nextLength;
            }
            
              String id_riwayat_transaksi = null;
               if(riwayatTrans.isEmpty()){
                id_riwayat_transaksi = "TR" + 1;
            } else {
                int panjangListr = riwayatTrans.size();
                int nextLength = panjangListr + 1;
                id_riwayat_transaksi = "TR" + nextLength;
            }
              
            transaksi t = new transaksi(id_transaksi,id_riwayat_transaksi , p.id_peserta(), tanggal_pemesanan, verAdmin , selectedKonser.harga());
            riwayat_transaksi rt = new riwayat_transaksi (id_riwayat_transaksi ,selectedKonser.id_konserc , selectedKonser.genre(), tp);
            listTransaksi.add(t);
            riwayatTrans.add(rt);
    }
           
              for(transaksi t : listTransaksi){
                  System.out.println("text id " + t.id_transaksi);
                    System.out.println("text id " + t.id_riwayat_transaksi);
                    System.out.println("id_peserta " + t.id_peserta);
                    System.out.println("tangga : " + t.tanggal_pemesanan);
              }
                   System.out.println("");
                   
                   for(riwayat_transaksi r : riwayatTrans) {
                       System.out.println("konser : " + r.id_konser);
                       System.out.println("gente : " + r.genre);
                       System.out.println("tempat : " + r.kursi_dipesan);
                   }
             

   // FIX BAWAAN            
       }
              }
    
                            
                         
    
    

