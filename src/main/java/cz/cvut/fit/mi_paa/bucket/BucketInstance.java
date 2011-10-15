package cz.cvut.fit.mi_paa.bucket;

import cz.cvut.fit.mi_paa.bucket.resolver.BruteForceResolver;
import cz.cvut.fit.mi_paa.bucket.resolver.HeuristicResolver;
import cz.cvut.fit.mi_paa.bucket.resolver.Resolver;
import cz.cvut.fit.mi_paa.bucket.result.Result;

final public class BucketInstance {
	final private int id;

	final private int numOfBuckets;

	final private Bucket[] buckets;

	public BucketInstance(String[] chunks) {
		id = getInt(chunks[0]);
		numOfBuckets = getInt(chunks[1]);
		buckets = getBuckets(chunks);
	}

	private int getInt(String value) {
		return Integer.parseInt(value);
	}

	private Bucket[] getBuckets(String[] chunks) {
		Bucket[] buckets = new Bucket[getNumOfBuckets()];
		for (int i = 0; i < getNumOfBuckets(); i++) {
			buckets[i] = getBucket(i, chunks);
		}
		return buckets;
	}

	private Bucket getBucket(int i, String[] chunks) {
		return new Bucket(getCapacity(i, chunks), getVolume(i, chunks), getTarget(i, chunks));
	}

	private int getCapacity(int i, String[] chunks) {
		return getInt(getChunk(i, chunks));
	}

	private int getVolume(int i, String[] chunks) {
		return getInt(getChunk(i + getNumOfBuckets(), chunks));
	}

	private int getTarget(int i, String[] chunks) {
		return getInt(getChunk(i + 2 * getNumOfBuckets(), chunks));
	}

	private String getChunk(int i, String[] chunks) {
		return getChunk(2, i, chunks);
	}

	private String getChunk(int offset, int i, String[] chunks) {
		return chunks[offset + i];
	}

	public int getId() {
		return id;
	}

	public int getNumOfBuckets() {
		return numOfBuckets;
	}

	public Bucket[] getBuckets() {
		return buckets;
	}

	public Result solveBruteForce() {
		return solve(new BruteForceResolver(this));
	}

	public Result solveHeuristic() {
		return solve(new HeuristicResolver(this));
	}

	private Result solve(Resolver resolver) {
		return resolver.solve();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("id: ");
		sb.append(getId());
		sb.append(", numOfBuckets: ");
		sb.append(getNumOfBuckets());
		sb.append(", buckets[");
		for (int i = 0; i < getNumOfBuckets(); i++) {
			sb.append(getBuckets()[i]);
			sb.append(", ");
		}
		sb.delete(sb.length() - 3, sb.length() - 1);
		sb.append("]]");
		return sb.toString();
	}

}
