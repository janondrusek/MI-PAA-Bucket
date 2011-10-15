package cz.cvut.fit.mi_paa.bucket.resolver;

import java.util.LinkedList;
import java.util.Queue;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;
import cz.cvut.fit.mi_paa.bucket.result.Result;

public class BruteForceResolver extends AbstractResolver {

	public BruteForceResolver(BucketInstance instance) {
		super(instance);
	}

	@Override
	protected Queue<BucketInstance> createQueque() {
		return new LinkedList<>();
	}

	@Override
	public Result solve() {

		getEnqueued().add(getBucketInstance());
		markVisited(getBucketInstance());
		getResult().incremenVisitedtVertices();

		while (!getEnqueued().isEmpty()) {
			BucketInstance polled = getEnqueued().poll();

			if (polled.isFinished()) {
				getResult().setBucketInstance(polled);
				break;
			}

			for (int i = 0; i < getBucketInstance().getNumOfBuckets(); i++) {
				for (int j = 0; j < getBucketInstance().getNumOfBuckets(); j++) {
					if (i == j) {
						continue;
					}

					if (polled.getBuckets()[i].isNotEmpty() && polled.getBuckets()[j].isNotFull()) {
						enqueue(pour(i, j, polled));
					}

					if (polled.getBuckets()[j].isNotEmpty() && polled.getBuckets()[i].isNotFull()) {
						enqueue(pour(j, i, polled));
					}
				}
			}

			for (int i = 0; i < getBucketInstance().getNumOfBuckets(); i++) {
				if (polled.getBuckets()[i].isNotFull()) {
					enqueue(pour(i, polled));
				}

				if (polled.getBuckets()[i].isNotEmpty()) {
					enqueue(empty(i, polled));
				}
			}

		}
		return getResult();
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
}
