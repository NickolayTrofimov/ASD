import java.util.*;

public class RBTree {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private class Node {
        int value;
        Node left, right, parent;
        boolean color;

        Node(int value) {
            this.value = value;
            this.color = RED;
        }
    }

    private Node root;
    private int nodeCount;

    public RBTree() {
        root = null;
        nodeCount = 0;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        root = bstInsert(root, newNode);
        fixInsert(newNode);
        nodeCount++;
    }

    public boolean search(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) {
                return true;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public void delete(int value) {
        Node nodeToDelete = findNode(root, value);
        if (nodeToDelete != null) {
            deleteNode(nodeToDelete);
            nodeCount--;
        }
    }

    public List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current.value);

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return result;
    }

    public List<Integer> dfs() {
        List<Integer> result = new ArrayList<>();
        dfsInOrder(root, result);
        return result;
    }

    public int size() {
        return nodeCount;
    }

    public void printTree() {
        printTree(root, "", true);
    }

    private Node bstInsert(Node root, Node node) {
        if (root == null) {
            return node;
        }

        if (node.value < root.value) {
            root.left = bstInsert(root.left, node);
            root.left.parent = root;
        } else if (node.value > root.value) {
            root.right = bstInsert(root.right, node);
            root.right.parent = root;
        }

        return root;
    }

    private void fixInsert(Node node) {
        Node parent, grandparent;

        while (node != root && node.parent != null && node.parent.color == RED) {
            parent = node.parent;
            grandparent = parent.parent;

            if (grandparent == null) break;

            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                if (uncle != null && uncle.color == RED) {
                    grandparent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandparent);
                    boolean tempColor = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = tempColor;
                    node = parent;
                }
            }
            else {
                Node uncle = grandparent.left;

                if (uncle != null && uncle.color == RED) {
                    grandparent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandparent);
                    boolean tempColor = parent.color;
                    parent.color = grandparent.color;
                    grandparent.color = tempColor;
                    node = parent;
                }
            }
        }

        root.color = BLACK;
    }

    private Node findNode(Node root, int value) {
        Node current = root;
        while (current != null) {
            if (value == current.value) {
                return current;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    private void deleteNode(Node z) {
        Node y = z;
        boolean yOriginalColor = y.color;
        Node x;

        if (z.left == null) {
            x = z.right;
            transplant(z, z.right);
        }
        else if (z.right == null) {
            x = z.left;
            transplant(z, z.left);
        }
        else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;

            if (y.parent == z) {
                if (x != null) {
                    x.parent = y;
                }
            } else {
                transplant(y, y.right);
                y.right = z.right;
                if (y.right != null) {
                    y.right.parent = y;
                }
            }

            transplant(z, y);
            y.left = z.left;
            if (y.left != null) {
                y.left.parent = y;
            }
            y.color = z.color;
        }

        if (yOriginalColor == BLACK) {
            if (x == null) {
            } else {
                fixDelete(x);
            }
        }
    }

    private void fixDelete(Node x) {
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;

                if (w != null && w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }

                if (w == null) {
                    break;
                }

                if ((w.left == null || w.left.color == BLACK) &&
                        (w.right == null || w.right.color == BLACK)) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.right == null || w.right.color == BLACK) {
                        if (w.left != null) {
                            w.left.color = BLACK;
                        }
                        w.color = RED;
                        rotateRight(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    if (w.right != null) {
                        w.right.color = BLACK;
                    }
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;

                if (w != null && w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }

                if (w == null) {
                    break;
                }

                if ((w.right == null || w.right.color == BLACK) &&
                        (w.left == null || w.left.color == BLACK)) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.left == null || w.left.color == BLACK) {
                        if (w.right != null) {
                            w.right.color = BLACK;
                        }
                        w.color = RED;
                        rotateLeft(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    if (w.left != null) {
                        w.left.color = BLACK;
                    }
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }

        x.color = BLACK;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != null) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }

        x.right = y;
        y.parent = x;
    }

    private void dfsInOrder(Node node, List<Integer> result) {
        if (node == null) return;
        dfsInOrder(node.left, result);
        result.add(node.value);
        dfsInOrder(node.right, result);
    }

    private void printTree(Node node, String indent, boolean isLeft) {
        if (node == null) return;

        if (node.right != null) {
            printTree(node.right, indent + (isLeft ? "│   " : "    "), false);
        }

        System.out.print(indent);
        if (isLeft) {
            System.out.print("└── ");
        } else {
            System.out.print("┌── ");
        }
        System.out.println(node.value + (node.color == RED ? " (R)" : " (B)"));

        if (node.left != null) {
            printTree(node.left, indent + (isLeft ? "    " : "│   "), true);
        }
    }
}