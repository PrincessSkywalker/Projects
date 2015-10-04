// This class should countain your implementation of the Buffer interface.
import java.util.ArrayDeque;
public class FastBuffer implements Buffer{
	private ArrayDeque<Character> buffer_ = new ArrayDeque<>(10000000);
	private int cursor_;

	public int size(){
		return buffer_.size();
	}

	public void load(char[] initial, int cursorPosition){
		for(char c : initial){
			buffer_.push(c);
		}
		cursor_ = cursorPosition;
	}

	public char[] toArray(){
		char[] array = new char[size()];
		ArrayDeque<Character> removed = new ArrayDeque<>(size());

		for(int i = 0; i < size(); i ++){
			removed.push(buffer_.pop());
		}
		for(int i = 0; i < removed.size(); i ++){
			array[i] = removed.peek();
			buffer_.push(removed.pop());
		}
		return array;
	}

	public int getCursor(){
		return cursor_;
	}

	public void setCursor(int j){
		cursor_ = j;
	}

	public void moveRight(){
		if(cursor_ < size()){
			cursor_ ++;
		}
	}

	public void moveLeft(){
		if(cursor_ > 0){
			cursor_ --;
		}
	}

	public void insertLeft(char c){
		ArrayDeque<Character> removed = new ArrayDeque<>(size() - cursor_); 
		for(int i = size(); i > cursor_; i--){
			removed.push(buffer_.pop());
		}
		buffer_.push(c);
		for(int i = 0; i < removed.size(); i ++){
			buffer_.push(removed.pop());
		}
	}

	public char deleteRight(){
		ArrayDeque<Character> removed = new ArrayDeque<>(size() - cursor_); 
		for(int i = size(); i > cursor_; i--){
			removed.push(buffer_.pop());
		}
		char c = removed.pop();
		for(int i = 0; i < removed.size(); i ++){
			buffer_.push(removed.pop());
		}
		return c;
	}

	public char deleteLeft(){
		ArrayDeque<Character> removed = new ArrayDeque<>(size() - cursor_); 
		for(int i = size(); i > cursor_; i--){
			removed.push(buffer_.pop());
		}
		char c = buffer_.pop();
		for(int i = 0; i < removed.size(); i ++){
			buffer_.push(removed.pop());
		}
		return c;
	}
}