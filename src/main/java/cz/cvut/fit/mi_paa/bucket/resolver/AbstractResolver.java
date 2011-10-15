package cz.cvut.fit.mi_paa.bucket.resolver;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;
import cz.cvut.fit.mi_paa.bucket.result.Result;

public abstract class AbstractResolver implements Resolver {

	private Queue<BucketInstance> enqueued;

	private BucketInstance instance;

	private Set<String> visited = new HashSet<>();

	private Result result = new Result();

	public AbstractResolver(BucketInstance instance) {
		this.instance = instance;
		enqueued = createQueque();
	}

	abstract protected Queue<BucketInstance> createQueque();

	protected void markVisited(BucketInstance instance) {
		getVisited().add(instance.getIdentifier());
	}

	protected BucketInstance getBucketInstance() {
		return instance;
	}

	protected Queue<BucketInstance> getEnqueued() {
		return enqueued;
	}

	protected Set<String> getVisited() {
		return visited;
	}

	protected Result getResult() {
		return result;
	}

}
