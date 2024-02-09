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
     public record transaksi(String id_transaksi,String id_riwayat_transaksi , String id_peserta, String NamaPeserta, String tanggal_pemesanan, String verAdmin, int Total_bayar){};
     public record riwayat_transaksi ( String id_riwayat_transaksi , String id_konser,  String genre, String kursi_dipesan){};

    public static void main(String[] args) {
        // TODO code application logic here
        
        peserta[] listPeserta = new peserta[2];
        listPeserta[0] = new peserta("PS01" , "Jeje" , "085643451123");
        listPeserta[1] = new peserta("PS02" , "Bebe" , "087865453315");
        
        konser[] listKonser = new konser [6];
        listKonser[0] = new konser ("C01","Coldplay", "Pop" ,"Jakarta", "Gold", 3000 ,new String []{"1A" , "2A" ,"3A"} );
        listKonser[1] = new konser ("C02" ,"Coldplay","Pop" , "Jakarta", "Silver", 2000, new String []{"1B" , "2B" ,"3B"});
        listKonser[2] = new konser ("C03" ,"Coldplay", "Pop" ,"Jakarta", "Bronze", 3000, new String []{"1C" , "2C" ,"3C"});
        listKonser[3] = new konser ("T01" ,"Twice", "Kpop" ,"Jakarta", "Gold", 9000, new String []{"1A" , "2A" ,"3A"});
        listKonser[4] = new konser("T02" ,"Twice", "Kpop" ,"Jakarta", "Silver", 8000, new String []{"1B" , "2B" ,"3B"});
        listKonser[5] = new konser("T03" ,"Twice", "Kpop" ,"Jakarta", "Bronze", 7000, new String []{"1C" , "2C" ,"3C"});
                
                    Scanner input = new Scanner(System.in);
                       
                    LinkedList<riwayat_transaksi> riwayatTrans = new LinkedList<>();
                    LinkedList<transaksi> listTransaksi = new LinkedList<>();
                    Stack<konser> rakBuku = new Stack<>();
        for(int i = 0; i < listKonser.length; i++){
            rakBuku.push(listKonser[i]);
        }
                   
              //  INI ANTRIAN PESERTA KONSER
              Queue<peserta> pesertaQueue = new LinkedList<peserta>();
              for(int i=0; i<listPeserta.length; i++){
              pesertaQueue.add(listPeserta[i]);
              }
                System.out.println("          ===============================");
               System.out.println("              PROGRAM SISTEM KONSER");
                System.out.println("          ===============================");
               System.out.println("");
              
              int p_menu ;
           do {

            System.out.println("             menu     ");
             System.out.println("===============================");
              System.out.println("+---+-----------------------------+");  
            System.out.println("1. Lihat Konser");
              System.out.println("+---+-----------------------------+");  
            System.out.println("2. Pesan Tiket");
              System.out.println("+---+-----------------------------+");  
            System.out.println("3. Keluar");
              System.out.println("+---+-----------------------------+");  
        
                  System.out.println("");
                  System.out.print("Pilih menu (1/2/3): ");
                  p_menu = input.nextInt();
                  
                 switch(p_menu){
                     case 1 :
                         System.out.println("");
                         System.out.println("+---+-----------------------------+");  
                         System.out.println(" LIST SEMUA KONSER ");
                          System.out.println("+---+-----------------------------+");  
                          System.out.println("");
                         for(konser l : listKonser) {
                             System.out.println("| Id Konser : " + l.id_konserc);
                             System.out.println("+---+-----------------------------+");  
                             System.out.println("| Nama Konser : " + l.namaBand); 
                             System.out.println("+---+-----------------------------+");  
                             System.out.println("| Tempat Konser : " + l.tempat_konser);
                             System.out.println("+---+-----------------------------+");  
                             System.out.println("| Harga Konser : " + l.harga);
                             System.out.println("+---+-----------------------------+");  
                             System.out.println("");
                         }
                         break;
                         
                     case 2 :
                           while(!pesertaQueue.isEmpty()){ 
          System.out.println("===========================");
          System.out.println("  List Konser Yang Tersedia ");
          System.out.println("1. Pop");
          System.out.println("2. Kpop ");
          System.out.println("==========================="); 

                        System.out.println("");
                        peserta p = pesertaQueue.poll();
                        System.out.println("Nama Kamu " + p.nama());
                        System.out.println("");
                        input.nextLine();
                  
                    boolean cari_genre = false;
                     String  pilih_genre;
                     
                    while (true) {
                        System.out.println("");
                         System.out.print("Masukkan Jenis Genre  :");
                  pilih_genre = input.nextLine();
            for (konser concert : listKonser) {
            if (concert.genre().equalsIgnoreCase(pilih_genre)) {
            System.out.println("==========================");
            System.out.println(" List Konser Yang Tersedia ");
            System.out.println("===========================");
            System.out.println("| Nama Konser : " + concert.namaBand  + " | ");
            System.out.println("---------------------------");
              cari_genre = true;
                break; // Stop searching once found
            } 
        }
            if (!cari_genre){
                System.out.println("Genre yang Anda Masukkan Tidak Valid !");
            } else {
               break;
            }
            
              }
 
                  System.out.println("");
                  
                  while(true)  {
                      
          System.out.print("Pilih Nama Band  :");
          String pilih_konser = input.nextLine();
           
                       boolean konserFound = false;
                          System.out.println("==========================");
                          System.out.println(" List Konser Yang Tersedia ");
                          System.out.println("===========================");

            for (konser konsers : listKonser) {
              
                     if(konsers.namaBand().equalsIgnoreCase(pilih_konser)&& konsers.genre.equalsIgnoreCase(pilih_genre) ){
                                                    System.out.println("| No. " + konsers.id_konserc);
                          System.out.println("+---+-----------------------------+");  
                          System.out.println("| Band: " + konsers.namaBand());
                           System.out.println("+---+-----------------------------+");  
                          System.out.println("| Kategori " + konsers.kategori);
                           System.out.println("+---+-----------------------------+");  
                          System.out.println("| Harga Rp." + konsers.harga);
                           System.out.println("+---+-----------------------------+");  
                          System.out.println("| Kursi Yang Tersedia : " + Arrays.toString(konsers.jumlah_kursi()));
                          System.out.println("+---+-----------------------------+");  
                          System.out.println("");
                           konserFound = true;
                     }
            }
            
            if(!konserFound){
                         System.out.println("Konser yang kamu mau tidak tersedia Atau Tidak Sesuai Dengan Genre Yang Dipilih ");
            } else  {
                break;
            }
                  }
                  
                   System.out.println(""); 
                
                    konser selectedKonser = null;
                    String kategoriR = null;
                    int  harga = 0;
                    
                  while(true) {
             System.out.print("Pilih Nomor Kategori Konser : ");
             kategoriR = input.nextLine().toUpperCase();            
             
             boolean kategori = false;
                        
            for (konser c: listKonser) {
            if (c.id_konserc.equals(kategoriR) && c.genre.equalsIgnoreCase(pilih_genre) ) {
                   System.out.println("==========================");
                          System.out.println(" List Konser Yang Tersedia ");
                          System.out.println("===========================");
                          System.out.println("");
                        System.out.println("+---+-----------------------------+");  
                          System.out.println("| No. " + c.id_konserc);
                          System.out.println("+---+-----------------------------+");  
                          System.out.println("| Band: " + c.namaBand());
                          System.out.println("+---+-----------------------------+");  
                          harga = c.harga;
                          System.out.println("| Kategori " + c.kategori);
                           System.out.println("+---+-----------------------------+");  
                          System.out.println("| Harga Rp." + c.harga);
                          System.out.println("+---+-----------------------------+");  
                          System.out.println("| Kursi Yang Tersedia : " + Arrays.toString(c.jumlah_kursi()));
                           System.out.println("+---+-----------------------------+");  
                           selectedKonser = c;
                           kategori = true;
                       
              break;  // Exit the loop once the correct konserc is found
            }                       
            }
                if(!kategori){
                    System.out.println("Nomor Kategori Yang Kamu Masukkan Tidak Valid !");
                } else {
                  
                    break;
                }
                  }
                  
              System.out.println("");
                      String tp = null;
         boolean kursiFound = false;
        int getKategori =0;
         while (true){
             
              System.out.print("Pilih Kursi Kamu ( pisahkan dengan koma apabila lebih dari 1 ) : ");
              tp = input.nextLine();
               String[] listKode = tp.split(", ");
                 getKategori =  listKode.length;
                 
              for (String currentKode : listKode) {
          for (int i = 0; i < listKonser.length; i++) {
              konser k = listKonser[i];
               String[] seats = k.jumlah_kursi;
              
                LinkedList<String> kursi = new LinkedList<>(Arrays.asList(seats));
                
             if(kursi.contains(currentKode) && k.id_konserc.equalsIgnoreCase(kategoriR)){
                  kursiFound = true;
                 kursi.remove(currentKode);
                   String[] listKodeDitemukan = kursi.toArray(new String[0]);
                   
                List<konser> removeListKonser = new LinkedList<>(Arrays.asList(listKonser));
               Iterator<konser> iterator = removeListKonser.iterator(); 
        while (iterator.hasNext()) {
            konser konser = iterator.next();
            if (konser.id_konserc.equals(kategoriR)) {
                iterator.remove();
            }
        }
             konser konsers = new konser(k.id_konserc, k.namaBand, k.genre, k.tempat_konser, k.kategori, k.harga, listKodeDitemukan);

                // Add the updated konser object back to the listKonser
                listKonser[i]=(konsers);
               
             }
 
         }
              }
          if(!kursiFound){
              System.out.println("Tidak valid");
          } else {
              break;
          }
              
         }

             
                    LocalDate tanggal_pembuatan = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String tanggal_pemesanan = tanggal_pembuatan.format(formatter);
                
            String verAdmin = "Jojo";
                       System.out.println("");
            System.out.println("Harga Tiket yang Harus Anda Bayar : " + selectedKonser.harga()*getKategori + "" );
                          System.out.println("");
           double kembalian = 0;
            double nominalPembayaran = 0;
            while ( true) {
            System.out.print("Masukkan Nominal Pembayaran: ");
         nominalPembayaran = input.nextInt();
            
            if(nominalPembayaran == selectedKonser.harga*getKategori) {
                System.out.println("Uang Anda Pas !");
                break;
            } else if(nominalPembayaran > selectedKonser.harga*getKategori){
              kembalian = nominalPembayaran - selectedKonser.harga;
                System.out.println("Kembalian Anda : "+ kembalian);
                break;
            } else {
                System.out.println("Uang Anda Kurang, Masukkan Nominal Yang Valid !");
            }
            }
              
            System.out.println("================================");
            System.out.println(" Transaksi Anda Sebagai Berikut");
            System.out.println("================================");
            System.out.println("Total Pesanan anda " + harga);
           System.out.println("Total Uang Anda  : " + nominalPembayaran);
               if(kembalian > 0)    {
                   System.out.println("Kembalian Anda : " + kembalian);
               }   else {
                   System.out.println("Kembalian Anda  : 0");
               }        
            System.out.println("");
            System.out.println("Tanggal Pemesanan yang Dibuat : "+ tanggal_pemesanan);
            System.out.println("Di verifikasi oleh admin : " + verAdmin);
                               System.out.println("");
            // PEMBUATAN ID 
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
                id_riwayat_transaksi = "RT" + 1;
            } else {
                int panjangListr = riwayatTrans.size();
                int nextLengthP = panjangListr + 1;
                id_riwayat_transaksi = "RT" + nextLengthP;
            }
               
            transaksi t = new transaksi(id_transaksi,id_riwayat_transaksi , p.id_peserta, p.nama, tanggal_pemesanan, verAdmin , selectedKonser.harga()*getKategori);
            riwayat_transaksi rt = new riwayat_transaksi (id_riwayat_transaksi ,selectedKonser.id_konserc , selectedKonser.genre(), tp );
            listTransaksi.add(t);
            riwayatTrans.add(rt);
            
                           } 
            
               // Tabel
                               System.out.println("");
             System.out.println("+---+-----------------------------+");  
             System.out.println("      Riwayat Transaksi Anda  ");
              System.out.println("+---+-----------------------------+");  
                               System.out.println("");
                               
                    for(riwayat_transaksi tr : riwayatTrans){

                         
                        System.out.println("+---+-----------------------------+"); 
                           for(transaksi ta : listTransaksi){
                              if(tr.id_riwayat_transaksi.equals(ta.id_riwayat_transaksi)) {
                                  
                              
                         for (peserta pe : listPeserta){
                         
                            if(pe.id_peserta.equals(ta.id_peserta)){
                        System.out.println("| Nama Pemesan  : " + ta.NamaPeserta);

                            }
                         }
                              }
                           }
                        System.out.println("+---+-----------------------------+");

                        for(konser ks : listKonser){
                            if(tr.id_konser.equals(ks.id_konserc)){
                                System.out.println("| Nama Band : " + ks.namaBand);
                                System.out.println("+---+-----------------------------+");
                                System.out.println("| Kategori Konser : " + ks.kategori);
                                System.out.println("+---+-----------------------------+");
                                System.out.println("| Tempat Konser : " + ks.tempat_konser);
                                System.out.println("+---+-----------------------------+");
                                System.out.println("| Kursi Yang Anda Pesan : " + tr.kursi_dipesan);
                                System.out.println("+---+-----------------------------+");
                            }
                        }

                        for(transaksi ts : listTransaksi){
                             if(tr.id_riwayat_transaksi.equals(ts.id_riwayat_transaksi)) {
                              for (peserta pe : listPeserta){
                            if(pe.id_peserta.equals(ts.id_peserta)){
                            
                        System.out.println("| Total Pesanan Anda : " + ts.Total_bayar );
                            }
                         
                        }
                             }
                        }
                         System.out.println("+---+-----------------------------+"); 
                        
                      
                          System.out.println("");    
                    }
               
                           
            
                       break;  
                      
                     case 3 :
                       System.out.println("Terima kasih telah menggunakan Aplikasi Tiket Konser. Sampai jumpa!");
                   break;
                     default:
                         System.out.println("Pilihan Tidak Sesuai Coba Lagi !");
                 }
                 
           } while(p_menu != 3);
                   
       }
              }