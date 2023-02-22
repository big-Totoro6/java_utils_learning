package org.example;

import org.junit.Test;

import java.util.Stack;

public class AlphTest {
    @Test
    public void test_valid_parentheses() {
        System.out.println(isValid("{}"));
    }

    public boolean isValid(String s) {
        //1.判断空字符串
        if (s.isEmpty()) return true;
        //2.创建辅助栈
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(')');
            } else if ('[' == c) {
                stack.push(']');
            } else if ('{' == c) {
                stack.push('}');
            } else {
                if (stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //递归合并
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        //我不管下一级干啥 只知道 如果l1为空就返回l2 l2为空就返回l1
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {//然后我需要判断就是l1和l2的值哪个小 谁小就交给谁合并
            l1.next = mergeTwoLists1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists1(l1, l2.next);
            return l2;
        }
    }

    //递归合并
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode pre=preHead;
        while(l1!=null && l2!=null){
            //先判断 l1 和l2 头节点那个值小 将pre指向那个小的节点 并把该节点后移
            if (l1.val<=l2.val){
                pre.next=l1;
                l1=l1.next;
            }else{
                pre.next=l2;
                l2=l2.next;
            }
            //pre 跟着当前指向的节点
            pre=pre.next;
        }
        //合并后最多就一个还没有合并的
        pre.next=l1==null?l2:l1;
        return preHead.next;
    }

}
