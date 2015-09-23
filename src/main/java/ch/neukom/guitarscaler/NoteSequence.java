package ch.neukom.guitarscaler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import ch.neukom.guitarscaler.Note;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class NoteSequence implements Iterable<Note> {
	private final static Splitter NOTE_SPLITTER = Splitter.on('-').trimResults().omitEmptyStrings();
	
	private final Optional<Scale> scale;
	private final List<Note> notes = Lists.newLinkedList();
	
	public NoteSequence() {
		this("");
	}
	
	public NoteSequence(String noteString) {
		this(null, noteString, null);
	}
	
	public NoteSequence(@Nullable ScaleDefinition scaleDefinition, String noteString, @Nullable Integer root) {
		notes.addAll(NOTE_SPLITTER
				.splitToList(noteString)
				.stream()
				.map(note -> Note.getNote(Integer.valueOf(note)))
				.collect(Collectors.toList())
			);
		
		if(scaleDefinition != null && root != null) {
			scale = Optional.of(new Scale(scaleDefinition, Note.getNote(root)));
		} else {
			scale = Optional.empty();
		}
	}
	
	public Optional<Scale> getScale() {
		return scale;
	}
	
	public void addNote(Note note) {
		this.notes.add(note);
	}

	@Override
	public Iterator<Note> iterator() {
		return notes.iterator();
	}
}
