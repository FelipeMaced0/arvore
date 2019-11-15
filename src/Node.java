

/**
 *
 * @author Felipe Macedo
 */

public class Node  {
    
    int key;
    Node right;
    Node left;
    Node father;
    
    
    public Node(int key){
        this.key = key;
    }
    
    public void setRight(Node right){
        this.right = right;
    }
    
    public void setLeft(Node left){
        this.left = left;
    }
    
    public void setFather(Node father){
        this.father = father;
    }
    
    public Node getRight(){
        return right;
    }
    
    public Node getLeft(){
        return left;
    }
    
    public int getKey(){
        return key;
    }
    
    public Node getFather(){
        return father;
    }
    
    @Override
    public String toString(){
        return String.valueOf(key);
    }

    @Override
    public boolean equals(Object x){
        if(x!=null){
            if(x instanceof Node){
                return this.key==((Node)x).key;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.key;
        return hash;
    }
}