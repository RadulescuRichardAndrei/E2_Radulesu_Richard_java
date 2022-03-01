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
    static boolean letterCheck(String a, String b){
        for(int i=0; i < a.length(); i++)
            if(b.indexOf(a.charAt(i)) >= 0)
                return true;
        return false;
    }
    static void relations(int n,boolean[][] NGM,String[] NGL,String[] words){
        for (int i=0; i < n; i++)
            for (int j=0; j < n; j++)
                if(i != j && letterCheck(words[i],words[j])) NGM[i][j] = true;
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
        System.out.println();
    }

    //bonus
    public static void cycle3(int n, boolean[][] NGM,String[] words){
        int i, j, k;
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                if(NGM[i][j]){
                   for(k=0;k<n;k++)
                       if(NGM[j][k] && NGM[i][k])
                       {
                           StringBuilder sb =new StringBuilder();
                           sb.append(i);
                           sb.append(' ');
                           sb.append(j);
                           sb.append(' ');
                           sb.append(k);
                           sb.append(' ');
                           System.out.println(sb);
                           return;
                       }

                }

    }


    public static void main(String[] args) {
        int n=0, p=0;
        char[] alph= new char[args.length-2];
        long start =System.currentTimeMillis();

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
        //printR(n,words);
        //printR(n,NGL);
        //cycle3(n,NGM,words);


        long end=System.currentTimeMillis();
        double time= (double) (end-start);
        System.out.print(time*1000);
        System.out.print(" nanoseconds");


/*
* Bonus
* Can you find the largest possible k?
* short answear no
* longer...
* If we represent the words as nodes in a graph and then the edges between 2 nodes exist if the words have at least 1 letter from the alphabet in common.
* The problem then becomes:  finding a number k which represents the size of the biggest cycle in the graph (k >= 3)
* It can be proven the problem of finding Hamiltonian cycle, which is in NP-compl., reduces to this problem so they are polynomial equivalent.
* So there is no efficient algorithm that can solve this problem.
*
* For a small n we can use backtracking to find all cycles and determine the greatest k.
* Using dfs we visit the nodes in the graph and check if the current node has an edge with the starting node and update k.
* --------------------
* for k=3 function cycle3
*
* */

    }
}
