

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

	Node root;
	private boolean exit=false;
	private boolean preExit=false;
	private int index=0;

public void insert(int data) {
		Node node=new Node();
		node.data=data;
		
		root = insertHelper(root,node);
	}
private Node insertHelper(Node root, Node node) {
		
		if(root==null) {
			root=node;
			return root;
		}
		else if(node.data>root.data) {
			root.rightNode = insertHelper( root.rightNode,  node); 
		}
		
		else {
			root.leftNode = insertHelper( root.leftNode,  node); 
		}
		
		return root;
	}
	
public boolean search(int data) {
		
		return searchHelper(root,data);
	}
private boolean searchHelper(Node root, int data) {
		
		if(root==null) return false;
		
		else if(data<root.data) {
			return searchHelper(root.leftNode,data);
		}
        else if(data>root.data) {
        	return searchHelper(root.rightNode,data);
		}
        
		return true;
	
	}

public Node getNode(int data) {
	
	return getNodeHelper(root,data);
	
}
private Node getNodeHelper(Node root, int data) {
	
	if(root==null) return root;
	
	else if(data<root.data) {
		return getNodeHelper(root.leftNode,data);
	}
	else if(data>root.data) {
		return getNodeHelper(root.rightNode,data);
	}
	
	return root;

}

public void remove(int data) {
	
	if(search(data))
	root=removeHelper(root,data);
}
private Node removeHelper(Node root, int data) {
	
	if(data<root.data) {
		root.leftNode=removeHelper(root.leftNode, data);
	}
	else if(data>root.data) {
		root.rightNode=removeHelper(root.rightNode, data);
	}
	else {//node found
		
		if(root.leftNode==null && root.rightNode==null) {
			root=null;
		}
		else if(root.rightNode!=null) {
			 root.data= successor(root);
			 root.rightNode=removeHelper(root.rightNode,root.data);
		}
		else {
			 root.data= predecessor(root);
			 root.leftNode=removeHelper(root.leftNode,root.data);
		}
		
	}
	return root;
}

private int successor(Node root) {
	root=root.rightNode;
	
	while(root.leftNode!=null) {
		root=root.leftNode;
	}
	return root.data;
}
private int predecessor(Node root) {
	root=root.leftNode;
	
	while(root.rightNode!=null) {
		root=root.rightNode;
	}
	return root.data;
}

public void inOrderTraversing() {
	
	inOrderTraversingHelper(root);
}
private void inOrderTraversingHelper(Node node) {
	
	if(node==null) 	return;
	

	inOrderTraversingHelper(node.leftNode);
	System.out.println(node.data);
	inOrderTraversingHelper(node.rightNode);
	
	
}
public void postOrderTraversing() {
	postOrderTraversingHelper(root);

}
private void postOrderTraversingHelper(Node node) {
	
	if(node==null) return;
	
	postOrderTraversingHelper(node.leftNode);
	postOrderTraversingHelper(node.rightNode);
	System.out.println(node.data);
	
}
public void preOrderTraversing() {
	preOrderTraversingHelper(root);

}
private void preOrderTraversingHelper(Node node) {
	
	if(node==null) return;
	
	System.out.println(node.data);
	preOrderTraversingHelper(node.leftNode);
	preOrderTraversingHelper(node.rightNode);
	
}

public void levelTraversing() {
	Queue<Node> queue=new LinkedList<>();
	queue.add(root);
	levelTraversingHelper(queue, getHeight(),true);

}
private void levelTraversingHelper(Queue<Node> queue, int height, boolean exit) {

	int size=queue.size();
	int spaceLength=((int) Math.pow(2, height-1)*4 / size );

	for(int i=0;i<size;i++){
		Node node=queue.poll();
		if(node==null) {
			for(int j=0;j<spaceLength;j++)
				System.out.print(" ");

				queue.add(null);
				queue.add(null);

			continue;
		}
		exit=false;
		for(int j=0;j<spaceLength;j++) {
			if(j!=spaceLength/2)
			System.out.print(" ");
			else
		System.out.print(node.data);
		
		}
		
		queue.add(node.leftNode);
		queue.add(node.rightNode);
		
	}
	System.out.println();
	if (exit) return;
levelTraversingHelper(queue,height,true);
	
}

public void reverser() {
	
	reverserHelper(root);

}
private void reverserHelper(Node node) {
	
	if(node==null) 	return;
	
	Node tempNode=	node.leftNode;
	node.leftNode = node.rightNode;
	node.rightNode = tempNode;
	
	reverserHelper(node.leftNode);
	
	reverserHelper(node.rightNode);
	
}

public int getHeight() {

	return getHeight(root);
}
private int getHeight(Node root) {
	
	if(root == null) return -1;
	
	int leftHeight=getHeight(root.leftNode);
	int rightHeight=getHeight(root.rightNode);

	return max(leftHeight,rightHeight)+1;
}

private int max(int leftHeight, int rightHeight) {
	
	if(leftHeight>rightHeight) return leftHeight;
	return rightHeight;
}

private int getDepth(Node node) {
	return getLevel(node);
}

public int getLevel(Node node) {
	exit=false;
	return getLevelHelper(root,node,-1);

}
private int getLevelHelper(Node root,Node node, int level) {
if(root==null )return level;
level++;
if(root==node) {
	exit=true;
	return level;
}
      
		level =getLevelHelper(root.leftNode,node,level);
		if(exit) return level;
		level =getLevelHelper(root.rightNode,node,level);
		if(exit) return level;
		
		
		level--;
		return level;
	
}

public boolean isTreeFull() {
	 return isTreeFullHelper(root);
	
}
private boolean isTreeFullHelper(Node root) {
	
 if(root==null) return true;
 
 int leftLength=getHeight(root.leftNode);
 int rightLength=getHeight(root.rightNode);

 if((leftLength==rightLength)==false) 
 return false;

 boolean b1=isTreeFullHelper(root.leftNode);
 boolean b2=isTreeFullHelper(root.rightNode);
 
 return (b1 && b2);
	
}

public boolean isTreeComplete() {
	preExit=false;
	int height=getHeight();
	 return isTreeCompleteHelper(root,-1,height);
	
}
private boolean isTreeCompleteHelper(Node root,int count,int height) {

	if(root==null){
		if(count!=height-1 && count!=height) return false; // as long as last or second last level, else return false
        else if(count==height-1 && preExit==false) preExit=true; // if encountered the second last level, the rest of the row should follow the same pattern. exit -> leaving status is true in case a violation happens
		else if(preExit==true && count!=height-1 ) return false; // exit -> true, then count -> height -1, else return false
	
		return true; // (count==height and exit==false ) or ( count==height-1 and exit is just switched to true )
	} 
	count++;	
	
	boolean b1=isTreeCompleteHelper(root.leftNode,count,height);
	
	boolean b2=isTreeCompleteHelper(root.rightNode,count,height);
	
	count--;
	return (b1 && b2);
}

public int getMax() {
	
	return getMaxHelper(root);
}
private int getMaxHelper(Node root) {
	
	if(root==null) return this.root.data;
	
	int leftMax=getMaxHelper(root.leftNode);
	int rightMax=getMaxHelper(root.rightNode);
	
	return max(root.data,max(leftMax,rightMax));
}

public int getSum() {
	
	return getSumHelper(root);
}
private int getSumHelper(Node root) {
	
	if(root==null) return 0;
	
	int leftSum=getSumHelper(root.leftNode);
	int rightSum=getSumHelper(root.rightNode);

	return root.data+leftSum+rightSum;
}

public int getNumberOfLeaves() {
	
	return getNumberOfLeavesHelper(root);
}
private int getNumberOfLeavesHelper(Node root) {
	
	if(root==null) return 0;

	if(root.leftNode==null && root.rightNode==null) return 1;

	int leftLeaves=getNumberOfLeavesHelper(root.leftNode);
	int rightLeaves=getNumberOfLeavesHelper(root.rightNode);

	return leftLeaves+rightLeaves;
}

public int getNumberOfNodes() {
	
	return getNumberOfNodesHelper(root);
}
private int getNumberOfNodesHelper(Node root) {

	if(root==null) return 0;
 
	int leftNodes=getNumberOfNodesHelper(root.leftNode);
	int rightNodes=getNumberOfNodesHelper(root.rightNode);

	return (leftNodes+rightNodes+1);
}

public void minimizeHeight() {
	int []array=new int[getNumberOfNodesHelper(root)];
    index=-1;
	array=storeInOrder(root,array);
	
	root=minimizeHeightHelper(array,0,array.length-1);

	}
public void minimizeHeight(Node root) {
	int []array=new int[getNumberOfNodesHelper(root)];
    index=-1;
	array=storeInOrder(root,array);
	
	root=minimizeHeightHelper(array,0,array.length-1);
}
private Node minimizeHeightHelper(int[] array, int start, int end) {
	
	if(start>end) return null;
	
	int mid=(start+end)/2;
	
	Node node=new Node();
	node.data=array[mid];
	
	node.leftNode=minimizeHeightHelper(array,start,mid-1);
	node.rightNode=minimizeHeightHelper(array,mid+1,end);
	
	return node;
}

private int[] storeInOrder(Node root, int[] array) {
	
	if(root==null) return array;

	array=storeInOrder(root.leftNode,array);
	index++;
	array[index]=root.data;
	
	array=storeInOrder(root.rightNode,array);
	
	return array;
}



class Node{
	
	 Node leftNode;
	 Node rightNode;
	 int data;
	 
}
}

