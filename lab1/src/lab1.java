public class lab1 {
    static int SumDigit(int n){
        int result=0;
        while(n>0){
            result+= n%10;
            n/=10;
        }
        if(result>9)
            return SumDigit(result);
        else
            return result;


    }
    public static void main(String[] args) {
        System.out.print("Hello World!");
        String[] languages= {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1000000) * 3;
        String binary ="10101";
        String hexa ="FF";
        n += Integer.parseInt(binary, 2) + Integer.parseInt(hexa,16);
        n *= 6;
        System.out.print(n);
        System.out.println();
        int result=SumDigit(n);
        System.out.print(result);
        System.out.print("Willy-nilly, this semester I will learn " + languages[result]);

    }
}
