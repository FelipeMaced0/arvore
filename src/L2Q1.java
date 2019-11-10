/**
 *
 * @author Felipe Macedo
 */
public class L2Q1 {
    
    
    public static void main(String args[]){
        Arquivo file = new Arquivo("src/L2Q1.in","src/L2Q1.out");
        String out="";
        
        while(!file.isEndOfFile()){
            
            Tree t = new Tree(true);
            String line [] = file.readLine().trim().split(" ");
            
            for(int i=0; i<line.length; i = i+1){
                
                int number = Integer.valueOf(line[i]);
                Node x = new Node(number);
                
                t.insert(x);
                out = out + t.nodeHeight(x) + " ";
            }
            
            out = out.trim();
            
            Node max = t.max();
            Node p = t.predecessor(max);
           
            String alt = String.valueOf(t.nodeHeight(max));
            String pred = p!=null?String.valueOf(p.key):"NaN";
            
            out = out + " max "+max.key+" alt "+alt+" pred "+pred+"\n";
        }
        
        out = out.trim();
        
        file.print(out);
        file.close();
    }
}
