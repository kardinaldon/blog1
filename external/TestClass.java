public class TestClass {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

    }
}
