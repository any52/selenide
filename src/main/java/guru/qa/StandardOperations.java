package guru.qa;

public class StandardOperations {
    public static void main(String[] args) {
        int a = 100;
        int b = 300;
        int c = 250;
        int d = 3;
        int var1 = 10;
        int var2 = 20;
        int var3 = 10;
        System.out.println("Arithmetic operations");
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("b / a = " + (b / a));
        System.out.println("b % a = " + (b % a));
        System.out.println("c % a = " + (c % a));
        System.out.println("a++   = " +  (a++));
        System.out.println("a--   = " +  (a--));
        System.out.println("d++   = " +  (d++));
        System.out.println("++d   = " +  (++d));
        System.out.println("////////////////////");
        System.out.println("Logical operations");
        if(var1==var2 && var2==var3){
            System.out.println("var1, var2, var3 are equal");
        }
        if(var1!=var2 || var2!=var3) {
            System.out.println("var1, var2, var3 are not equal");
        }
        System.out.println("////////////////////");
        System.out.println("Overflow");
        int intMax = 2147483647;
        long longMax = 9223372036854775807L;
        System.out.println("Overflow for int type: " + (intMax+5));
        System.out.println("Overflow for long type: " + (longMax+5));
        System.out.println("////////////////////");
        System.out.println("Combination int and double:");
        System.out.println(10.0+2);
        System.out.println("Combination string, int and double:");
        System.out.println("Hello, world!" + 10.5+1);
    }
}
