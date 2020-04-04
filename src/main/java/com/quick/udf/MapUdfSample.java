package com.quick.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author wangxc
 * @date: 2020/3/31 下午11:16
 *	a,b,c -> str_to_map(x:a,Y;b,z:c)
 */
public class MapUdfSample extends GenericUDF {

	private final Map<Text, Text> resultMap = new LinkedHashMap<Text, Text>();

	private final Map<String, String> config_map = new LinkedHashMap<String, String>();

	private transient ObjectInspector[] argumentOIs;


	public void initConfigMap(){
		config_map.put("a","100");
		config_map.put("b","200");
		config_map.put("c","300");
		config_map.put("e","400");
	}

	public Object evaluate(DeferredObject[] arguments) throws HiveException {
		resultMap.clear(); // 重要

		Object key1 = arguments[0].get();
		Object key2 = arguments[1].get();
		Object key3 = arguments[2].get();
		String key1Str = "";
		if (key1 != null) {
			StringObjectInspector soi0 = (StringObjectInspector)argumentOIs[0];
			key1Str = soi0.getPrimitiveJavaObject(key1);
		}
		StringObjectInspector soi1 = (StringObjectInspector)argumentOIs[1];
		StringObjectInspector soi2 = (StringObjectInspector)argumentOIs[2];
		String key2Str = soi1.getPrimitiveJavaObject(key2);
		String key3Str = soi2.getPrimitiveJavaObject(key3);
		resultMap.put(new Text("key1"), new Text(key1Str));
		resultMap.put(new Text("key2"), new Text(key2Str));
		resultMap.put(new Text("key3"), new Text(config_map.get(key3Str)));
		return resultMap;
	}




	public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
		if (arguments.length == 0) {
			throw new UDFArgumentException("UrlParamsToMap param must be more argus.");
		}
		initConfigMap();
		argumentOIs = arguments;
		return ObjectInspectorFactory
				.getStandardMapObjectInspector(PrimitiveObjectInspectorFactory.writableStringObjectInspector,
						PrimitiveObjectInspectorFactory.writableStringObjectInspector);
	}



	public String getDisplayString(String[] children) {
		return "map(" + children[0] + ")";
	}

}