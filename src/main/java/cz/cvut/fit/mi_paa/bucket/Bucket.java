package cz.cvut.fit.mi_paa.bucket;

final public class Bucket implements Cloneable {

	final private int capacity;

	final private int target;

	private int volume;

	public Bucket(int capacity, int volume, int target) {
		this.capacity = capacity;
		this.volume = volume;
		this.target = target;
	}

	public void pour() {
		pour(capacity);
	}

	public void pour(int volume) {
		if (getVolume() + volume > getCapacity()) {
			this.volume = getCapacity();
		} else if (getVolume() + volume < 0) {
			empty();
		} else {
			this.volume += volume;
		}
	}

	public void empty() {
		this.volume = 0;
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}

	public boolean isEmpty() {
		return getVolume() == 0;
	}

	public boolean isFinished() {
		return getVolume() == getTarget();
	}

	public boolean isNotFull() {
		return !isFull();
	}

	public boolean isFull() {
		return getVolume() == getCapacity();
	}

	public int getCapacity() {
		return capacity;
	}

	public int getTarget() {
		return target;
	}

	public int getVolume() {
		return volume;
	}

	@Override
	public Bucket clone() {
		return new Bucket(getCapacity(), getVolume(), getTarget());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("capacity: ");
		sb.append(getCapacity());
		sb.append(", volume: ");
		sb.append(getVolume());
		sb.append(", target: ");
		sb.append(getTarget());
		sb.append("]");
		return sb.toString();
	}
}
