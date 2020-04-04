package com.quick.udf;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StandardMapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;
import org.junit.Test;

/**
 *
 * @author wangxc
 * @date: 2020/3/31 下午10:53
 *
 */
public class TestUdf {

	@Test
	public void testUDF() {
		SampleUdf example = new SampleUdf();
		System.out.println(example.evaluate(new Text("world")).toString());
	}

	@Test
	public void testMapUdfSample() throws HiveException {
		MapUdfSample mapUdfSample = new MapUdfSample();


		ObjectInspector stringOI =  PrimitiveObjectInspectorFactory.javaStringObjectInspector;
		ObjectInspector[] ois = new ObjectInspector[3];
		ois[0] = stringOI;
		ois[1] = stringOI;
		ois[2] = stringOI;


		StandardMapObjectInspector result = (StandardMapObjectInspector) mapUdfSample.initialize(ois);
		System.out.println(result);

		Object oResult = mapUdfSample.evaluate(new GenericUDF.DeferredObject[]{new GenericUDF.DeferredJavaObject("Hello"),
				new GenericUDF.DeferredJavaObject("World"),new GenericUDF.DeferredJavaObject("a")});
		System.out.println(oResult);
	}

}
