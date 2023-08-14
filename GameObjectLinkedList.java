

public class GameObjectLinkedList{

    private Node head;
    private Node tail;
    private int size;


    public GameObjectLinkedList(){
        tail = new Node();
        head = new Node(null, null, tail);
        tail.changePrevNode(head);
        size = 0;
    }

    public void add(GameObject item){
        Node temp = new Node(item, tail.getPrev(), tail);
        tail.getPrev().changeNextNode(temp);
        tail.changePrevNode(temp);
        size++;
    }


    public void tick(){
        Node temp = head;
        for(int i = 0; i < size; i++){
            temp = temp.getNext();
            temp.getData().tick();
            if(temp.getData().status() == false){
                temp = temp.getPrev();
                temp.getNext().delete();
            }
        }
    }

    
 
    
    private class Node{
        
        GameObject data;
        Node next;
        Node prev;
        
        public Node(GameObject data, Node prevNode, Node nextNode){
            this.data = data;
            prev = prevNode;
            next = nextNode;
        }

        public Node(){
            data = null;
            next = null;
            prev = null;
        }

        public GameObject getData(){
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

        public void delete(){
            prev.changeNextNode(next);
            next.changePrevNode(prev);
            size--;
        }

    }
}
