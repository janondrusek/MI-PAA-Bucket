package cz.cvut.fit.mi_paa.bucket;

import cz.cvut.fit.mi_paa.bucket.resolver.BruteForceResolver;
import cz.cvut.fit.mi_paa.bucket.resolver.HeuristicResolver;
import cz.cvut.fit.mi_paa.bucket.resolver.Resolver;
import cz.cvut.fit.mi_paa.bucket.result.Result;

final public class BucketInstance implements Cloneable {

	final private int id;

	final private int numOfBuckets;

	final private Bucket[] buckets;

	public BucketInstance(String[] chunks) {
		this(getInt(chunks[0]), getInt(chunks[1]), getBuckets(getInt(chunks[1]), chunks));
	}

	private static int getInt(String value) {
		return Integer.parseInt(value);
	}

	private BucketInstance(int id, int numOfBuckets, Bucket[] buckets) {
		this.id = id;
		this.numOfBuckets = numOfBuckets;
		this.buckets = buckets;
	}

	private static Bucket[] getBuckets(int numOfBuckets, String[] chunks) {
		Bucket[] buckets = new Bucket[numOfBuckets];
		for (int i = 0; i < numOfBuckets; i++) {
			buckets[i] = getBucket(i, numOfBuckets, chunks);
		}
		return buckets;
	}

	private static Bucket getBucket(int i, int numOfBuckets, String[] chunks) {
		return new Bucket(getCapacity(i, numOfBuckets, chunks), getVolume(i, numOfBuckets, chunks), getTarget(i,
				numOfBuckets, chunks));
	}

	private static int getCapacity(int i, int numOfBuckets, String[] chunks) {
		return getInt(getChunk(i, chunks));
	}

	private static int getVolume(int i, int numOfBuckets, String[] chunks) {
		return getInt(getChunk(i + numOfBuckets, chunks));
	}

	private static int getTarget(int i, int numOfBuckets, String[] chunks) {
		return getInt(getChunk(i + 2 * numOfBuckets, chunks));
	}

	private static String getChunk(int i, String[] chunks) {
		return getChunk(2, i, chunks);
	}

	private static String getChunk(int offset, int i, String[] chunks) {
		return chunks[offset + i];
	}

	public int getId() {
		return id;
	}

	public int getNumOfBuckets() {
		return numOfBuckets;
	}

	public boolean isFinished() {
		for (Bucket bucket : getBuckets()) {
			if (!bucket.isFinished()) {
				return false;
			}
		}
		return true;
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
	public BucketInstance clone() {
		Bucket[] buckets = new Bucket[this.getNumOfBuckets()];

		for (int i = 0; i < getNumOfBuckets(); i++) {
			buckets[i] = getBuckets()[i].clone();
		}

		return new BucketInstance(getId(), getNumOfBuckets(), buckets);
	}

	public String getIdentifier() {
		StringBuffer code = new StringBuffer();

		for (int i = 1; i <= getNumOfBuckets(); i++) {
			code.append(i);
			code.append(getBuckets()[i - 1].getVolume());
		}

		return code.toString();
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
