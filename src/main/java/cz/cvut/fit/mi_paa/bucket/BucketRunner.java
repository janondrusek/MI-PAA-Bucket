package cz.cvut.fit.mi_paa.bucket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.management.ManagementFactory;

import cz.cvut.fit.mi_paa.bucket.result.Result;

public class BucketRunner {
	public static void main(String[] args) {
		long startCpu = getCpuTime();
		long startTimestamp = System.currentTimeMillis();
		try {
			BucketInstance instance = getBucketInstance(getBucketReader(args[0]), args[1]);
			solveBucketInstance(instance);
		} catch (Exception e) {
			help(e.getMessage());
			e.printStackTrace();
		} finally {
			printTimeInfo("Total operation", startCpu, startTimestamp);
		}
	}

	private static BucketInstance getBucketInstance(BucketReader bucketReader, String id) {
		BucketInstance instance = null;
		while (bucketReader.hasNext()) {
			BucketInstance bi = bucketReader.next();
			if (bi.getId() == Integer.parseInt(id)) {
				instance = bi;
				break;
			}
		}
		return instance;
	}

	private static BucketReader getBucketReader(String path) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		return new BucketReader(br);
	}

	private static void solveBucketInstance(BucketInstance instance) {
		System.out.println(instance);
		Result result = instance.solveBruteForce();
		System.out.println(result);
	}

	private static void help(String message) {
		System.out.println(message);
		System.out
				.println("Usage: java -jar "
						+ BucketRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath()
						+ " datafile.txt id");
	}

	private static long getCpuTime() {
		return ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getProcessCpuTime();
	}

	private static void printTimeInfo(String operationName, long startCpu, long startTimestamp) {
		System.out.printf("%s took %.2f CPU s, real: %.2f s%n", operationName, (getCpuTime() - startCpu) / 1000000000D,
				(System.currentTimeMillis() - startTimestamp) / 1000D);
	}
}
