package lab4;

import java.util.LinkedList;

public class Hashtable {
    int count = 1;
    int numCollisions = 0;
    public LinkedList [] myTable;
    
    public Hashtable(int size){
        myTable = new LinkedList [size];
        for(int i = 0; i < myTable.length; i++){
            myTable[i] = new LinkedList();
        }
    }
    
    public String buildSublists(iNode sentence,  int turtle, int rabbit, int listSize, iNode x){
        /* Debug Here
         System.out.println("In the insert method table length = " + myTable.length);
         System.out.println("n = " + n);
         System.out.println("min = " + min);
         System.out.println("max = " + max);
         System.out.println("listSize =  " + listSize);
         */
        
        String temp = "{";
        iNode copy = sentence;
        
        /* Rabbit is at the end therefore terminate */
        if(rabbit == listSize+1){
            //	System.out.println("Max Cap hit: ");
            return null;
        }
        else{
            for(int i = turtle; i < rabbit; i++){
                temp = temp + copy.element;
                copy=copy.next;
            }
            temp = temp + "}";
            x = new iNode(temp, x);
            
            while(x!= null){
                System.out.println("    S" + count + "= " + x.element);
                count++;
                int key = hashCode(x.element);
                int hashKey = key % (listSize);
                System.out.println("       Hash-key = " + key + " % " + listSize + " = " + hashKey);
                hashTable(key, hashKey);
                x = x.next;
            }
        }
        sentence= sentence.next;
        buildSublists(sentence, turtle+1, rabbit+1, listSize, x);
        return temp;
    }
    
    private void hashTable(int key, int hashKey) {
        System.out.println("		Hash Table = [ Key-> " + key + ", hashkey-> " + hashKey + " ]");
        
        if(myTable[hashKey].isEmpty()){
            System.out.println(						"Insert key " + key + " at the " + hashKey + "th position of the array. ");
            myTable[hashKey].add(key);
        }
        else{
            numCollisions++;
            int counter = 0;
            myTable[hashKey].add(counter, key);
            counter++;
            System.out.println("			The number of collisions: " + numCollisions);
        }
        
    }
    private int hashCode(Object cargo)  {
        int temp = 0;
        int finalInt = 0;
        String s = (String) cargo;
        for(int i = 1 ; i < s.length (); i ++){
            char character = s.charAt(i);
            int ascii = (int) character; //convert the first character
            finalInt = finalInt + ascii;
        }
        return finalInt; 
    }
    public void print(){
        for(int i = 0; i < myTable.length; i++){
            System.out.println("hashtable[" + i + "] = " + myTable[i] );
        }
    }
    
}

