package com.example.springtablesample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ArrayTable;

@Controller
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String index(Model model) {

		model.addAttribute("tables", createTables(createItems()));
		return "index";
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ArrayTable<Specification, Specification, Item>> list() {
		return createTables(createItems());
	}

	/**
	 * 商品データを作成する.
	 * 
	 * @return 商品データ
	 */
	private List<Item> createItems() {
		List<Item> list = new ArrayList<>();
		list.add(new Item("ノート A4 30枚 7mm", new Specification("サイズ", "A4"), new Specification("枚数", "30枚"),
				new Specification("罫幅", "7mm")));
		list.add(new Item("ノート A4 50枚 7mm", new Specification("サイズ", "A4"), new Specification("枚数", "50枚"),
				new Specification("罫幅", "7mm")));
		list.add(new Item("ノート A4 100枚 7mm", new Specification("サイズ", "A4"), new Specification("枚数", "100枚"),
				new Specification("罫幅", "7mm")));

		list.add(new Item("ノート B5 30枚 7mm", new Specification("サイズ", "B5"), new Specification("枚数", "30枚"),
				new Specification("罫幅", "7mm")));
		list.add(new Item("ノート B5 50枚 7mm", new Specification("サイズ", "B5"), new Specification("枚数", "50枚"),
				new Specification("罫幅", "7mm")));
		list.add(new Item("ノート B5 100枚 7mm", new Specification("サイズ", "B5"), new Specification("枚数", "100枚"),
				new Specification("罫幅", "7mm")));

		list.add(new Item("ノート B5 30枚 6mm", new Specification("サイズ", "B5"), new Specification("枚数", "30枚"),
				new Specification("罫幅", "6mm")));
		list.add(new Item("ノート B5 50枚 6mm", new Specification("サイズ", "B5"), new Specification("枚数", "50枚"),
				new Specification("罫幅", "6mm")));

		list.add(new Item("ノート A5 50枚 6mm", new Specification("サイズ", "A5"), new Specification("枚数", "50枚"),
				new Specification("罫幅", "6mm")));

		return list;
	}

	/**
	 * リストをテーブル構造に変換する.
	 * 
	 * @param items
	 *            商品データのリスト
	 * @return 商品データのテーブルのリスト
	 */
	private List<ArrayTable<Specification, Specification, Item>> createTables(List<Item> items) {
		Map<String, List<Specification>> map = items.stream().map(Item::getSpecifications).flatMap(List::stream)
				.sorted((e0, e1) -> e0.getFixedLengthValue().compareTo(e1.getFixedLengthValue()))
				.collect(Collectors.groupingBy(Specification::getName));

		List<ArrayTable<Specification, Specification, Item>> list = new ArrayList<>();
		List<String[]> combi = createCombination(items.stream().flatMap(e -> e.getSpecifications().stream())
				.map(Specification::getName).collect(Collectors.toSet()));

		for (String[] c : combi) {
			ArrayTable<Specification, Specification, Item> t = items.stream().collect(() -> {
				return ArrayTable.create(new TreeSet<>(map.get(c[0])), new TreeSet<>(map.get(c[1])));
			}, (acc, e) -> acc.put(e.findSpecification(c[0]), e.findSpecification(c[1]), e), (e1, e2) -> e1.putAll(e2));
			list.add(t);
		}

		return list;
	}

	/**
	 * 引数から2つの組み合わせを求める.
	 * 
	 * @param specificationNames
	 *            Specification の名前のリスト
	 * @return 組み合わせ
	 */
	private List<String[]> createCombination(Collection<String> specificationNames) {
		if (specificationNames.size() < 2) {
			throw new IllegalArgumentException("specifications の長さは2以上でなけばなりません。 size: " + specificationNames.size());
		}

		Set<String> set = new HashSet<>(specificationNames);
		Queue<String> queue = new LinkedList<>(set);

		List<String[]> list = new ArrayList<>();
		String s0;
		while ((s0 = queue.poll()) != null) {
			for (String s1 : queue) {
				list.add(new String[] { s0, s1 });
			}
		}
		return list;
	}
}
