package cz.cvut.fit.mi_paa.bucket.result;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;
import cz.cvut.fit.mi_paa.bucket.resolver.Resolver;

public class Result {
	private long visitedVertices;

	private BucketInstance instance;

	private Resolver resolver;

	public Result(Resolver resolver) {
		this.resolver = resolver;
	}

	public void incremenVisitedtVertices() {
		visitedVertices++;
	}

	public long getVisitedVertices() {
		return visitedVertices;
	}

	public BucketInstance getBucketInstance() {
		return instance;
	}

	public void setBucketInstance(BucketInstance instance) {
		this.instance = instance;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("[");
		sb.append("resolver: ");
		sb.append(resolver.getClass().getName());
		sb.append(", visitedVertices: ");
		sb.append(getVisitedVertices());
		sb.append(", bucketInstance");
		sb.append(getBucketInstance());
		sb.append("]");

		return sb.toString();
	}

}
