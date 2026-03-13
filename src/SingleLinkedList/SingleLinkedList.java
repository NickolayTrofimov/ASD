package SingleLinkedList;

public class SingleLinkedList {
    private Node head;

    //Создание пустого списка
    public SingleLinkedList(){
        head = null;
    }

    //Создание списка с одним элементом
    public SingleLinkedList(int data){
        head = new Node(data);
    }

    //Создание списка с массивом
    public SingleLinkedList(int[] massive){
        for (int data : massive){
            addInEnd(data);
        }
    }

    //Вывод информации
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
                System.out.println(pointer.data + " -> ");
            }
            pointer = pointer.nextNode;
        }
    }

    //Добавление в конец
    public void addInEnd(int data){
        Node node = new Node(data);
        if(head == null){
            head = node;
        } else {
            Node pointer = head;
            while (pointer != null) {
                if (pointer.nextNode == null) {
                    pointer.nextNode = node;
                    return;
                }
                pointer = pointer.nextNode;
            }
        }
    }

    //Удаление с конца
    public void removeFromEnd(){
        Node pointer = head;
        if(head == null){
            System.out.println("Список пуст");
        } else {
            while(pointer.nextNode.nextNode != null){
                pointer = pointer.nextNode;
            }
            pointer.nextNode = null;
        }
    }

    //Добавление в начало
    public void addInStart(int data){
        Node node = new Node(data);
        node.nextNode = head;
        head = node;
    }

    //Удаление с начала
    public void removeFromStart(){
        if(head == null){
            System.out.println("Список пуст");
        } else {
            head = head.nextNode;
        }
    }

    //Добавление на какую-то позицию упрощённый
    public void addInMiddle(int data, int index){
        Node node = new Node(data);
        Node pointer = head;
        int counter = 0;
        if (head == null){
            System.out.println("Список пуст");
            if (index == 0){
                addInStart(data);
                return;
            } else {
                System.out.println("На данный индекс поставить нельзя");
                return;
            }
        }
        if (index == lastIndex()){
            addInEnd(data);
            return;
        }
        if (index < 0){
            System.out.println("Индекс не может быть отрицательным");
            return;
        }
        if (index > lastIndex()){
            System.out.println("Индекс больше последнего индекса");
        } else {
            while (counter != index - 1){
                pointer = pointer.nextNode;
                counter += 1;
            }
            node.nextNode = pointer.nextNode;
            pointer.nextNode = node;
        }
    }

    //Добавление на какую-то позицию без доп.методов
    public void addInMiddle1(int data, int index){
        Node node = new Node(data);
        Node pointer = head;
        int counter = 0;
        if (head == null){
            System.out.println("Список пуст");
            if (index == 0){
                addInStart(data);
                return;
            } else {
                System.out.println("На данный индекс поставить нельзя");
                return;
            }
        }
        if (index < 0){
            System.out.println("Индекс не может быть меньше 0");
        } else {
            while (counter != index - 1){
                if (pointer == null){
                    System.out.println("Индекс больше последнего индекса");
                    return;
                }
                pointer = pointer.nextNode;
                counter += 1;
            }
            node.nextNode = pointer.nextNode;
            pointer.nextNode = node;
        }
    }

    //Удаление с какой-то позиции
    public void removeFromMiddle(int index){
        Node pointer = head;
        int counter = 0;
        if (head == null){
            System.out.println("Список пуст");
        }
        if (index < 0){
            System.out.println("Индекс не может быть отрицательным");
            return;
        }
        if (index > lastIndex()){
            System.out.println("Индекс больше последнего индекса");
            return;
        }
        if (index == 0){
            removeFromStart();
        }
        if (index == lastIndex()){
            removeFromEnd();
        } else {
            while(counter != index - 1){
                pointer = pointer.nextNode;
                counter += 1;
            }
            pointer.nextNode = pointer.nextNode.nextNode;
        }
    }

    //Удаление с какой-то позиций без доп.методов
    public void removeFromMiddle1(int index){
        Node pointer = head;
        int counter = 0;
        if (head == null){
            System.out.println("Список пуст");
            return;
        }
        if (index < 0){
            System.out.println("Индекс не может быть отрицательным");
            return;
        }
        if (index == 0){
            removeFromStart();
        } else {
            while (counter != index - 1){
                if (pointer == null){
                    System.out.println("Индекс больше последнего индекса");
                    return;
                }
                pointer = pointer.nextNode;
                counter += 1;
            }
            pointer.nextNode = pointer.nextNode.nextNode;
        }
    }

    //Добавление в конец массива
    public void addMassiveInEnd(int[] massive){
        int size = massive.length;
        if (size == 0){
            System.out.println("Масиив пуст, добавлять нечего");
        } else {
            int counter = 0;
            while (counter != size){
                addInEnd(massive[counter]);
                counter += 1;
            }
        }
    }

    //Ещё добавление в конец массива
    public void addMassiveInEnd1(int[] massive){
        if (massive.length == 0){
            System.out.println("Масиив пуст, добавлять нечего");
        } else {
            for (int data : massive){
                addInEnd(data);
            }
        }
    }

    //Добавление на какую-то позицию массива
    public void addMassiveInMiddle(int[] massive, int index){
        if (massive.length == 0){
            System.out.println("Массив пуст, добавлять нечего");
        } else {
            if (index < 0){
                System.out.println("Индекс не может быть отрицательным");
                return;
            }
            if (head == null){
                System.out.println("Списко пуст");
                if (index == 0){
                    for (int data : massive){
                        addInEnd(data);
                    }
                } else {
                    System.out.println("Индекс больше конечного индекса");
                }
            } else {
                Node pointer = head;
                int counter = 0;
                while(counter != index - 1){
                    if (pointer == null){
                        System.out.println("Индекс больше конечного индекса");
                        return;
                    }
                    pointer = pointer.nextNode;
                    counter += 1;
                }
                for (int data : massive){
                    Node node = new Node(data);
                    node.nextNode = pointer.nextNode;
                    pointer.nextNode = node;
                    pointer = node;
                }
            }
        }
    }

    //Удаление начиная с какой-то позиции
    public void removeAllFromMiddle(int index){
        if (head == null){
            System.out.println("Массив пустой, удалять нечего");
            return;
        }
        if (index < 0){
            System.out.println("Индекс не может быть отрицательным");
            return;
        }
        if (index == 0){
            head = null;
        } else {
            int counter = 0;
            Node pointer = head;
            while (counter != index - 1){
                if (pointer == null){
                    System.out.println("Индекс больше конечного индекса");
                    return;
                }
                pointer = pointer.nextNode;
                counter += 1;
            }
            pointer.nextNode = null;
        }
    }

    //Удаление с какой-то позиции по какую-то
    public void removeMassiveFromMiddle(int start, int end){ //Start и end тоже удаляем
        if (head == null){
            System.out.println("Список пуст");
            return;
        }
        if (start < 0 || end < 0){
            System.out.println("Индекс не может быть отрицательным");
            return;
        }
        if (end < start){
            System.out.println("Некорректный диапозон");
        } else {
            Node pointer = head;
            int counter = 0;
            Node beforeStart;
            while(counter != start - 1){
                if (pointer == null){
                    System.out.println("Начальный индекс удаления меньше конечного индекса списка");
                    return;
                }
                pointer = pointer.nextNode;
                counter += 1;
            }
            beforeStart = pointer;
            while(counter != end){
                if (pointer == null){
                    beforeStart.nextNode = null;
                    return;
                }
                pointer = pointer.nextNode;
                counter += 1;
            }
            beforeStart.nextNode = pointer.nextNode;
        }
    }

    //Вывод последнего индекса списка
    public int lastIndex(){
        Node pointer = head;
        int counter = -1;
        if(head == null){
            System.out.println("Список пуст");
            return 0;
        } else {
            while (pointer != null){
                counter++;
                pointer = pointer.nextNode;
            }
        }
        return counter;
    }

    //Размер списка
    public String length(){
        Node pointer = head;
        int counter = 1;
        if(head == null){
            return "Список пуст";
        } else {
            while (pointer != null){
                counter++;
                pointer = pointer.nextNode;
            }
        }
        return "Размер списка: " + counter;
    }

    //Проверка на пустоту
    public boolean empty(){
        boolean flag = false;
        if(head == null){
            flag = true;
            System.out.println("Список пуст");
            return flag;
        }
        return flag;
    }

    //Проверка на наличие элемента
    public boolean elementPresence(int data){
        Node pointer = head;
        boolean flag = false;
        if(head == null){
            System.out.println("Список пуст");
            return flag;
        } else {
            while (pointer != null){
                if(pointer.data == data){
                    flag = true;
                    return flag;
                }
                pointer = pointer.nextNode;
            }
        }
        return flag;
    }

    //Удаления все вхождения какого-то значения
    public void removeNodeWithData(int data){
        if (head == null){
            System.out.println("Список пуст, удалять нечего");
        } else {
            Node pointer = head;
            while (pointer.data == data){
                pointer = pointer.nextNode;
            }
            head = pointer;
            while (pointer != null){
                if (pointer.nextNode.data == data){
                    pointer.nextNode = pointer.nextNode.nextNode;
                }
                pointer = pointer.nextNode;
            }
        }
    }

    //Получение индексов некоторого значения
    public void getIndexes(int data){
        Node pointer = head;
        int counter = 0;
        while(pointer != null){
            if (pointer.data == data){
                System.out.println(counter);
            }
            pointer = pointer.nextNode;
            counter += 1;
        }
    }

    //Перевернуть линейный список за 1 проход
    public void reverse(){
        if (head == null){
            System.out.println("Список пуст");
            return;
        }
        if (head.nextNode == null){
            System.out.println("Список из одного элемента");
        } else {
            Node pointer = head;
            Node previous = null;
            Node next = null;
            while (pointer != null){
                next = pointer.nextNode;
                pointer.nextNode = previous;
                previous = pointer;
                pointer = next;
            }
            head = previous;
        }
    }
}
