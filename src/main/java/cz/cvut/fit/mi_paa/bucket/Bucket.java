package cz.cvut.fit.mi_paa.bucket;

final public class Bucket {

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
		this.volume = volume;
	}

	public void empty() {
		pour(0);
	}

	public boolean isEmpty() {
		return !isFull();
	}

	public boolean isNotFinished() {
		return !isFinished();
	}

	public boolean isFinished() {
		return volume == target;
	}

	public boolean isFull() {
		return volume == capacity;
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
