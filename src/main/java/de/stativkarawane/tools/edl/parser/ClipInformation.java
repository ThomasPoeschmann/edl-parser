package de.stativkarawane.tools.edl.parser;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClipInformation {

	/**
	 * ID of the clip.
	 */
	private int id;

	/**
	 * Internal title in the movie.
	 */
	private String title;

	/**
	 * Clip name.
	 */
	private String clipName;

	private String finalProductionStartTime;

	private String finalProductionEndTime;

	private String claimedClipStartTime;

	private String claimedClipEndTime;

}
