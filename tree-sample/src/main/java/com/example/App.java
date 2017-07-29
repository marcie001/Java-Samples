package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Usage: ./mvnw exec:java -Dexec.args=/path/to/zenkoku.csv");
			System.err.println("Download zenkoku.csv from http://jusyo.jp/csv/new.php");
			System.exit(1);
		}

		long t = System.nanoTime();
		Tree<Address> tree = Files.lines(Paths.get(args[0]), Charset.forName("MS932"))
				.filter(e -> !e.startsWith("\"住所CD")).map(e -> e.split(",")).collect(Tree<Address>::new,
						(acc, e) -> acc.add(new Address(Integer.parseInt(e[1]), e[7]),
								new Address(Integer.parseInt(e[2]), e[9]), new Address(Integer.parseInt(e[3]), e[11])),
						(acc0, acc1) -> new UnsupportedOperationException());
		System.out.println("Time: " + (System.nanoTime() - t));

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.writeValue(new File(args[0] + ".json"), tree.getRoot());
	}
}
