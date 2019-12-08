package de.stativkarawane.tools.edl.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.stativkarawane.tools.edl.parser.EdlDefinition.*;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);
	private static final String COLUMN_DELIMITER = ";";

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = getFileNameFromProgramArguments(args);
		File f = checkAndReturnFile(fileName);
		Movie movie = parseMovie(f);
		printAsCSV(movie);
	}

	private static String getFileNameFromProgramArguments(String[] args) {
		if (args == null || args.length != 1) {
			throw new IllegalArgumentException(
					String.format(
							"Please start the main method %s with argument [filename] for the EDL file to parse",
							Main.class.getName()));
		}
		return args[0];
	}

	private static File checkAndReturnFile(String filename) {
		File f = new File(filename);
		if (!f.exists()) {
			throw new IllegalArgumentException("File does not exist: " + filename);
		}
		return f;
	}

	private static Movie parseMovie(File f) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(f)) {
			List<ClipInformation> clipInformations = parseClipInformations(scanner);
			return new Movie(f.getName(), clipInformations);
		}
	}

	private static List<ClipInformation> parseClipInformations(Scanner scanner) {
		List<ClipInformation> result = new LinkedList<>();
		String line;
		ClipInformation current = null;
		while (scanner.hasNextLine() && (line = scanner.nextLine()) != null) {
			if (line.length() < 3) {
				return result;
			}
			// first line of new entry
			if (StringUtils.isNumeric(getContentFromLine(LINE_1_ID, line))) {
				if (current != null) {
					result.add(current);
				}
				current = new ClipInformation();
				current.setId(Integer.parseInt(getContentFromLine(LINE_1_ID, line)));
				current.setTitle(getContentFromLine(LINE_1_TITLE, line));
				current.setFinalProductionStartTime(getContentFromLine(LINE_1_FINAL_PRODUCTION_START_TIME, line));
				current.setFinalProductionEndTime(getContentFromLine(LINE_1_FINAL_PRODUCTION_END_TIME, line));
				current.setClaimedClipStartTime(getContentFromLine(LINE_1_CLAIMED_CLIP_START_TIME, line));
				current.setClaimedClipEndTime(getContentFromLine(LINE_1_CLAIMED_CLIP_END_TIME, line));
			} else if (line.startsWith(LINE_2_CLIPNAME_START)) {
				if (current != null) {
					current.setClipName(getContentFromLine(LINE_2_CLIP_NAME, line));
				}
			}
			// if there is no next line, store the current
			if (!scanner.hasNextLine() && current != null) {
				result.add(current);
			}
		}

		return result;
	}

	private static void printAsCSV(Movie movie) {
		System.out.println("MOVIE;ID;TITLE;CLIPNAME;PROD BEGIN;PROD END;CLIP BEGIN;CLIP END");

		for (ClipInformation info : movie.getClipInformations()) {
			printAsLine(
					movie.getFilename(),
					Integer.toString(info.getId()),
					info.getTitle(),
					info.getClipName(),
					info.getFinalProductionStartTime(),
					info.getFinalProductionEndTime(),
					info.getClaimedClipStartTime(),
					info.getClaimedClipEndTime());
		}
	}

	private static void printAsLine(String... strings) {
		for (String s : strings) {
			System.out.print(s);
			System.out.print(COLUMN_DELIMITER);
		}
		System.out.println();
	}

}
