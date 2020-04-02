package com.quick.udf;

import junit.framework.Assert;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.JavaBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

}
