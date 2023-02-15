package two_d_trees;

import java.util.NoSuchElementException;

public class MyLinkedList<T>{
	private int size = 0;
	private LinkedListNode<T> head = null;
	private LinkedListNode<T> tail = null;

	public MyLinkedList() {
	}

	public MyLinkedList(LinkedListNode<T> head) {
		this.head = head;

		// Count size until last node and set tail
		while(head != null && head.next() != null) {
			size++;
			head = head.next();
		}
		size++;
		tail = head;
	}

	public MyLinkedList(T val) {
		head = new LinkedListNode<T>(val);
		tail = head;
		size++;
	}

	public void pushFront(T data) {
		LinkedListNode<T> node = new LinkedListNode<T>(data, head);

		// If the list is empty both head and tail point to the new node
		tail = isEmpty() ? node : tail;
		head = node;
		size++;
	}

	public void pushBack(T data) {
		LinkedListNode<T> node = new LinkedListNode<T>(data);

		// If the list is empty both head and tail point to the new node
		if (isEmpty()) {
			head = node;
		} else {
			tail.setNext(node);
		}
		tail = node;
		size++;
	}

	public T popFront() throws NoSuchElementException {
		if (isEmpty()) throw new NoSuchElementException("ERROR: List is empty.");

		// If the size is 1 then both head and tail should become null
		if (size() == 1) tail = null;
		T item = head.data();
		head = head.next();
		size--;
		return item;
	}

	public T popBack() throws NoSuchElementException {
		if (isEmpty()) throw new NoSuchElementException("ERROR: List is empty.");

		// If the size is 1 then both head and tail should become null
		if (size() == 1) {
			T item = head.data();
			head = null;
			tail = null;
			size--;
			return item;
		}

		// Store popped element's data
		T item = tail.data();

		// Traverse the list with a node 'curr' starting at the head
		// Create a dummy node 'prev' that points to our 'curr' node
		LinkedListNode<T> curr = head;
		LinkedListNode<T> prev = new LinkedListNode<T>(null, curr);
		while(curr.next() != null) {
			prev = curr;
			curr = curr.next();
		}

		// Make 'prev' the new tail and set its next pointer to null
		prev.setNext(null);
		tail = prev;
		size--;
		return item;
	}

	public T head() {
		return head != null ? head.data() : null;
	}

	public LinkedListNode<T> headNode() {
		return head;
	}

	public T tail() {
		return tail != null ? tail.data() : null;
	}

	public int size() {
		return size;
	}

	public void overrideSize(int size) {
		this.size = size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String toString() {
		LinkedListNode<T> curr = head;
		StringBuilder res = new StringBuilder();
		while (curr != null) {
			res.append(curr.data().toString() + " ");
			curr = curr.next();
		}
		return res.toString().trim();
	}
}
