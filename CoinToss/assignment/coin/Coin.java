package assignment.coin;

/**
 * @author Tianqi Wen (tianqiw)
 */
public class Coin {
	private Side sideUp;

	public Coin() {
		this.toss();
	}

	/**
	 * toss the coin once
	 * assign a random value (heads or tails) to the sideUp property
	 */
	public void toss() {
		this.sideUp = Side.values()[(int) (Math.random() * 2)];
	}

	public Side getSideUp() {
		return this.sideUp;
	}
}
