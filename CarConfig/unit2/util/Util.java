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

import exception.AutoException;
import exception.CustomException;
import Adapter.BuildAuto;
import model.Automobile;

/**
 * helper founctions used for File IO
 * 
 * @author Tianqi Wen (tianqiw)
 */
public class Util {
	private static final String PATH = "Resources/%s";
	private Logger logger;
	
	public Util() {
		logger = new Logger(String.format(PATH, "logs.txt"));
	}
	
	/**
	 * Reading data from a text file and building an Automotive
	 * 
	 * @param filename
	 * @return Automotive Object created from text file
	 */
	public Automobile buildAutoObject(String filename, Automobile auto) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					String.format(PATH, filename)));
			String line = br.readLine().trim();

			String[] segs = line.split(":");

			String makeName = segs[0];
			String modelName = segs[1];
			float basePrice = Float.parseFloat(segs[2]);
			int opsetSize = Integer.parseInt(segs[3]);
			auto = new Automobile(makeName, modelName, basePrice);
			int opsetIndex = 0;

			while ((line = br.readLine()) != null) {
				try {
					line = line.trim();
					if (line.startsWith("OptionSet:")) {
						if (opsetIndex++ >= opsetSize) {
							// If more optionset than declared in file
							// throw UNDECLARED_OPTIONSET
							throw new AutoException(
									CustomException.UNDECLARED_OPTIONSET,
									Integer.valueOf(opsetIndex),
									Integer.valueOf(opsetSize));
						}
						segs = line.split(":");
						if (segs.length != 3) {
							// If optionSet input format not valid
							// throw MISSING_OPSET
							throw new AutoException(
									CustomException.MISSING_OPSET);
						}
						String opsetName = segs[1];
						int optSize = Integer.parseInt(segs[2]);
						String[] opts = br.readLine().trim().split(",");
						if (auto.findOpsetByName(opsetName) != null) {
							// If opsetName already exists
							// throw DPU_OPSET
							throw new AutoException(CustomException.DUP_OPSET,
									opsetName, opts);
						}
						auto.setOpset(opsetName, opts);
						try {
							if (opts.length < optSize) {
								// If some option is missing
								// throw MISSING_OPTION
								throw new AutoException(
										CustomException.MISSING_OPTION,
										Integer.valueOf(optSize - opts.length),
										opsetName);
							}
						} catch (AutoException ae) {
							// handle missing option
							logger.log(ae.toString());
							ae.fix(auto);
						}
					}
				} catch (AutoException ae) {
					// handle undeclared/dup/missing optionset, missing option price
					// MISSING_PRICE is thrown in OptionSet.java
					logger.log(ae.toString());
					ae.fix(auto);
				}
			}

			br.close();
		} catch (FileNotFoundException fne) {
			logger.log(fne.toString());
			fne.printStackTrace();
		} catch (IOException ioe) {
			logger.log(ioe.toString());
			ioe.printStackTrace();
		} finally {
			// close BufferedReader
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
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
	public Automobile readSerializedFile(String filename) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filename));
			Automobile auto = (Automobile) in.readObject();
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
