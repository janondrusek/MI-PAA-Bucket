package cz.cvut.fit.mi_paa.bucket.resolver;

import java.util.LinkedList;
import java.util.Queue;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public class BruteForceResolver extends AbstractResolver {

	public BruteForceResolver(BucketInstance instance) {
		super(instance);
	}

	@Override
	protected Queue<BucketInstance> createQueque() {
		return new LinkedList<>();
	}

}
