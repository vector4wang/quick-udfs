package com.quick.udf;

import com.quick.udf.simple.SampleUdf;
import org.apache.hadoop.io.Text;
import org.junit.Test;

public class TestUdf {

	@Test
	public void testUDF() {
		SampleUdf example = new SampleUdf();
		System.out.println(example.evaluate(new Text("world")).toString());
	}
}