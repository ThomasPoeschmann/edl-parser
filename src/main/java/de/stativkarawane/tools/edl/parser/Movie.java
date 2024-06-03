package de.stativkarawane.tools.edl.parser;

import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class Movie {

	/**
	 * Name of the EDL movie file.
	 */
	@NonNull
	private String filename;

	@NonNull
	private List<ClipInformation> clipInformations;

}
