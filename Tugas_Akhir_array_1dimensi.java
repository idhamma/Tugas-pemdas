/*
Nama : Idham Muhamad Akbar

Tanggung jawab : membuat source code, membuat penjelasan, mengetik fail.
 */
import java.util.Scanner;

public class Tugas_Akhir_array_1dimensi {
    final static int MAX_SISWA = 10;
    final static int MAX_MATPEL = 10;
    final static int MAX_SEMESTER = 2;
    /* menyimpan nama siswa */
    static String[] siswa = new String[MAX_SISWA];
    /* menyimpan nama matpel per siswa tiap semester, semester ganjil=0, genap=1 */
    static String[] siswaMatpel = new String[MAX_MATPEL];
    /* menyimpan nilai matpel per siswa tiap semester, semester ganjil=0, genap=1 */
    static double[][][] siswaMatpelNilai = new double[MAX_SISWA][MAX_SEMESTER][MAX_MATPEL];
    /* menyimpan banyak siswa */
    static int banyakSiswa = 0;
    /* menyimpan banyak matpel */
    static int banyakSiswaMatpel = 0;
    public static String konversiNilai(double nilai){
        String konversi = null;
        if (81 < nilai && nilai <100 ) konversi = "A";
        else if (75 < nilai && nilai <= 80 ) konversi = "B+";
        else if (69 < nilai && nilai <= 75 ) konversi = "B";
        else if (65 < nilai && nilai <= 69 ) konversi = "C+";
        else if (59 < nilai && nilai <= 65 ) konversi = "C";
        else if (55 < nilai && nilai <= 59 ) konversi = "D+";
        else if (40 < nilai && nilai <= 55 ) konversi = "D";
        else konversi = "E";
        return konversi;
    }

    public static void tambahSiswa(String nama){
        siswa[banyakSiswa] = nama;
        banyakSiswa++;
    }
    public static void tambahMatpel(String matpel){
        for (int i=0;i<MAX_SISWA;i++){
            siswaMatpel[banyakSiswaMatpel] = matpel;
        }
        banyakSiswaMatpel++;
    }
    public static int cariSiswa(String nama){
        int kodesiswa=-1;
        for (int i =0; i<banyakSiswa;i++){
            if (nama.equals(siswa[i])) kodesiswa = i;
        }
        return kodesiswa;
    }
    public static int cariMatpel(String matpel){
        int kodematpel=-1;
        for (int i =0; i<banyakSiswaMatpel;i++){
            if (matpel.equals(siswaMatpel[i])) kodematpel = i;
        }
        return kodematpel;
    }
    /*
    nama: nama siswa
    semester: semester, 0 untuk ganjil, 1 untuk genap
    matpel: nama matpel
    nilai: nilai matpel tiap semester
    */
    public static void isiDataSiswa(String nama, int semester, String matpel, int nilai){
        int index_siswa=0;
        int index_mapel =0;

        index_siswa = cariSiswa(nama);
        index_mapel = cariMatpel(matpel);
        if (index_mapel > -1 || index_siswa > -1) siswaMatpelNilai[index_siswa][semester][index_mapel] = (double) nilai;
    }

    public static void printSiswa(){
        System.out.print("\nSISWA:");
        for (int i = 0; i <banyakSiswa;i++){
            System.out.print(" " + siswa[i]);
        }
        System.out.print("\n");
    }
    public static void printMatpel(){
        System.out.print("MATA PELAJARAN:");
        for (int i = 0; i <banyakSiswaMatpel;i++){
            System.out.print(" " + siswaMatpel[i]);
        }
        System.out.print("\n");
    }
    public static void printRaportSiswa(String nama){

        int index_siswa = cariSiswa(nama);
        if (index_siswa > -1) {
            String nilai_huruf = "";

            System.out.println(">> BEGIN RAPORT <<\n");
            System.out.println("Nama: " + nama);
            //looping sebanyak jumlah semester
            for (int h =0;h<MAX_SEMESTER;h++){
                if (h==0) System.out.println("Semester: Ganjil");
                else if (h==1) System.out.println("\nSemester: Genap");

                System.out.printf("%-20s| %9s| %14s\n", "Mata Pelajaran", "Nilai", "Nilai Huruf ");
                System.out.println("-----------------------------------------------");
                //looping sebanyak mata pelajaran yang ada
                for (int i = 0; i < banyakSiswaMatpel; i++) {
                    nilai_huruf = konversiNilai(siswaMatpelNilai[index_siswa][h][i]);
                    System.out.printf("%-20s| %9.1f| %14s\n", siswaMatpel[i], siswaMatpelNilai[index_siswa][0][i], nilai_huruf);
                }
            }
            System.out.println("\n>> END RAPORT <<");
        }
    }

    public static int cariJuara(int semester){
        float []jumlah = new float[banyakSiswa];
        int index_siswa= 0;
        for (int i =0;i<jumlah.length;i++){
            for (int j=0;j<banyakSiswaMatpel;j++){
                jumlah[i] += siswaMatpelNilai[i][semester][j];
            }
            if (i>0 && (jumlah[i]/banyakSiswaMatpel) > (jumlah[i-1]/banyakSiswaMatpel) )index_siswa = i;
        }
        return index_siswa;
    }

    public static void hitungNilai(int semester){
        for (int i = 0;i<banyakSiswa;i++){
            System.out.print(siswa[i]);
            //memasukan banyak nilai huruf
            int A=0,B_plus=0,B=0,C_plus=0,C=0,D_plus=0,D=0,E=0;
            for (int j =0;j<banyakSiswaMatpel;j++){
                double nilai_semester=0;
                String nilai_huruf = "";
                nilai_semester = siswaMatpelNilai[i][semester][j];
                nilai_huruf = konversiNilai(nilai_semester);

                switch (nilai_huruf){
                    case "A" -> A++;
                    case "B+"-> B_plus++;
                    case "B" -> B++;
                    case "C" -> C++;
                    case "C+"-> C_plus++;
                    case "D+"-> D_plus++;
                    case "D" -> D++;
                    case "E" -> E++;
                }
            }
            //menghitung jumlah nilai huruf
            if (A>0) System.out.printf(" A:%d",A);
            if (B_plus>0) System.out.printf(" B+:%d",B_plus);
            if (B>0) System.out.printf(" B:%d",B);
            if (C_plus>0) System.out.printf(" C+:%d",C_plus);
            if (C>0) System.out.printf(" C:%d",C);
            if (D_plus>0) System.out.printf(" D+:%d",D_plus);
            if (D>0) System.out.printf(" D:%d",D);
            if (E>0) System.out.printf(" E:%d",E);
            System.out.print("\n");
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(true){
            String input = scan.nextLine();
            if (input.equals("")) break;
            String []input_split = input.split(" ");
            int semester=-1;

            switch (input_split[0]){
                case "SISWA" -> tambahSiswa(input_split[1]);
                case "MATPEL" -> tambahMatpel(input_split[1]);
                case "PRINT_RAPORT" -> printRaportSiswa(input_split[1]);
                case "PRINT_SISWA" -> printSiswa();
                case "PRINT_MATPEL" -> printMatpel();
                case "NILAI" -> {
                    int nilai = Integer.parseInt(input_split[4]);
                    if (input_split[2].equals("GANJIL")) semester = 0;
                    else if (input_split[2].equals("GENAP")) semester = 1;
                    if (semester == -1) continue;
                    isiDataSiswa(input_split[1],semester,input_split[3],nilai);
                }
                case "CARI_JUARA" ->{
                    if (input_split[1].equals("GANJIL")) semester = 0;
                    else if (input_split[1].equals("GENAP")) semester = 1;
                    if (semester == -1) continue;
                    System.out.printf("JUARA_1 %s %s\n",input_split[1],siswa[cariJuara(semester)]);
                }
                case "HITUNG_NILAI" ->{
                    if (input_split[1].equals("GANJIL")) semester = 0;
                    else if (input_split[1].equals("GENAP")) semester = 1;
                    if (semester == -1) continue;
                    hitungNilai(semester);
                }
            }
        }
    }
}