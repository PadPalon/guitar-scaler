package ch.neukom.guitarscaler;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ScaleDefinition implements Iterable<ScaleDefinition.Step> {
	private List<Step> steps = Lists.newLinkedList();
	
	public ScaleDefinition addStep(int distance) {
		Step step;
		if(steps.isEmpty()) {
			step = new Step(distance);
		} else {
			step = new Step(Iterables.getLast(steps), distance);
		}
		steps.add(step);
		return this;
	}
	
	public ScaleDefinition finishScale() {
		steps.get(0).setPrevious(Iterables.getLast(steps));
		return this;
	}

	@Override
	public Iterator<Step> iterator() {
		return steps.iterator();
	}

	public class Step {
		private Step previous;
		private Step next;
		
		private final int distance;
		
		public Step(int distance) {
			this.distance = distance;
		}
		
		public Step(Step previous, int distance) {
			this.setPrevious(previous);
			this.distance = distance;
		}

		public Step getPrevious() {
			return previous;
		}

		public void setPrevious(Step previous) {
			this.previous = previous;
			previous.setNext(this);
		}

		public Step getNext() {
			return next;
		}

		public void setNext(Step next) {
			this.next = next;
		}

		public int getDistance() {
			return distance;
		}
	}
}
