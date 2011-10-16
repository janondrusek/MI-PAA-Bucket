package cz.cvut.fit.mi_paa.bucket.comparator;

import cz.cvut.fit.mi_paa.bucket.Bucket;
import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public class EuclidHeuristicComparator extends AbstractHeuristicComparator {

	@Override
	protected int getScore(BucketInstance instance) {
		int sum = 0;
		for (Bucket bucket : instance.getBuckets()) {
			sum += (bucket.getTarget() - bucket.getVolume());
		}
		return sum;
	}

}
