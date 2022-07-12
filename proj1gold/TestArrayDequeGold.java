import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> buggy = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StringBuilder mes = new StringBuilder("");
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            if (correct.size() == 0) {
                int addNumber = StdRandom.uniform(1000);
                int x = StdRandom.uniform(2);
                if (x == 0) {
                    mes.append("addFirst(" + addNumber + ")\n");
                    buggy.addFirst(addNumber);
                    correct.addFirst(addNumber);
                } else {
                    mes.append("addLast(" + addNumber + ")\n");
                    buggy.addLast(addNumber);
                    correct.addLast(addNumber);
                }
            } else {
                int x = StdRandom.uniform(4);
                int addNumber = StdRandom.uniform(1000);
                Integer c = null;
                Integer b = null;
                switch (x) {
                    case 0:
                        mes.append("addFirst(" + addNumber + ")\n");
                        buggy.addFirst(addNumber);
                        correct.addFirst(addNumber);
                        break;
                    case 1:
                        mes.append("addLast(" + addNumber + ")\n");
                        buggy.addLast(addNumber);
                        correct.addLast(addNumber);
                        break;
                    case 2:
                        mes.append("removeFirst()\n");
                        c = correct.removeFirst();
                        b = buggy.removeFirst();
                        assertEquals(mes.toString(), c, b);
                        break;
                    case 3:
                        mes.append("removeLast()\n");
                        c = correct.removeLast();
                        b = buggy.removeLast();
                        assertEquals(mes.toString(), c, b);
                        break;

                }
            }
        }
    }
}
