package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import advent.utils.AdventMathUtils;

public class AdventMathUtilsTest {

	@Test
	public void testMode() {
		int mostFrequentInteger = AdventMathUtils.calculateMode(List.of(1, 3, 4, 3, 4, 3, 2, 3, 3, 3, 3, 3));
		assertEquals(3, mostFrequentInteger);

		char mostFrequentChar = AdventMathUtils.calculateMode(List.of('1', '3', '4', '3', '4', '3', '2', '3', '3', '3', '3', '3'));
		assertEquals('3', mostFrequentChar);
	}

	@Test
	public void testMostFrequentElementsWithOne() {
		List<Integer> mostFrequentList = AdventMathUtils.findMostFrequentElementsInList(List.of(1, 3, 4, 3, 4, 3, 2, 3, 3, 3, 3, 3));
		assertEquals(1, mostFrequentList.size());
		assertEquals(3, (int) mostFrequentList.get(0));
	}

	@Test
	public void testMostFrequentElementsWithMultiple() {
		List<Integer> mostFrequentList = AdventMathUtils.findMostFrequentElementsInList(List.of(3, 3, 3, 3, 3, 1, 1, 1, 1, 1));
		assertEquals(2, mostFrequentList.size());
	}
}
