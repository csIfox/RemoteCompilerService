package com.ifox.rcs.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.SequenceInputStream;

import com.ifox.rcs.util.LogUtils;

public class JavaCompiler {

	public String doJavac(String userPath, String fileName) {
		try {
			Process process = Runtime.getRuntime().exec("cmd");
			OutputStream outputStream = process.getOutputStream();
			outputStream.write(("cd " + userPath + "\r\n").getBytes());
			outputStream.write(("javac " + fileName + "\r\n").getBytes());
			outputStream.close();
			SequenceInputStream inputStream = new SequenceInputStream(
					process.getInputStream(), process.getErrorStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "gbk"));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				i++;
				if (i < 8) {
					continue;
				} else if (i == 8) {
					line = line.replace(userPath + ">", "");
				} else if (i > 9) {
					break;
				}
				stringBuffer.append(line);
			}
			String result = stringBuffer.toString().trim();
			System.out.println(result);
			reader.close();
			return result;
		} catch (IOException e) {
			LogUtils.e(e);
			return "Exception";
		}
	}

}
