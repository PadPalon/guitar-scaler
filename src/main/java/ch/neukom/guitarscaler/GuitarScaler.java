package ch.neukom.guitarscaler;

import java.util.Map;

import ch.neukom.guitarscaler.Note;

public class GuitarScaler {
	public static void main(String[] args) {
		ScaleReader scaleReader = new ScaleReader();
		Map<String, ScaleDefinition> scales = scaleReader.readScales();
		NoteSequence noteSequence = new NoteSequence(scales.get("minor"), "1-4-3-4", 1);
		for(Note note: noteSequence) {
			System.out.println(note.toString());
		}
		NoteSequence transcribed = Transcriber.from(noteSequence).move(2).descending().transcribe();
		for(Note note: transcribed) {
			System.out.println(note.toString());
		}
	}
}
