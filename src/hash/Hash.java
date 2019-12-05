package hash;
import java.util.Random;

/**
 *
 * @author Aluno
 */
public class Hash {

   TreeAVL[] table;
   
   public Hash(int tam){
       table = new TreeAVL[tam];
       for(int i=0;i<tam;i++){
           table[i] = new TreeAVL();
       }
   }
   
   public int length(){
       return table.length;
   }
   public int functionHash(int key){
       int p = ((key^3)/3)+((key^2)/2)+key;
       
       return (int)p%table.length;
   }
   
   public void insert(Node x){
       int p = functionHash(x.key);
       table[p].insert(x);
   }
   
   public String imprimir(){
       String out="";
       for(int i=0;i<table.length;i++){
           out += i+" "+table[i].inOrderTreeWalk()+"\n";
       }
       return out;
   }
   
    public static void main(String[] args) {
        Random r = new Random();
        Hash a = new Hash(100);
        
        for(int i=0;i<a.length();i++){
            a.insert(new Node(r.nextInt(101)));
        }
        System.out.print(a.imprimir());
    }
}
