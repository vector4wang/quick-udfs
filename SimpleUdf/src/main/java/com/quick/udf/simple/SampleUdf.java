package com.quick.udf.simple;


import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 *
 * @author wangxc
 * @date: 2020/3/30 下午9:26
 * https://www.cnblogs.com/swordfall/p/11167486.html
 * Hive中有3种UDF：
 *
 * 　　UDF：操作单个数据行，产生单个数据行；
 * 　　UDAF：操作多个数据行，产生一个数据行；
 * 　　UDTF：操作一个数据行，产生多个数据行一个表作为输出；
 */
public class SampleUdf extends UDF {

	public Text evaluate(Text input) {
		if(input == null) return null;
		return new Text("udf: " + input.toString());
	}
}
