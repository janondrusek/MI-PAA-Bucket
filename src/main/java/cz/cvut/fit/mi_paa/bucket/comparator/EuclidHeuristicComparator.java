package cz.cvut.fit.mi_paa.bucket.comparator;

import cz.cvut.fit.mi_paa.bucket.Bucket;
import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public class EuclidHeuristicComparator extends AbstractHeuristicComparator {

	@Override
	protected int getScore(BucketInstance instance) {
		int score = 0;
		for (Bucket bucket : instance.getBuckets()) {
			score += (bucket.getTarget() - bucket.getVolume());
		}
		return score;
	}

	@Override
	protected int getResult(int left, int right) {
		return left - right;
	}

}
