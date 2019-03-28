package structures.nonintersectionsets.programs;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("programs1.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("programs2.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("programs3.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("programs4.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLineArgs = br.readLine().split(" ");
        int variablesCount = Integer.parseInt(firstLineArgs[0]);
        int equalitiesCount = Integer.parseInt(firstLineArgs[1]);
        int inequalitiesCount = Integer.parseInt(firstLineArgs[2]);

        Operands operands = new Operands(variablesCount);
        for (int i = 0; i < equalitiesCount; i++) {
            String[] query = br.readLine().split(" ");
            int first = Integer.parseInt(query[0]);
            operands.add(first);
            int second = Integer.parseInt(query[1]);
            operands.add(second);
            operands.union(first, second);
        }

        for (int i = 0; i < inequalitiesCount; i++) {
            String[] query = br.readLine().split(" ");
            int first = Integer.parseInt(query[0]);
            int second = Integer.parseInt(query[1]);
            if (operands.exists(first)
                    && operands.exists(second)
                    && (operands.find(first) == operands.find(second))) {
                System.out.println(0);
                return;
            }
            if (!operands.exists(first)
                    && !operands.exists(second)
                    && first == second) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}

class Operand {
    private final int index;
    private int parent;     //invariant: if parent != index, then size == 0

    public Operand(int index) {
        this.index = index;
        this.parent = index;
    }

    public int getIndex() {
        return index;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}

class Operands {
    //all except 0
    private final Operand[] operands;

    public Operands(int size) {
        operands = new Operand[size + 1];
    }

    public void add(int index) {
        if (operands[index] == null) {
            operands[index] = new Operand(index);
        }
    }

    public void union(int first, int second) {
        first = find(first);
        second = find(second);
        if (first == second) {
            return;
        }
        Operand firstOperand = operands[first];
        Operand secondOperand = operands[second];
        secondOperand.setParent(firstOperand.getIndex());
        return;
    }

    public boolean exists(int i) {
        return operands[i] != null;
    }

    public int find(int i) {
        if (i != operands[i].getParent()) {
            operands[i].setParent(find(operands[i].getParent()));
        }
        return operands[i].getParent();
    }

}