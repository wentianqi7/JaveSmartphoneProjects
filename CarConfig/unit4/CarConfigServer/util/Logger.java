package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * logging exception message and timestamp
 * @author Tianqi Wen (tianqiw)
 *
 */
public class Logger {
	private String filename;

	public Logger(String filename) {
		this.filename = filename;
	}

	public void log(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append(new Date()).append("\t").append(message);
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(filename, true)));
			out.println(sb.toString());
			out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
