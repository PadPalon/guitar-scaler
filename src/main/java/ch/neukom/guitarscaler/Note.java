package ch.neukom.guitarscaler;

import java.util.Map;

import com.google.common.collect.Maps;

public enum Note {
	A(1), AS(2), B(3), C(4), CS(5), D(6), DS(7), E(8), F(9), FS(10), G(11), GS(12);
	
	private static Map<Integer, Note> NOTES = Maps.newHashMap();
	
	static {
		for(Note note: Note.values()) {
			NOTES.put(note.getNoteValue(), note);
		}
	}
	
	private final Integer noteValue;
	
	Note(int noteValue) {
		this.noteValue = noteValue;
	}
	
	public Integer getNoteValue() {
		return noteValue;
	}
	
	public static Note getNote(Integer noteValue) {
		return NOTES.get(noteValue);
	}
}
