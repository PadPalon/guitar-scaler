package ch.neukom.guitarscaler;

import java.util.ArrayList;

import ch.neukom.guitarscaler.Note;

public class Scale extends ArrayList<Note> {
	public Scale(ScaleDefinition definition, Note root) {
		Integer rootValue = root.getNoteValue();
		for(ScaleDefinition.Step step: definition) {
			this.add(Note.getNote(rootValue % 12));
			rootValue += step.getDistance();
		}
	}
}
