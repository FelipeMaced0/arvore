/**
 *
 * @author Felipe Macedo
 */
public class L2Q2 {
    
    public static void main(String args[]){
        Arquivo file = new Arquivo("src/L2Q2.in","src/L2Q2.out");
        String out = "";
        while(!file.isEndOfFile()){
            Tree t = new Tree(false);
            String line [] = file.readLine().trim().split(" ");
            for(String i: line){
                int number = Integer.valueOf(i);
                t.insert(new Node(number));
            }
            out += t.subInOrderTreeWalk(t.root)+"\n";
        }
        out = out.trim();
        file.print(out);
        file.close();
    }
}