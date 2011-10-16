package cz.cvut.fit.mi_paa.bucket;

import java.io.BufferedReader;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class BucketReader implements Iterator<BucketInstance> {

	private BufferedReader br;

	private String line;

	public BucketReader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public boolean hasNext() {
		boolean next = true;
		try {
			line = br.readLine();
			if (line.trim().length() <= 0) {
				next = false;
			}
		} catch (Exception e) {
			next = false;
		}
		return next;
	}

	@Override
	public BucketInstance next() {
		return new BucketInstance(StringUtils.splitByWholeSeparator(line, null));
	}

	@Override
	public void remove() {

	}

	public void close() {
		IOUtils.closeQuietly(br);
	}

}
