/**
 *
 * @author Felipe Macedo
 */
public class L2Q3 {
    
    
    public static void main(String args[]){
        
        Arquivo file = new Arquivo("src/L2Q3.in","src/L2Q3.out");
        String out="";
        
        while(!file.isEndOfFile()){
            
            Tree t = new Tree(true);
            String line [] = file.readLine().trim().split(" ");
            
            for(int i=0; i<line.length-1; i=i+2){
                
                if(line[i].equals("a")){
                    
                    int number = Integer.valueOf(line[i+1]);
                    t.insert(new Node(number));
                    
                }else{
                    
                    int number = Integer.valueOf(line[i+1]);
                    Node x = new Node(number);
                    
                    if(t.delete(x)==null){
                        t.insert(x);
                    }
                }
            } 
            out = out + t.heightInOrderTreeWalk() + "\n";
        }
        
        out = out.trim();
        
        file.print(out);
        file.close();
    }
}
