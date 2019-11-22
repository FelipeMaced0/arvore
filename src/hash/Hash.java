package hash;

/**
 *
 * @author Aluno
 */
public class Hash {

   TreeAVL tabela[];
   
   public Hash(int tam){
       tabela = new TreeAVL[tam];
       for(int i=0;i<tam;i++){
           tabela[i] = new TreeAVL();
       }
   }
   
   public int functionHash(int key){
       return (key^2)%tabela.length;
   }
   
   public void insert(Node x){
       int p = functionHash(x.key);
       tabela[p].insert(x);
   }
   
   public String imprimir(){
       String out="";
       for(int i=0;i<tabela.length;i++){
           out += i+" "+tabela[i].inOrderTreeWalk();
       }
       return out;
   }
   
    public static void main(String[] args) {
        Hash a = new Hash(100);
    }
}
