package cz.cvut.fit.mi_paa.bucket.comparator;

import cz.cvut.fit.mi_paa.bucket.Bucket;
import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public class FinishedBucketsHeuristicComparator extends AbstractHeuristicComparator {

	@Override
	protected int getScore(BucketInstance instance) {
		int score = 0;
		for (Bucket bucket : instance.getBuckets()) {
			if (bucket.isFinished()) {
				score++;
			}
		}
		return score;
	}

	@Override
	protected int getResult(int left, int right) {
		return right - left;
	}

}
