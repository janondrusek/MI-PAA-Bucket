package cz.cvut.fit.mi_paa.bucket.comparator;

import java.util.Comparator;

import cz.cvut.fit.mi_paa.bucket.Bucket;
import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public class EuklidHeuristicComparator<T> implements Comparator<BucketInstance> {

	@Override
	public int compare(BucketInstance left, BucketInstance right) {
		int leftScore = getScore(left);
		int rightScore = getScore(right);
		return getResult(leftScore, rightScore);
	}

	private int getScore(BucketInstance instance) {
		int sum = 0;
		for (Bucket bucket : instance.getBuckets()) {
			sum += (bucket.getTarget() - bucket.getVolume());
		}
		return sum;
	}

	private int getResult(int left, int right) {
		if (left < right) {
			return -1;
		} else if (left > right) {
			return 1;
		}
		return 0;
	}

}
