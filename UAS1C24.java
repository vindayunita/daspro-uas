import java.util.Scanner;

public class UAS1C24 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int jumlahTim = (36 % 3) + 4;
        String[] namaTim = new String[jumlahTim];
        int[][] skorTim = new int[jumlahTim][2];
        int[] totalSkor = new int[jumlahTim];
        boolean dataSudahDiinput = false;
        int menu;

        System.out.println("=== MENU UTAMA ===");

        do {
            System.out.println("\n1. Input Data Skor Tim");
            System.out.println("2. Tampilkan Tabel Skor");
            System.out.println("3. Tentukan Juara");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1:
                    for (int i = 0; i < jumlahTim; i++) {
                        System.out.print("Masukkan nama tim ke-" + (i + 1) + ": ");
                        namaTim[i] = input.nextLine();

                        for (int j = 0; j < 2; j++) {
                            while (true) {
                                System.out.print("Masukkan skor " + namaTim[i] + " untuk Level " + (j + 1) + ": ");
                                int skor = input.nextInt();
                                if (skor >= 0) {
                                    skorTim[i][j] = (skor < 35) ? 0 : skor;
                                    break;
                                } else {
                                    System.out.println("Input tidak valid. Skor harus positif atau nol.");
                                }
                            }
                        }
                        input.nextLine();
                    }
                    dataSudahDiinput = true;
                    System.out.println("Data berhasil diinput. Kembali ke menu utama.");
                    break;

                case 2:
                    if (!dataSudahDiinput) {
                        System.out.println("Tidak ada data yang bisa ditampilkan. Silakan input data terlebih dahulu.");
                        break;
                    }

                    System.out.println("\nTabel Skor Turnamen");
                    System.out.printf("%-15s%-10s%-10s%-10s\n", "Nama Tim", "Level 1", "Level 2", "Total Skor");

                    for (int i = 0; i < jumlahTim; i++) {
                        totalSkor[i] = skorTim[i][0] + skorTim[i][1];

                        if (totalSkor[i] % 2 == 0) totalSkor[i] -= 15;

                        if (skorTim[i][0] > 50 && skorTim[i][1] > 50) totalSkor[i] += 24;

                        System.out.printf("%-15s%-10d%-10d%-10d\n", namaTim[i], skorTim[i][0], skorTim[i][1], totalSkor[i]);
                    }
                    break;

                case 3:
                    if (!dataSudahDiinput) {
                        System.out.println("Tidak ada data yang bisa ditampilkan. Silakan input data terlebih dahulu.");
                        break;
                    }

                    int juaraIndex = 0;
                    boolean seri = false;

                    for (int i = 1; i < jumlahTim; i++) {
                        if (totalSkor[i] > totalSkor[juaraIndex]) {
                            juaraIndex = i;
                            seri = false;
                        } else if (totalSkor[i] == totalSkor[juaraIndex]) {
                            if (skorTim[i][1] > skorTim[juaraIndex][1]) {
                                juaraIndex = i;
                                seri = false;
                            } else if (skorTim[i][1] == skorTim[juaraIndex][1]) {
                                seri = true;
                            }
                        }
                    }

                    if (seri) {
                        System.out.println("Turnamen berakhir seri. Tim terbaik adalah [Nama Anda]");
                    } else {
                        System.out.println("Selamat kepada Tim " + namaTim[juaraIndex] + " yang telah memenangkan kompetisi!");
                    }
                    break;

                case 4:
                    System.out.println("Keluar dari program. Terima kasih!");
                    break;

                default:
                    System.out.println("Menu tidak valid. Silakan pilih ulang.");
            }
        } while (menu != 4);
    }
}
