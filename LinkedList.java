import org.w3c.dom.Node;

public class LinkedList<AnyType extends GameObject>{

    private Node head;
    private Node tail;
    private int size;


    public LinkedList(){
        tail = new Node();
        head = new Node(null, null, tail);
        tail.changePrevNode(head);
    }

    public void add(AnyType item){
        Node temp = new Node(item, tail.getPrev(), tail);
        tail.getPrev().changeNextNode(temp);
        tail.changePrevNode(temp);
    }

    public void tick(){
        Node temp = head;
        for(int i = 0; i < size; i++){
            temp = temp.getNext();
            temp.getData().tick();
        }
    }
 
    
    private class Node{
        
        AnyType data;
        Node next;
        Node prev;
        
        public Node(AnyType data, Node prevNode, Node nextNode){
            this.data = data;
            prev = prevNode;
            next = nextNode;
        }

        public Node(){
            data = null;
            next = null;
            prev = null;
        }

        public AnyType getData(){
            return data;
        }

        public Node getPrev(){
            return prev;
        }

        public void changePrevNode(Node newPrev){
            prev = newPrev;
        }

        public Node getNext(){
            return next;
        }

        public void changeNextNode(Node newNext){
            next = newNext;
        }

    }
}
