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

	abstract protected int getResult(int left, int right);

	abstract protected int getScore(BucketInstance instance);

}
