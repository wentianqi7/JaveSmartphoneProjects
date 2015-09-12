package assignment.coin;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Tianqi Wen (tianqiw) Simulates the toss coin process
 */
public class Simulation {
	private static final int NUM_TEST = 20;
	private int countHead;
	private int countTail;
	private Coin coin;

	public Simulation() {
		this.countHead = 0;
		this.countTail = 0;
		this.coin = new Coin();
	}

	public int getCountHead() {
		return this.countHead;
	}

	public int getCountTail() {
		return this.countTail;
	}

	/**
	 * @return the initial side of the coin
	 */
	public Side getInitialSide() {
		return this.coin.getSideUp();
	}

	/**
	 * simulation for toss the coin 20 times
	 * 
	 * @return list of results
	 */
	public List<Side> tossTwentyTimes() {
		List<Side> result = new ArrayList<Side>(NUM_TEST);
		for (int i = 0; i < NUM_TEST; i++) {
			// toss the coin
			coin.toss();
			Side sideUp = coin.getSideUp();
			// check the side
			switch (sideUp) {
			case HEADS:
				this.countHead++;
				break;
			case TAILS:
				this.countTail++;
			}
			result.add(sideUp);
		}
		return result;
	}

	public static void main(String[] args) {
		Simulation sim = new Simulation();
		System.out.println("Initial Value: " + sim.getInitialSide());
		System.out.println("Loop Result: ");
		List<Side> result = sim.tossTwentyTimes();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < NUM_TEST; i++) {
			sb.append(Integer.toString(i));
			sb.append(" : ");
			sb.append(result.get(i));
			sb.append("\n");
		}
		sb.append("Head Count: ");
		sb.append(sim.getCountHead());
		sb.append("\nTail Count: ");
		sb.append(sim.getCountTail());
		System.out.println(sb.toString());
	}
}
