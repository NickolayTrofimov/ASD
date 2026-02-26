package LinearList;

public class List {
    private Node head;

    public enum AddStrategy{
        START, END
    }

    public List(){
        head = null;
    }

    public List(int data){
        head = new Node(data);
    }

    public List(int[] massive, AddStrategy strategy){}

    public void print(){
        if(head == null){
            System.out.println("Список пуст");
            return;
        }

        Node pointer = head;
        while(pointer != null){
            if (pointer.nextNode == null){
                System.out.println(pointer.data);
            } else{
                System.out.println(pointer.data + ", ");
            }
            pointer = pointer.nextNode;
        }
    }

    public void addInEnd(int data){
        Node node = new Node(data);
        Node pointer = head;
        while(pointer != null){
            if (pointer.nextNode == null){
                pointer.nextNode = node;
            }
            pointer = pointer.nextNode;
        }
    }

    public void addInStart(int data){
        Node node = new Node(data);
        node.nextNode = head;
        head = node;
    }
}
