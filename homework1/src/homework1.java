public class homework1 {
    static boolean validate(String a){
        if(a == null)
           return false;
    return a.matches("^+?(0|[1-9]\d*)$");
    }
    static boolean validateS(String c){
        if(c == null)
            return false;
        if(c.length()>1)
            return false;
        else
            return Character.isLetter(c.charAt(0));
    }

    public static void main(String[] args) {
        int n,p;
        String[] alph;
        if(validate(args[0]) && validate(args[1])){
            n=Integer.parseInt(args[0]);
            p=Integer.parseInt(args[1]);
            }
        for(int i = 2; i < args.length; i++) {
            if(validateS(argc[i]))
                alph[i-2]=args[i];
        }

    }
}
