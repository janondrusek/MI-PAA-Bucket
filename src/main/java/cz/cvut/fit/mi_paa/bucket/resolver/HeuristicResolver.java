package cz.cvut.fit.mi_paa.bucket.resolver;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import cz.cvut.fit.mi_paa.bucket.BucketInstance;
import cz.cvut.fit.mi_paa.bucket.comparator.AbstractHeuristicComparator;

public class HeuristicResolver<T extends AbstractHeuristicComparator> extends AbstractResolver {

	private Class<T> clazz;

	public HeuristicResolver(Class<T> clazz, BucketInstance instance) {
		super(instance);
		this.clazz = clazz;
	}

	@Override
	protected Queue<BucketInstance> createQueque() {
		return new PriorityQueue<>(11, getComparator());
	}

	private Comparator<BucketInstance> getComparator() {
		T comparator = null;
		try {
			comparator = ConstructorUtils.invokeConstructor(clazz);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		return comparator;
	}

	@Override
	public String toString() {
		return "[" + getClass().getName() + ", comparator: " + clazz.getName() + "]";
	}
}
