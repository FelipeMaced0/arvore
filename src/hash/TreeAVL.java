package hash;



/**
 *
 * @author Felipe Macedo
 */
public class TreeAVL {
    
    Node root;
    boolean toRight=true;//flag that determines if equal elements are allowed
    
    public TreeAVL(){
    }
    
    public TreeAVL(boolean toRight){
        this.toRight = toRight;
    }
    
    public void setFlag(boolean toRight){
        this.toRight = toRight;
    }
    
    public boolean getFlag(){
        return toRight;
    }
    
    public void rotations(Node x){
        //X está a direita de seu pai
        
        if(x.father.right != null && x.key == x.father.right.key){
            
            //O pai de x está a direita de seu pai. Possibilidade direita direita
            if(x.father.father.right!= null && x.father.key == x.father.father.right.key){
                
                this.rotateLeft(x.father.father);
                
            }
            //O pai de x está a esquerda de seu pai. Possibilidade esquerda direita
            else{
                
                this.rotateLeft(x.father);
                this.rotateRight(x.father);//pai de x mudou 
            }
        }
        //X está a esquerda de seu pai
        else{
            
            //O pai de x está a esquerda de seu pai. Possibilidade esquerda esquerda
            if(x.father.father.left != null && x.father.key == x.father.father.left.key){
                
                this.rotateRight(x.father.father);
            }
            //O pai de x está a direita de seu pai. Possibilidade direita esquerda
            else{
                
                this.rotateRight(x.father);
                this.rotateLeft(x.father);//pai de x mudou
            }
        }
    }
    
    public void insert(Node z){
       int b;
       Node x = root;
       Node y = null;
       
       while (x != null){
           y = x;
          
           if(z.key < x.key){
               x = x.left;
           }
           else if(this.toRight || z.key>x.key){
                x = x.right;
           }
           else{
               x = null;
           }
       }
       
       z.father = y;
       
       if(y==null){
           root = z; 
       }
       else if (z.key<y.key){
           y.left = z;
       }
       else if(this.toRight || z.key>y.key){
           y.right = z;
       }
       
       if(z.father!= null && z.father.father!=null){
            b = this.balance(z.father.father);
            if(b>1){
                this.rotations(z);
            }
       }
    }
    
    public Node delete(Node z){
        Node y,x;
        
        if(this.search(root,z.key)!=null){

            if(z.right==null || z.left==null){
                y = z;
            }

            else{
                y = this.sucessor(z);
            }

            if(y.left!=null){
                x = y.left;
            }
            else{
                x = y.right;
            }

            if(x!=null){
                x.father = y.father;
            }

            if(y.father==null){
                root = x;
            }
            else if(y == y.father.left){
                y.father.left = x;
            }
            else{
                y.father.right = x;
            }

            if(y!=z){
                z.key = y.key;
            }

            return y;
        }
        return null;
    }
    
    
    public int balance(Node x){
        int out = 0;
        if(x!=null){
            return Math.abs(this.treeHeight(x.right)-this.treeHeight(x.left));
        }
        return out;
    }
    
    //rotate a node to right
    public void rotateRight(Node x){
        Node y;
        
        y = x.left;
        x.left = y.right;
        
        if(y.right!=null){
            y.right.father = x;
        }
        
        y.father = x.father;
        
        if(y.father==null){
            this.root = y;
        }
        else if(x.equals(x.father.left)==true){
            x.father.left = y;
        }
        else{
            x.father.right = y;
        }
        
        y.right = x;
        x.father = y;
    }
    
    //rotate a node to left
    public void rotateLeft(Node x){
        Node y;
        
        y = x.right;
        x.right = y.left;
        
        if(y.left!=null){
            y.left.father = x;
        }
        
        y.father = x.father;
        
        if(y.father==null){
            this.root = y;
        }
        else if(x.equals(x.father.left)){
            x.father.left = y;
        }
        else{
            x.father.right = y;
        }
        
        y.left = x;
        x.father = y;
    }
    
    //Returns the height of a node
    public int nodeHeight(Node x){
        int c = 0;
        //foi comprar cigarro
        while(x!=null && x.father!=null){
            x = x.father;
            c = c+1;
        }
        return c; 
    }
    
    //Returns the sum of keys begining with x
    public int sum(Node x){
        int R,L;
        
        if(x!=null){
            R = sum(x.right);
            L = sum(x.left);
            
            return R+L+x.key;
        }
        
        return 0;
    }
    
    //Returns the height of the tree
    public int treeHeight(Node x){
        int R;
        int L;
        
        
        if(x!=null){
            R = treeHeight(x.right);
            L = treeHeight(x.left);
            
            
            if(L<R){
                return R+1;
            }
            else{
                return L+1;
            }
        }
        
        return 0;
    }
    
    public Node search(Node x, int key){
        
        while(x!=null && key!=x.key){
            if(key<x.key){
                x = x.left;
            }
            else{
                x = x.right;
            }
        }
        return x;
    }
    
    public Node min(Node x){
        while(x.left!=null){
            x = x.left;
        }
        return x;
    }
    
    //Returns the minimum value of the tree
    public Node min(){
        return min(root);
    }
    
    public Node max(Node x){
        while(x.right!=null){
            x = x.right;
        }
        return x;
    }
    
    //Returns the maximum value of the tree
    public Node max(){
        return max(root);
    }
    
    //Returns the sucessor of a node
    public Node sucessor(Node x){
        Node y;
        
        if(x.right!=null){
            return this.min(x.right);
        }
        y = x.father;
        while(y!=null && y.right!=null && x.key == y.right.key){
            x = y;
            y = y.father;
        }
        return y;
    }
    
    //Returns the predecessor of a node
    public Node predecessor(Node x){
        Node y;
        
        if(x.left != null){
            return max(x.left);
        }
        
        y = x.father;
        
        while(y!=null && y.left != null && x.key == y.left.key){
            x = y;
            y = y.father;
        }
        return y;
    }
    
    public String inOrderTreeWalk(){
        return inOrderTreeWalk(this.root);
    }
    
    public String inOrderTreeWalk(Node x){
        String out="";
        if(x!=null){
            out += inOrderTreeWalk(x.left)+" "+x.key+" "+inOrderTreeWalk(x.right);
        }
        return out;
    }
    
    public String subInOrderTreeWalk(Node x){
        String out="";
        int sum;
        if(x!=null){
            sum = sum(x.right)-sum(x.left); 
            out += subInOrderTreeWalk(x.left)+" "+x.key+" ("+sum+") "+subInOrderTreeWalk(x.right);
        }
        return out.trim();
    }
    
    public String heightInOrderTreeWalk(){
        return heightInOrderTreeWalk(this.root, 0);
    }
    
    public String heightInOrderTreeWalk(Node x , int height){
        String out="";
        if(x!=null){
            height = nodeHeight(x); 
            out += heightInOrderTreeWalk(x.left, height+1)+" "+x.key+" ("+height+") "+heightInOrderTreeWalk(x.right,height+1);
        }
        return out.trim();
    }
    
    public static void main(String args[]){
        TreeAVL t = new TreeAVL();
        Node a = new Node(3);
        Node b = new Node(2);
        Node c = new Node(1);
        
        t.insert(a);
        t.insert(b);
        t.insert(c);
        
        System.out.println(t.treeHeight(b));
        
    }
}