public class homework1 {
    static boolean validate(String a){
        if(a == null)
           return false;
    return a.matches("\\+?(0|[1-9]\\d*)");
    }
    static boolean validateS(String c){
        if(c == null)
            return false;
        if(c.length()>1)
            return false;
        else
            return true;
    }
    static boolean LetterCheck(String a, String b){
        for(int i=0; i < a.length(); i++)
            if(b.indexOf(a.charAt(i)) >= 0)
                return true;
        return false;
    }
    static void relations(int n,boolean[][] NGM,String[] NGL,String[] words){
        for (int i=0; i < n; i++)
            for (int j=0; j < n; j++)
                if(i != j && LetterCheck(words[i],words[j])) NGM[i][j] = true;
                else NGM[i][j] = false;

        for (int i=0; i < n; i++){
             StringBuilder sb= new StringBuilder();
            for (int j=0; j < n; j++)
                if(NGM[i][j]){
                    sb.append(words[j]);
                    sb.append(' ');
                }
            if(sb != null) NGL[i] = sb.toString();
        }

    }
    public static void printR(int n,String[] NGL){
        for(int i=0; i < n; i++)
            System.out.println(NGL[i]);
    }

    public static void main(String[] args) {
        int n=0, p=0;
        char[] alph= new char[args.length-2];
        if(validate(args[0]) && validate(args[1])){
                n=Integer.parseInt(args[0]);
                p=Integer.parseInt(args[1]);
            }
            else {
            System.exit(0);
            }

        for(int i = 2; i < args.length; i++) {
            if(validateS(args[i])){
                alph[i-2]=args[i].charAt(0);
            }
            else {
                System.exit(0);
            }

        }

        String[] words = new String[n];
        for(int i = 0; i < n; i++){
            StringBuilder sb =new StringBuilder();
            while (true){
                int pos = (int) (Math.random() * (alph.length+1))-1;
                if(pos<0) break;
                sb.append(alph[pos]);
            }
            words[i]=sb.toString();
        }

        boolean[][] NGM= new boolean[n][n];
        String[] NGL= new String[n];
        relations(n,NGM,NGL,words);
        printR(n,words);
        printR(n,NGL);







    }
}
