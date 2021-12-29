class Cell {
	private int data;
	private boolean revealed = false;
	
	int getData() {
		return data;
	}
	void setData(int data) {
		this.data = data;
	}
	
	boolean getRevealed() {
		return revealed;
	}
	void setRevealed() {
		revealed = true;
	}
}
