public class Test {

    // Method main utama (entry point)
    public static void main(String args[]) {
        System.out.println("main(String args[]) dipanggil (Utama)");
        
        // Memanggil method main yang di-overload
        main("PBO Pertemuan 8");
        main(100);
    }

    // Overload 1: Parameter String tunggal
    public static void main(String arg) {
        System.out.println("main(String) dipanggil: " + arg);
    }

    // Overload 2: Parameter int tunggal
    public static void main(int arg) {
        System.out.println("main(int) dipanggil: " + arg);
    }
}
