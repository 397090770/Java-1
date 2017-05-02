package com.yanshu.myManven.mappers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class OutputHtml extends TableMapper<Text, Text> {

	protected void map(ImmutableBytesWritable key, Result value,
			Mapper<ImmutableBytesWritable, Result, Text, Text>.Context context)
			throws IOException, InterruptedException {

		if (value != null) {
			boolean utf8Flag = false;
			String html = getValue("data", "html", value, utf8Flag);

			if (html.contains("http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"")) {
				utf8Flag = true;
			}

			if (html.contains("content=\"text/html; charset=utf-8\"")) {
				utf8Flag = true;
			}

			if (html.contains("<meta charset=\"utf-8\">")) {
				utf8Flag = true;
			}

			html = getValue("data", "html", value, utf8Flag);
			html += "\t" + getValue("data", "url", value, utf8Flag);
			html += "\t" + getValue("data", "time", value, utf8Flag);

			context.write(new Text(html), new Text());
		}
	}

	public static String getValue(String columnFamily, String qualifier,
			Result cellValue, boolean utf8Flag)
			throws UnsupportedEncodingException {

		String value = "";
		if (utf8Flag) {
			value = new String(cellValue.getValue(Bytes.toBytes(columnFamily),
					Bytes.toBytes(qualifier)), "UTF-8");
		} else {
			value = new String(cellValue.getValue(Bytes.toBytes(columnFamily),
					Bytes.toBytes(qualifier)), "gbk");
		}
		return value;
	}
}