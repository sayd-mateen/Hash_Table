/* Name: Sayd Mateen
*/

import java.util.*;

public class Hash{
	public static void main(String[] args) {
        NodeList[] hashLink = new NodeList[8209];
        ProbeList[] hashProbe = new ProbeList[8209];
		long timeProbe, timeLink;
		short[] arr = new short[8001];
		Random randomGen = new Random();
		for (int i=1; i <= 8000; i++) {
			arr[i] = (short)randomGen.nextInt(32768); 
		}
        //*******************************************//
		// Start of Linked implementation of Hash Table
        for (int i=0; i < 8209 ;i++) {
            NodeList temp = new NodeList();
            hashLink[i] = temp;
        }
		timeLink = System.nanoTime();
		for (int i=1; i <= 8000; i++) {
			insertLink(arr[i], hashLink);
            System.out.println(i);
		}
		for (int i=0; i <= 4000; i++) {
			findLink(i, arr, hashLink);
		}
		for (int i=0; i <= 2000; i++) {
			deleteLink(i, arr, hashLink);
		}
		timeLink = System.nanoTime() - timeLink;
		//******************************************//
		// Start of Probe implementation of Hash Table
        for (int i=0; i < 8209 ;i++) {
            ProbeList temp = new ProbeList();
            hashProbe[i] = temp;
        }
		timeProbe = System.nanoTime();
		for (int i=1; i <= 8000; i++) {
			insertProbe(arr[i], hashProbe);
            System.out.println(i);
		}
		for (int i=0; i <= 4000; i++) {
			findProbe(i, arr, hashProbe);
		}
		for (int i=0; i <= 2000; i++) {
			deleteProbe(i, arr, hashProbe);
		}
		timeProbe = System.nanoTime() - timeProbe;
        System.out.println("Time amount for Linked implementation is: " + timeLink);
		System.out.println("Time amount for Probing implementation is: " + timeProbe);
	}
    //**************************************************************//
	public static void insertLink(short x, NodeList[] hashLinked){
        int key = x % 8209;
        hashLinked[key].add(x);
        //System.out.println(hashLinked[key].listCount);
	}
	public static void findLink(int x, short[] arr, NodeList[] hashLinked){
        int num = arr[x + 2000];
        for (int i=0; i < 8209; i++){
            NodeList get = hashLinked[i];
            if(get.search(num) != -1){
                System.out.println("Found "+ x);
            }
        }
	}
	public static void deleteLink(int x, short[] arr, NodeList[] hashLinked){
        int num = arr[x + 2000];
        for (int i=0; i < 8209; i++){
            NodeList get = hashLinked[i];
            int index = get.search(num);
            if(index != -1){   
                get.remove(index);
                System.out.println("Removed "+ x);
            }
        }
	}
    //*************************************************************//
	public static void insertProbe(short x, ProbeList[] hashProbe){
        int key = x % 8209;
        int count = key;
        if(hashProbe[key].number == -1){
            hashProbe[key].number = x;
            hashProbe[key].count++;
        }else{
            while(hashProbe[count].number != -1){
                count++;
                if(count == 8209){
                    count = 0;
                }
            }
            hashProbe[count].number = x;
            hashProbe[key].count++;
        }
	}
	public static void findProbe(int x, short[] arr, ProbeList[] hashProbe){
        int num = arr[x + 2000];
        int count = num % 8209;
        while(hashProbe[count].number != num){
            count++;
            if(count == 8209){
                count = 0;
            }
        }
        System.out.println("Found "+ x);
	} 
	public static void deleteProbe(int x, short[] arr, ProbeList[] hashProbe){
        int num = arr[x + 2000];
        int key = num % 8209;
        int count = key;
        while(hashProbe[count].number != num){
            count++;
            if(count == 8209){
                count = 0;
            }
        }
        hashProbe[count].number = -1;
        hashProbe[key].count--;
        System.out.println("Removed "+ x);
	}
} 
//*****************************************************************************************//
// This pobed class will help create an array of probe objects for the hash table 
class ProbeList{
    short number = -1;
    int count = 0; 
}
//******************************************************************************************//
//Node class will create a new node with the following attributes for the linked list 
class Node {
    Node next = null;
    Object data = null;
    public Node getNext() {
        return next;
    }
    public void setNext(Node nextValue) {
        next = nextValue;
    }             
        // Node constructor
    public Node(Object value) {
        next = null;
        data = value;
    }
}

// This node list will help create a liked list of nodes 
class NodeList {
    Node head = new Node(null);
    int listCount = 0;    
    // adds the node to the list
    public void add(Object data)
    // appends the specified element to the end of this list.
    {
        Node temp = new Node(data);
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(temp);
        listCount++;
    }
    public int search(int c){
        Node current = head;
        int count = 1;
        while (current.getNext() != null) {
            current = current.getNext();
            if((short)current.data == c) {
                return count;
            }
            count++;
        }
        return -1;
    }
    public void remove(int index)
    {
        Node current = head;
        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        listCount--; 
    }
}
