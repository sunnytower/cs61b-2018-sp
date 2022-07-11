public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char ch : word.toCharArray()) {
            deque.addLast(ch);
        }
        return deque;
    }
    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }
    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        } else if (deque.removeFirst() != deque.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(deque);
        }
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        while (!deque.isEmpty()) {
            if (deque.size() <= 1) {
                return true;
            }
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
