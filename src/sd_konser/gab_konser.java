/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sd_konser;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class gab_konser {

     public record peserta(String id_peserta, String nama,  String no_hp){};
     public record konser( String id_konserc, String namaBand, String genre, String tempat_konser,String kategori ,int harga , String[] jumlah_kursi ){};
     public record transaksi(String id_transaksi,String id_riwayat_transaksi , String id_peserta, String tanggal_pemesanan, String verAdmin, int Total_bayar){};
     public record riwayat_transaksi ( String id_riwayat_transaksi , String id_konser,  String genre, String kursi_dipesan){};
     
     
    public static void main(String[] args) {
       
          Scanner input = new Scanner(System.in);
          LinkedList<riwayat_transaksi> riwayatTrans = new LinkedList<>();
          LinkedList<transaksi> listTransaksi = new LinkedList<>();
          
        peserta[] listPeserta = new peserta[2];
        listPeserta[0] = new peserta("PS01" , "Jeje" , "085643451123");
        listPeserta[1] = new peserta("PS02" , "Bebe" , "087865453315");
        
        konser[] listKonser = new konser [5];
        listKonser[0] = new konser ("C01","Coldplay", "Pop" ,"Jakarta", "Gold", 3000 ,new String []{"1A" , "2A" ,"3A"} );
        listKonser[1] = new konser ("C02" ,"Coldplay","Pop" , "Jakarta", "Silver", 2000, new String []{"1B" , "2B" ,"3B"});
        listKonser[2] = new konser ("C03" ,"Coldplay", "Pop" ,"Jakarta", "Bronze", 3000, new String []{"1C" , "2C" ,"3C"});
        listKonser[3] = new konser ("T01" ,"Twice", "Kpop" ,"Jakarta", "Gold", 5000, new String []{"1A" , "2A" ,"3A"});
        listKonser[4] = new konser("T02" ,"Twice", "Kpop" ,"Jakarta", "Bronze", 9000, new String []{"1B" , "2B" ,"3B"});
        
        

       
    }
}
