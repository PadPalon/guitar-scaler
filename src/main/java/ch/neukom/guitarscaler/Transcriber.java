package ch.neukom.guitarscaler;

import java.util.Optional;

import ch.neukom.guitarscaler.Note;

public class Transcriber {
	public enum Direction {
		ASCENDING(1), DESCENDING(-1);
		
		private final Integer modifier;
		
		Direction(Integer modifier) {
			this.modifier = modifier;
		}
		
		Integer getModifier() {
			return modifier;
		}
	}

	private final NoteSequence sequence;
	private Integer steps;
	private Direction direction = Direction.ASCENDING;
	private boolean chromatic = false;
	
	private Transcriber(NoteSequence sequence) {
		this.sequence = sequence;
	}
	
	public static Transcriber from(NoteSequence sequence) {
		return new Transcriber(sequence);
	}
	
	public Transcriber move(Integer steps) {
		this.steps = steps;
		return this;
	}
	
	public Transcriber descending() {
		this.direction = Direction.DESCENDING;
		return this;
	}
	
	public Transcriber chromatic() {
		this.chromatic = true;
		return this;
	}
	
	public NoteSequence transcribe() {
		validateTranscriber();
		Optional<Scale> scale = sequence.getScale();
		if(scale.isPresent() && !chromatic) {
			return transcribeByScale(scale.get());
		} else {
			return transcribeChromatic();
		}
	}

	private void validateTranscriber() {
		if(steps == null) {
			throw new IllegalStateException("Steps need to be set in transcriber");
		}
	}

	private NoteSequence transcribeByScale(Scale scale) {
		NoteSequence transcribed = new NoteSequence();
		for(Note note: sequence) {
			int newPositionInScale = (scale.indexOf(note) + (steps * direction.getModifier()));
			if(direction == Direction.ASCENDING) {
				newPositionInScale = newPositionInScale % scale.size();
			} else {
				while(newPositionInScale < 0) {
					newPositionInScale = scale.size() + newPositionInScale;
				}
			}
			transcribed.addNote(scale.get(newPositionInScale));
		}
		return transcribed;
	}

	private NoteSequence transcribeChromatic() {
		NoteSequence transcribed = new NoteSequence();
		for(Note note: sequence) {
			Integer noteValue = note.getNoteValue();
			int stepsToMove = steps * direction.getModifier();
			transcribed.addNote(Note.getNote(noteValue + stepsToMove));
		}
		return transcribed;
	}
}
