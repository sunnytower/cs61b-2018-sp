import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> buggy = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        String message = "";
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            if (correct.size() == 0) {
                int addNumber = StdRandom.uniform(1000);
                int x = StdRandom.uniform(2);
                if (x == 0) {
                    message += "addFirst(" + addNumber + ")\n";
                    buggy.addFirst(addNumber);
                    correct.addFirst(addNumber);
                } else {
                    message += "addLast(" + addNumber + ")\n";
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
                        message += "addFirst(" + addNumber + ")\n";
                        buggy.addFirst(addNumber);
                        correct.addFirst(addNumber);
                        break;
                    case 1:
                        message += "addLast(" + addNumber + ")\n";
                        buggy.addLast(addNumber);
                        correct.addLast(addNumber);
                        break;
                    case 2:
                        message += "removeFirst()\n";
                        c = correct.removeFirst();
                        b = buggy.removeFirst();
                        assertEquals(message, c, b);
                        break;
                    case 3:
                        message += "removeLast()\n";
                        c = correct.removeLast();
                        b = buggy.removeLast();
                        assertEquals(message, c, b);
                        break;

                }
            }
        }
    }
}
