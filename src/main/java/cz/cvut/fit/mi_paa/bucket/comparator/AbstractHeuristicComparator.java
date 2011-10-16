package cz.cvut.fit.mi_paa.bucket.comparator;

import java.util.Comparator;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public abstract class AbstractHeuristicComparator implements Comparator<BucketInstance> {

	@Override
	public int compare(BucketInstance left, BucketInstance right) {
		int leftScore = getScore(left);
		int rightScore = getScore(right);
		return getResult(leftScore, rightScore);
	}

	private int getResult(int left, int right) {
		if (left < right) {
			return -1;
		} else if (left > right) {
			return 1;
		}
		return 0;
	}

	abstract protected int getScore(BucketInstance instance);

}
