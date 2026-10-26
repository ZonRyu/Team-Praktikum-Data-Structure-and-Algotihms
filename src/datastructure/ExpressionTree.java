package datastructure;

import model.ExpNode;
import model.Node;

import java.util.Stack;

/**
 * Class Expression Tree
 * Membangun tree dari ekspresi postfix dan mengevaluasi nilainya.
 */
public class ExpressionTree {
    private ExpNode root;

    public ExpressionTree() {
        this.root = null;
    }

    public ExpNode getRoot() {
        return root;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Konversi ekspresi Infix sederhana ke Postfix (Shunting-yard sederhana)
    private String infixToPostfix(String infix) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isWhitespace(c)) continue;

            if (Character.isLetterOrDigit(c)) {
                result.append(c).append(" ");
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop();
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop()).append(" ");
        }

        return result.toString().trim();
    }

    private int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    // Membangun Expression Tree dari Infix String
    public void buildTreeFromInfix(String infix) {
        String postfix = infixToPostfix(infix);
        String[] tokens = postfix.split("\\s+");
        Stack<ExpNode> stack = new Stack<>();

        for (String token : tokens) {
            if (!isOperator(token.charAt(0)) || token.length() > 1) {
                stack.push(new ExpNode(token));
            } else {
                ExpNode node = new ExpNode(token);
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            }
        }
        this.root = stack.peek();
    }

    // Evaluasi Matematika dari Expression Tree secara Rekursif
    public double evaluate(Node node) {
        if (node == null) return 0;

        // Leaf node (operand)
        if (node.left == null && node.right == null) {
            return Double.parseDouble(node.value);
        }

        // Subtree kiri dan kanan (operator)
        double leftVal = evaluate(node.left);
        double rightVal = evaluate(node.right);

        switch (node.value) {
            case "+": return leftVal + rightVal;
            case "-": return leftVal - rightVal;
            case "*": return leftVal * rightVal;
            case "/":
                if (rightVal == 0) throw new ArithmeticException("Pembagian dengan nol!");
                return leftVal / rightVal;
        }

        return 0;
    }

    // Traversals
    public void inorder(Node node) {
        if (node != null) {
            if (isOperator(node.value.charAt(0)) && node.value.length() == 1) System.out.print("( ");
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
            if (isOperator(node.value.charAt(0)) && node.value.length() == 1) System.out.print(") ");
        }
    }
}