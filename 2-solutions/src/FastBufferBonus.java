public class FastBufferBonus implements Buffer {

  private CharDeque deque;

  public FastBufferBonus() {
    this(10000000);
  }

  public FastBufferBonus(int capacity) {
    this.deque = new CharDeque(capacity);
  }

  public int size() {
    return this.deque.leftSize() + this.deque.rightSize();
  }

  public void load(char initial[], int cursorPosition) {
    if (initial.length <= this.deque.capacity()) {
      for (int i = 0; i < cursorPosition; i++) {
        this.deque.pushLeft(initial[i]);
      }
      for (int i = initial.length - 1; i >= cursorPosition; i--) {
        this.deque.pushRight(initial[i]);
      }
    }
  }

  public Character[] toArray() {
    Character[] res = new Character[this.size()];
    int i = 0;
    for (int j = 0; j < this.deque.leftSize(); j++) {
      res[i++] = this.deque.getLeft(j);
    }
    for (int j = 0; j < this.deque.rightSize(); j++) {
      res[i++] = this.deque.getRight(j);
    }
    return res;
  }

  public int getCursor() {
    return this.deque.leftSize();
  }

  public void setCursor(int j) {
    if (j < this.size()) {
      int offset = j - this.getCursor();
      if (offset < 0) {
        this.moveLeft(offset * -1);
      } else {
        this.moveRight(offset);
      }
    }
  }

  public void moveLeft() {
    this.moveLeft(1);
  }

  private void moveLeft(int n) {
    for (int i = 0; i < n; i++) {
      this.deque.pushRight(this.deque.popLeft());
    }
  }

  public void moveRight() {
    this.moveRight(1);
  }

  private void moveRight(int n) {
    for (int i = 0; i < n; i++) {
      this.deque.pushLeft(this.deque.popRight());
    }
  }

  public void insert(char c) {
    this.deque.pushLeft(c);
  }

  public Character deleteLeft() {
    return this.deque.popLeft();
  }

  public Character deleteRight() {
    return this.deque.popRight();
  }
}
