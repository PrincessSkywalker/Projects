interface Buffer {

  /** Get the current character count */
  int size();

  /** cursor position, in characters from start */
  void setCursor(int j);

  /** move cursor one to the right */
  void moveRight();

  /** move cursor one to the left */
  void moveLeft();

  /** insert a new char to the right of the cursor */
  void insert(char c);

  /** delete and return the character to the right of the cursor */
  Character deleteRight();

  /** delete and return the character to the left of the cursor */
  Character deleteLeft();

  /** convert the buffer contents to an array */
  Character[] toArray();
}
