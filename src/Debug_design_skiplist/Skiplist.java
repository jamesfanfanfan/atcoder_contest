package Debug_design_skiplist;

import java.util.Random;

class Skiplist {


    public static void main(String[] args) {

    }
    class Node{
        Node next;
        Node lower;
        int val;
        Node(int val){
            this.val = val;
        }
    }

    Node dummyHead = new Node(-1);
    Random rd = new Random();
    public Skiplist() {

    }

    public boolean search(int target) {
        System.out.println("Search:"+target);
        return sc(dummyHead, target);
    }
    boolean sc(Node root, int target){
        //System.out.println(root.val+" "+(root.next != null ? root.next.val : null));
        if(root.val == target) return true;
        if(root.next != null && root.next.val <= target){
            return sc(root.next, target);
        }else if(root.lower != null){
            return sc(root.lower, target);
        }else{
            return false;
        }
    }
    Node dfs(Node root, int val){

        if(root.lower == null){ // we reach the first floor
            while(root.next != null && root.next.val <= val){
                root = root.next;
            }
            if(root.val == val){
                if(rd.nextBoolean()){
                    return root;
                }
                return null;
            }
            Node neo = new Node(val);
            neo.next = root.next;
            root.next = neo;
            if(rd.nextBoolean()){
                return neo;
            }else{
                return null;
            }
        }
        Node add = null;
        if(root.val < val){
            while(root.next != null && root.next.val <= val){
                root = root.next;
            }
            if(root.val == val){
                if(rd.nextBoolean()){
                    return root;
                }
                return null;
            }
            add = dfs(root.lower, val);
        }
        if(add != null){
            Node neo = new Node(val);
            neo.next = root.next;
            root.next = neo;
            neo.lower = add;
            if(rd.nextBoolean()){
                return neo;
            }
        }
        return null;
    }

    public void add(int num) {
        System.out.println("add:"+num);
        if(search(num)) return;
        dfs(dummyHead, num);
        if(rd.nextBoolean()){
            Node neo = new Node(-1);
            neo.lower = dummyHead;
            dummyHead = neo;
        }
    }

    public boolean erase(int num) {
        System.out.println("erase:"+num);
        if(!search(num)) return false;
        //System.out.println(num);
        dele(dummyHead, num);
        return true;
    }
    void dele(Node root, int target){
        ///System.out.println(root.val);
        if(root.next != null && root.next.val < target){
            dele(root.next, target);

        }else if(root.next != null && root.next.val == target){
            root.next = root.next.next;
            if(root.lower != null){
                dele(root.lower, target);
            }
        }else{
            dele(root.lower, target);
        }

    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
//