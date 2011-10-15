package cz.cvut.fit.mi_paa.bucket.resolver;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public abstract class AbstractResolver implements Resolver {

	private BucketInstance instance;

	public AbstractResolver(BucketInstance instance) {
		this.instance = instance;
	}

	protected BucketInstance getBucketInstance() {
		return instance;
	}

}
