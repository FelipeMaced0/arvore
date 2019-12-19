/**
 *
 * @author Felipe Macedo
 */
public class Tree {
    
    Node root;
    boolean toRight;//flag that determines if equal elements are allowed
    
    public Tree(){
    }
    
    public Tree(boolean toRight){
        this.toRight = toRight;
    }
    
    public void setFlag(boolean toRight){
        this.toRight = toRight;
    }
    
    public boolean getFlag(){
        return toRight;
    }
    
    public void insert(Node z){
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
       
    }
    
    public Node delete(Node z){
        Node y,x;
        
        z = this.search(root,z.key);
        if(z != null){


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
    
    //rotate a node to right
    public void rotateRight(Node x){
        Node y;
        
        y = x.left;
        x.left = y.right;
        y.right.father = x;
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
        y.left.father = x;
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
        while(x!=null){
            x = x.father;
            c+=1;
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
    
    public int pred_suce(Node x){
        int pred=0,suce=0;
        
        pred += sum(x.right);
        suce += sum(x.left);
        
        while(x!=null){
            if(x.father != null){
                if(x.father.left != null && x.key == x.father.left.key){
                    suce += sum(x.father.right)+x.father.key;
                }
                else{
                    pred += sum(x.father.left)+x.father.key;
                }
            }
            x = x.father;
        }
        return pred-suce;
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
        Tree t = new Tree();
        Node a = new Node(0);
        Node b = new Node(1);
        Node c = new Node(2);
        
        t.insert(a);
        t.insert(b);
        t.insert(c);
        
        System.out.println(t.pred_suce(c));
        
    }
}