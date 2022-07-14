public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean isPalindrome(ListNode head){
        if(head == null || head.next == null)
            return true;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = head;
        while(fast != null && fast.next != null){
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        pre.next = null;
        ListNode cur1 = head;
        ListNode posthead = reverse(slow);
        ListNode cur2 = posthead;
        while(cur1 != null && cur2 != null){
            if(cur1.val != cur2.val)
                return false;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true; 
    }

    public ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
