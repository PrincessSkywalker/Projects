public class CharDeque {

  private char[] arr;
  private int i, j;

  public CharDeque(int capacity) {
    this.arr = new char[capacity];
    this.i = 0;
    this.j = capacity - 1;
  }

  public int capacity() {
    return this.arr.length;
  }

  public int leftSize() {
    return this.i;
  }

  public int rightSize() {
    return this.capacity() - (this.j + 1);
  }

  public char getLeft(int i) {
    return (i >= 0 && i < this.leftSize()) ? this.arr[i] : 0;
  }

  public char getRight(int i) {
    return (i >= 0 && i < this.rightSize()) ? this.arr[this.j + i + 1] : 0;
  }

  public void pushLeft(char c) {
    if (this.i < this.j) {
      this.arr[this.i++] = c;
    }
  }

  public void pushRight(char c) {
    if (this.j > this.i) {
      this.arr[this.j--] = c;
    }
  }

  public char popLeft() {
    if (this.i > 0) {
      char c = this.arr[--this.i];
      this.arr[this.i] = 0;
      return c;
    } else {
      return 0;
    }
  }

  public char popRight() {
    if (this.j < this.capacity() - 1) {
      char c = this.arr[++this.j];
      this.arr[this.j] = 0;
      return c;
    } else {
      return 0;
    }
  }
}
