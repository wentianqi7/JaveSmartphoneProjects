package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.Automotive;

/**
 * helper founctions used for File IO
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Util {
	private static final String PATH = "Resources/%s";

	/**
	 * Reading data from a text file and building an Automotive
	 * 
	 * @param filename
	 * @return Automotive Object created from text file
	 */
	public Automotive buildAutoObject(String filename, Automotive auto) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					String.format(PATH, filename)));
			String line = br.readLine().trim();

			String[] segs = line.split(":");
			String autoName = segs[0];
			float basePrice = Float.parseFloat(segs[1]);
			int opsetSize = Integer.parseInt(segs[2]);
			auto = new Automotive(autoName, basePrice, opsetSize);

			int opsetIndex = 0;

			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("OptionSet:")) {
					segs = line.split(":");
					String opsetName = segs[1];
					int optSize = Integer.parseInt(segs[2]);
					String[] opts = br.readLine().trim().split(",");
					auto.setOpsetByIndex(opsetIndex++, opsetName, optSize, opts);
				}
			}
			br.close();
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return auto;
	}

	/**
	 * write a serializable object to a serialized file
	 * 
	 * @param filename
	 * @param serliz
	 */
	public void writeToSerializedFile(String filename, Serializable serliz) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filename));
			out.writeObject(serliz);
			out.close();
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * load object from serialized file
	 * 
	 * @param filename
	 * @return object loaded from file
	 */
	public Automotive readSerializedFile(String filename) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filename));
			Automotive auto = (Automotive) in.readObject();
			in.close();
			return auto;
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}

		return null;
	}
}
