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

	private Result result = new Result(this);

	public AbstractResolver(BucketInstance instance) {
		this.instance = instance;
	}

	abstract protected Queue<BucketInstance> createQueque();

	@Override
	public Result solve() {
		enqueued = createQueque();
		enqueue(getBucketInstance());
		doBFS();

		return getResult();
	}

	private void doBFS() {
		while (!getEnqueued().isEmpty()) {
			BucketInstance polled = getEnqueued().poll();

			if (polled.isFinished()) {
				getResult().setBucketInstance(polled);
				break;
			}

			for (int i = 0; i < getBucketInstance().getNumOfBuckets(); i++) {
				for (int j = i + 1; j < getBucketInstance().getNumOfBuckets(); j++) {
					validateAndPour(i, j, polled);
					validateAndPour(j, i, polled);
				}
			}

			for (int i = 0; i < getBucketInstance().getNumOfBuckets(); i++) {
				validateAndFill(i, polled);
				validateAndEmpty(i, polled);
			}

		}

	}

	private void validateAndPour(int i, int j, BucketInstance instance) {
		if (instance.getBuckets()[i].isNotEmpty() && instance.getBuckets()[j].isNotFull()) {
			enqueue(pour(i, j, instance));
		}
	}

	private void validateAndFill(int i, BucketInstance instance) {
		if (instance.getBuckets()[i].isNotFull()) {
			enqueue(pour(i, instance));
		}
	}

	private void validateAndEmpty(int i, BucketInstance instance) {
		if (instance.getBuckets()[i].isNotEmpty()) {
			enqueue(empty(i, instance));
		}
	}

	private void markVisited(BucketInstance instance) {
		getVisited().add(instance.getIdentifier());
	}

	private BucketInstance empty(int i, BucketInstance instance) {
		BucketInstance emptied = instance.clone();
		emptied.getBuckets()[i].empty();
		return emptied;
	}

	private BucketInstance pour(int i, BucketInstance instance) {
		BucketInstance poured = instance.clone();
		poured.getBuckets()[i].pour();
		return poured;
	}

	private boolean enqueue(BucketInstance instance) {
		if (isNotVisited(instance)) {
			getEnqueued().add(instance);
			markVisited(instance);
			getResult().incremenVisitedtVertices();
			return true;
		}
		return false;
	}

	private boolean isVisited(BucketInstance instance) {
		return isVisited(instance.getIdentifier());
	}

	private boolean isVisited(String identifier) {
		return getVisited().contains(identifier);
	}

	private boolean isNotVisited(BucketInstance instance) {
		return !isVisited(instance);
	}

	private BucketInstance pour(int i, int j, BucketInstance instance) {
		BucketInstance poured = instance.clone();
		poured.getBuckets()[j].pour(poured.getBuckets()[i].getVolume());
		poured.getBuckets()[i].pour(-(poured.getBuckets()[j].getVolume() - instance.getBuckets()[j].getVolume()));
		return poured;
	}

	private BucketInstance getBucketInstance() {
		return instance;
	}

	private Queue<BucketInstance> getEnqueued() {
		return enqueued;
	}

	private Set<String> getVisited() {
		return visited;
	}

	private Result getResult() {
		return result;
	}

}
