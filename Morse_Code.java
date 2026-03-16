import java.util.Scanner;
class CustomStack {
    private String[] stack;
    private int top;
    private int capacity;

    public CustomStack(int size) {
        capacity = size;
        stack = new String[capacity];
        top = -1;
    }
    public void push(String item) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow!");
            return;
        }
        stack[++top] = item;
    }
    public String pop() {
        if (isEmpty()) return null;
        return stack[top--];
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("----- STACK HISTORY -----");
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}
class TwoFactorMorse {
    public static int getNumberForLetter(char ch) {
        if (ch >= 'A' && ch <= 'Z') return ch - 'A' + 1;
        return -1;
    }
    public static char getLetterForNumber(int num) {
        if (num >= 1 && num <= 26) return (char) ('A' + num - 1);
        return ' ';
    }
    public static String generateMorse(int digit) {
        StringBuilder code = new StringBuilder();
        if (digit == 0) code.append("-----");
        else if (digit <= 5) {
            for (int i = 0; i < digit; i++) code.append(".");
            for (int i = digit; i < 5; i++) code.append("-");
        } else {
            for (int i = 0; i < digit - 5; i++) code.append("-");
            for (int i = digit; i < 10; i++) code.append(".");
        }
        return code.toString();
    }
    public static String encode(String text) {
        text = text.toUpperCase();
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                encoded.append("| "); // marker for space ' '
                continue;
            }
            int num = getNumberForLetter(ch);
            String numStr = Integer.toString(num);
            for (char digit : numStr.toCharArray()) {
                encoded.append(generateMorse(digit - '0')).append(" ");
            }
            encoded.append("  "); //double space to separate letters
        }
        return encoded.toString().trim();
    }

    public static String decode(String morse) {
        StringBuilder decoded = new StringBuilder();
        String[] words = morse.split("\\|"); 
        for (String word : words) {
            String[] letters = word.trim().split(" {2}"); 
            for (String letter : letters) {
                String[] morseDigits = letter.trim().split(" ");
                StringBuilder numStr = new StringBuilder();
                for (String m : morseDigits) {
                    int digit = decodeMorseDigit(m);
                    if (digit != -1) numStr.append(digit);
                }
                if (numStr.length() > 0) {
                    int number = Integer.parseInt(numStr.toString());
                    decoded.append(getLetterForNumber(number));
                }
            }
            decoded.append(" ");
        }

        return decoded.toString().replaceAll("\\s+", " ").trim();
    }
    public static int decodeMorseDigit(String morseDigit) {
        for (int i = 0; i <= 9; i++) {
            if (generateMorse(i).equals(morseDigit)) return i;
        }
        return -1;
    }
}

public class Morse_Code {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomStack stack = new CustomStack(10);
        while (true) {
            System.out.println("\nTWO-FACTOR MORSE CODE ENCRYPTION");
            System.out.println("1. Encode Message");
            System.out.println("2. Decode Message");
            System.out.println("3. View History (Stack)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter message to encode: ");
                    String msg = sc.nextLine();
                    String encoded = TwoFactorMorse.encode(msg);
                    System.out.println("\nEncoded Morse:\n" + encoded);
                    stack.push("ENCODED: " + msg + " → " + encoded);
                    break;
                case 2:
                    System.out.print("Enter Morse code to decode:\n");
                    String morse = sc.nextLine();
                    String decoded = TwoFactorMorse.decode(morse);
                    System.out.println("\nDecoded Message: " + decoded);
                    stack.push("DECODED: " + morse + " → " + decoded);
                    break;
                case 3:
                    stack.display();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
