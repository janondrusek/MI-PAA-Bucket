package cz.cvut.fit.mi_paa.bucket.result;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;

public class Result {
	private long manipulations;

	private long visitedVertices;

	private BucketInstance instance;

	public void incrementManipulations() {
		manipulations++;
	}

	public long getManipulations() {
		return manipulations;
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
		sb.append("manipulations: ");
		sb.append(getManipulations());
		sb.append(", visitedVertices: ");
		sb.append(getVisitedVertices());
		sb.append(", bucketInstance");
		sb.append(getBucketInstance());
		sb.append("]");

		return sb.toString();
	}

}
