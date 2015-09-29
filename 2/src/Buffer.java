/** think of the cursor position as BETWEEN two adjacent characters.
    let '|' show the cursor position. 
    
    
    cursor == 0 - cursor is between the begining of the buffer, and the first char - "|abcdef"
    cursor == 1 - "cursor is between the first and second char - "a|bcdef"
    cursor == 5 - cursor is between the 5th and 6th char - "abcde|f"
    

*/
interface Buffer 
{

  /** Get the current character count of the buffer  */
  int size();
  
  /** load the buffer from an char array and position the cursor. after load
      size() == initial.length */
  void load(char initial[], int cursorPosition);

  /** convert the current buffer contents to an array 
   returnArray.length == size() 
  */
  char[] toArray();

  /** get the cursor position, in characters from start */
  void getCursor();

  /** set the cursor position, in characters from start */
  void setCursor(int j);

  /** move cursor one to the right 
      "abc|def" => "abcd|ef"
  */
  void moveRight();

  /** move cursor one to the left 
      "abc|def" => "ab|cdef"
   */
    void moveLeft();

  /** insert a new char to the left of the cursor 
   if the buffer is "abc|def", insertLeft('X') 
   would change the buffer to 'abcX|def'
   */
    void insertLeft(char c);

  /** delete and return the character to the right of the cursor 
   given "abc|def", deleteRight() => "abc|ef"
   */
  char deleteRight();

  /** delete and return the character to the left of the cursor 
   given "abc|def", deleteLeft() => "ab|def"
   */
  char deleteLeft();

}

