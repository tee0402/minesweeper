class Cell {
	private int data;
	private boolean revealed = false;
	
	int getData() {
		return data;
	}
	void setData(int newData) {
		data = newData;
	}
	
	boolean getRevealed() {
		return revealed;
	}
	void setRevealed() {
		revealed = true;
	}
}
