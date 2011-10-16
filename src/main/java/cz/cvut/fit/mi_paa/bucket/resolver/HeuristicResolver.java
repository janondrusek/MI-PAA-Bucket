package cz.cvut.fit.mi_paa.bucket.resolver;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;
import cz.cvut.fit.mi_paa.bucket.comparator.EuklidHeuristicComparator;

public class HeuristicResolver extends AbstractResolver {

	public HeuristicResolver(BucketInstance instance) {
		super(instance);
	}

	@Override
	protected Queue<BucketInstance> createQueque() {
		return new PriorityQueue<>(11, getComparator());
	}

	private Comparator<BucketInstance> getComparator() {
		return new EuklidHeuristicComparator<BucketInstance>();
	}

}
