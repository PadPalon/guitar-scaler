package ch.neukom.guitarscaler;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import ch.neukom.guitarscaler.Note;

public class Scale extends AbstractList<Note> {
	private final Note root;
	private final List<Note> notes = Lists.newArrayList();
	
	public Scale(ScaleDefinition definition, Note root) {
		this.root = root;
		
		Integer rootValue = root.getNoteValue();
		for(ScaleDefinition.Step step: definition) {
			notes.add(Note.getNote(rootValue % 12));
			rootValue += step.getDistance();
		}
	}

	@Override
	public Note get(int index) {
		return notes.get(index);
	}

	@Override
	public int size() {
		return notes.size();
	}

	public Note getRoot() {
		return root;
	}
}
