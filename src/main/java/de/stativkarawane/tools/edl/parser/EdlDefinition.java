package de.stativkarawane.tools.edl.parser;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * Definition of the contents of the EDL files which we parse.
 */
@RequiredArgsConstructor
public enum EdlDefinition {

	LINE_1_ID(0, 3),
	LINE_1_TITLE(5, 22),
	LINE_1_FINAL_PRODUCTION_START_TIME(61, 72),
	LINE_1_FINAL_PRODUCTION_END_TIME(73, 84),
	LINE_1_CLAIMED_CLIP_START_TIME(37, 48),
	LINE_1_CLAIMED_CLIP_END_TIME(49, 60),
	LINE_2_CLIP_NAME(18, null);

	/**
	 * Start index in the EDL file.
	 */
	@Getter
	private final int beginIndex;

	/**
	 * End index in the EDL file, not inclusive!
	 */
	@Getter
	private final Integer endIndex;

	public static final String LINE_2_CLIPNAME_START = "*FROM CLIP NAME:";

	public static String getContentFromLine(@NonNull EdlDefinition definition, @NonNull String line) {
		final int endIndex =
				definition.getEndIndex() != null
						? definition.getEndIndex()
						: line.length();
		if (endIndex < definition.beginIndex) {
			throw new IllegalArgumentException(String.format("Unable to parse %s in line %s", definition, line));
		}
		if (line.length() >= endIndex) {
			return StringUtils.trim(line.substring(definition.beginIndex, endIndex));
		} else {
			return null;
		}
	}

}
