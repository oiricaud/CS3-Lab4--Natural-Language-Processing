package lab4;


public class iNode {
		Object element; 
		iNode next;

		public iNode () { 
			element = 0; 
			next = null; 
		} 
		public iNode(String givenval){
			element = givenval;
		}

		public iNode (Object element, iNode next) { 
			this.element = element; 
			this.next = next; 
		} 
		
		public int size(iNode x){
			int size = 0;
			while(x!=null){
				size++;
				x = x.next;
			}
			return size++;
		}

		public String toString () { 
			return element + ""; 
		} 
		public Object getData(){
			return element;
		}
		public iNode getNext(){
			return next;
		}
		public iNode prev(iNode head, iNode tail){
			iNode previous = null;
			while(head != null){
				if(head.next == tail){
					previous = head;
					return previous;
				}
				head = head.next;
			}
			return previous;

		}
		public static void printList(iNode x){
			if (x!= null){
				System.out.print(x.element+" ");
				printList(x.next);
			}
		} 
	}

