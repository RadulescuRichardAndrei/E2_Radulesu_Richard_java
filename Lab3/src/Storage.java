public interface Storage {

   default int converse(String conv,int storageCap) {
      if(conv.equals("megabyte"))
         return storageCap*1024;
      if(conv.equals("kilobyte"))
         return storageCap*1024*1024;
      if(conv.equals("byte"))
         return storageCap*1024*1024*1024;
      return storageCap;
   }
}
