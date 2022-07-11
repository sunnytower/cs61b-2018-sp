import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> buggy = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        final int testTimes = 300;
        String message = "";
        for (int i=0; i < testTimes; i++) {
            int x = StdRandom.uniform(4);
            switch (x) {
                case 0:
                    int addNum = StdRandom.uniform(10);
                    message += "addfirst(" + addNum + ")\n";
                    buggy.addFirst(addNum);
                    correct.addFirst(addNum);
                    break;

                case 1:
                    addNum = StdRandom.uniform(10);
                    message += "addLast(" + addNum + ")\n";
                    buggy.addLast(addNum);
                    correct.addLast(addNum);
                    break;
                case 2:
                    message += "removeFirst()\n";
                    if (! buggy.isEmpty()) {
                        Integer x1 = buggy.removeFirst();
                        Integer x2 = correct.removeFirst();
                        assertEquals(message, x2, x1);
                    }
                    break;

                case 3:
                    message += "removeLast()\n";
                    if (! buggy.isEmpty()) {
                        Integer x1 = buggy.removeLast();
                        Integer x2 = correct.removeLast();
                        assertEquals(message, x2, x1);
                    }
            }

        }
    }
}
