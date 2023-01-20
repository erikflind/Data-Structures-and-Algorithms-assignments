import java.util.Comparator;

public class BinarySearch {

  // Common description of the below functions:
  // * Precondition: `a` is sorted according to the given comparator.
  // * Precondition: all arguments are non-null (no need to check).
  // * Required complexity: O(log(n)) comparisons where n is the length of `a`.

  // Check if the array `a` contains the given search key.
  public static <T> boolean contains(T[] a, T key, Comparator<T> comparator) {
    int low = 0;
    int high = a.length - 1;

    while (low <= high) {
      int mid = (low + high) / 2;
      int comparisonValue = comparator.compare(a[mid], key);

      if (comparisonValue == 0) {
        return true;
      } else if (comparisonValue < 0) { //upper half of array
        low = mid + 1;
      } else { //lower half of array
        high = mid - 1;
      }
    }

    return false;
  }

  // Return the *first position* of `key` in `a`, or -1 if `key` does not occur.
  public static <T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
    int low = 0;
    int high = a.length - 1;
    int keyIndex = -1; // default return value if the key is not found

    return helperFunction(a, key, comparator, low, high, keyIndex);
  }

  public static <T> int helperFunction(T[] a, T key, Comparator<T> comparator, int low, int high, int keyIndex) {
    if (!(low > high)) {
      int mid = (low + high) / 2;
      int comparisonValue = comparator.compare(a[mid], key);

      if (comparisonValue == 0) {
        return helperFunction(a, key, comparator, low, mid - 1, mid);
      } else if (comparisonValue < 0) {
        return helperFunction(a, key, comparator, mid + 1, high, keyIndex);
      } else {
        return helperFunction(a, key, comparator, low, mid - 1, keyIndex);
      }
    }

    return keyIndex;
  }

  // Versions of the above functions that use the natural ordering of the type T.
  // T needs to be "comparable" (i.e., implement the interface Comparable).
  // Examples: Integer, String (the alphabetic ordering)

  public static <T extends Comparable<? super T>> boolean contains(T[] a, T key) {
    return contains(a, key, Comparator.naturalOrder());
  }

  public static <T extends Comparable<? super T>> int firstIndexOf(T[] a, T key) {
    return firstIndexOf(a, key, Comparator.naturalOrder());
  }

  // Your tests.

  public static void main(String[] args) {
    Integer[] a = { 1, 3, 5, 7, 9 };
    assert contains(a, 1);
    assert !contains(a, 4);
    assert contains(a, 7);

    String[] b = { "cat", "cat", "cat", "dog", "turtle", "turtle" };
    assert firstIndexOf(b, "cat") == 0;
    assert firstIndexOf(b, "dog") == 3;
    assert firstIndexOf(b, "turtle") == 4;
    assert firstIndexOf(b, "zebra") == -1;
    assert firstIndexOf(b, "bee") == -1;
  }

}
