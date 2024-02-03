/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sd_konser;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class konser_s {

  record Show(String no_urut, String nama_band, String genre, String lokasi, String kategori_tiket, int harga, String[] kursi) {}
    record Peserta(String id_peserta, String nama, String no_hp) {}
    record Transaksi(String id_transaksi, String id_peserta, String id_riwayat_transaksi, String tanggal_pemesanan, String verAdmin, int total_bayar) {}
    record RiwayatTransaksi(String id_riwayat_transaksi, String id_konser, String genre, String kursi_dipesan) {}

    public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

        List<Show> pertunjukanList = new LinkedList<>();
        Queue<Peserta> pesertaQueue = new LinkedList<>();
        List<Transaksi> transaksiList = new LinkedList<>();
        List<RiwayatTransaksi> riwayatTransaksiList = new LinkedList<>();

        boolean pertunjukanDiinisialisasi = false;
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Lihat Konser");
            System.out.println("2. Pesan Tiket");
            System.out.println("3. Lihat Riwayat Transaksi");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1/2/3/4): ");

            int pilihan = input.nextInt();

            inisialisasiPertunjukan(pertunjukanList);

            switch (pilihan) {
            case 1:
                lihatKonser(pertunjukanList);
                break;
            case 2:
                pesanTiket(pertunjukanList, pesertaQueue, transaksiList, riwayatTransaksiList);
                break;
                case 3:
                    lihatRiwayatTransaksi(riwayatTransaksiList);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan Aplikasi Tiket Konser. Sampai jumpa!");
                    System.exit(0);
                    
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }
    }

    private static void lihatKonser(List<Show> pertunjukanList) {
        System.out.println("Tiket yang tersedia:");
        for (Show konser : pertunjukanList) {
            System.out.println("Kode: " + konser.no_urut() + ", Nama Band: " + konser.nama_band() +
                    ", Genre: " + konser.genre() + ", Lokasi: " + konser.lokasi() +
                    ", Kategori Tiket: " + konser.kategori_tiket() + ", Harga: " + konser.harga());
        }
        System.out.println("........................................................");
    }

private static void pesanTiket(List<Show> pertunjukanList, Queue<Peserta> pesertaQueue,
                               List<Transaksi> transaksiList, List<RiwayatTransaksi> riwayatTransaksiList) {
    Scanner input = new Scanner(System.in);

    System.out.print("Masukkan ID Peserta: ");
    String idPeserta = input.next();
    System.out.print("Masukkan Nama Peserta: ");
    String namaPeserta = input.next();
    System.out.print("Masukkan Nomor HP Peserta: ");
    String noHpPeserta = input.next();

    Peserta peserta = new Peserta(idPeserta, namaPeserta, noHpPeserta);
    pesertaQueue.add(peserta);
    
    String kodeKonser;
    Show konserDipesan;
    
    do {
        System.out.print("Masukkan Kode Konser yang Ingin Dipesan: ");
        kodeKonser = input.next();
        konserDipesan = searchKonserByCode(pertunjukanList, kodeKonser);

        if (konserDipesan == null) {
            System.out.println("Kode Konser tidak valid. Silakan coba lagi.");
        }
    } while (konserDipesan == null);

    if (konserDipesan != null) {
        String kursiDipesan;
        
    do {
        System.out.println("Kursi yang tersedia untuk konser " + konserDipesan.nama_band() + ": " + Arrays.toString(konserDipesan.kursi()));
        System.out.print("Masukkan Kursi yang Ingin Dipesan: ");
        kursiDipesan = input.next();

        if (!Arrays.asList(konserDipesan.kursi()).contains(kursiDipesan)) {
            System.out.println("Kursi tidak valid. Silakan coba lagi.");
        }
    } while (!Arrays.asList(konserDipesan.kursi()).contains(kursiDipesan));

        if (Arrays.asList(konserDipesan.kursi()).contains(kursiDipesan)) {
            System.out.print("Masukkan Jumlah Tiket yang Ingin Dipesan: ");
            int jumlahTiket = input.nextInt();

            int totalHarga = jumlahTiket * konserDipesan.harga();

            System.out.println("Total Harga Tiket: " + totalHarga);

            System.out.print("Masukkan Nominal Pembayaran: ");
            int nominalPembayaran = input.nextInt();

            if (nominalPembayaran >= totalHarga) {
                int kembalian = nominalPembayaran - totalHarga;

                System.out.println("Kembalian: " + kembalian);

                LocalDate tanggalPemesanan = LocalDate.now();
    System.out.println("Tanggal Pemesanan: " + tanggalPemesanan);

                System.out.print("Masukkan Verifikasi Admin: ");
                String verAdmin = input.next();

                Transaksi transaksi = new Transaksi(UUID.randomUUID().toString(), idPeserta,
                        UUID.randomUUID().toString(),tanggalPemesanan.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), verAdmin, totalHarga);
                transaksiList.add(transaksi);

                RiwayatTransaksi riwayatTransaksi = new RiwayatTransaksi(transaksi.id_riwayat_transaksi(),
                        kodeKonser, konserDipesan.genre(), kursiDipesan);
                riwayatTransaksiList.add(riwayatTransaksi);

                // Tampilkan Struk Pembayaran
                System.out.println("\n===== Struk Pembayaran =====");
                System.out.println("ID Transaksi: " + transaksi.id_transaksi());
                System.out.println("Nama Peserta: " + peserta.nama());
                System.out.println("Tanggal Pemesanan: " + transaksi.tanggal_pemesanan());
                System.out.println("Kode Konser: " + kodeKonser);
                System.out.println("Kursi Dipesan: " + kursiDipesan);
                System.out.println("Total Harga: " + totalHarga);
                System.out.println("Nominal Pembayaran: " + nominalPembayaran);
                System.out.println("Kembalian: " + kembalian);
                System.out.println("===========================\n");

                System.out.println("Pemesanan Tiket Berhasil!");
            } else {
                System.out.println("Nominal pembayaran kurang. Pemesanan Tiket Gagal!");
            }
        } else {
            System.out.println("Kursi tidak valid. Pemesanan Tiket Gagal!");
        }
    } else {
        System.out.println("Kode Konser tidak valid. Pemesanan Tiket Gagal!");
    }
}

    private static Show searchKonserByCode(List<Show> pertunjukanList, String kodeKonser) {
        for (Show konser : pertunjukanList) {
            if (konser.no_urut().equals(kodeKonser)) {
                return konser;
            }
        }
        return null;
    }

    private static void lihatRiwayatTransaksi(List<RiwayatTransaksi> riwayatTransaksiList) {
        System.out.println("Riwayat Transaksi:");
        for (RiwayatTransaksi riwayatTransaksi : riwayatTransaksiList) {
            System.out.println("ID Riwayat Transaksi: " + riwayatTransaksi.id_riwayat_transaksi() +
                    ", ID Konser: " + riwayatTransaksi.id_konser() + ", Genre: " + riwayatTransaksi.genre() +
                    ", Kursi Dipesan: " + riwayatTransaksi.kursi_dipesan());
        }
        System.out.println("........................................................");
    }
    
private static void inisialisasiPertunjukan(List<Show> pertunjukanList) {
    pertunjukanList.add(new Show("AM01", "Arctic Monkeys", "indie-rock", "Jakarta", "Gold", 1000000, new String[]{"A1a", "A2a", "A3a"}));
    pertunjukanList.add(new Show("AM02", "Arctic Monkeys", "indie-rock", "Jakarta", "Silver", 750000, new String[]{"A1b", "A2b", "A3b"}));
    pertunjukanList.add(new Show("AM03", "Arctic Monkeys", "indie-rock", "Jakarta", "Bronze", 500000, new String[]{"A1c", "A2c", "A3c"}));
    pertunjukanList.add(new Show("TW01", "Twice", "K-pop", "Jakarta", "Gold", 3000000, new String[]{"B1a", "B2a", "B3a"}));
    pertunjukanList.add(new Show("TW02", "Twice", "K-pop", "Jakarta", "Silver", 2750000, new String[]{"B1b", "B2b", "B3b"}));
    pertunjukanList.add(new Show("TW03", "Twice", "K-pop", "Jakarta", "Bronze", 2500000, new String[]{"B1c", "B2c", "B3c"}));
}    
}
