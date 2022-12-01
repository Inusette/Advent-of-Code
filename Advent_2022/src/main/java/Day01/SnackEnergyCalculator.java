package Day01;

import java.util.Collections;
import java.util.List;

/**
 * @author Inna Pirina (<a href="mailto:Inna.Pirina@cas.de">Inna.Pirina@cas.de</a>)
 * @since 01.12.2022
 */
public class SnackEnergyCalculator {

	public static int identifySnackBundleWithTheMostEnergy(List<List<Integer>> snackEnergies) {
		List<Integer> snacksSortedByEnergy = sortTotalEnergies(snackEnergies);
		return snacksSortedByEnergy.get(0);
	}

	public static int sumTopThreeSnackBundles(List<List<Integer>> snackEnergies) {
		List<Integer> snacksSortedByEnergy = sortTotalEnergies(snackEnergies);
		return snacksSortedByEnergy.get(0) + snacksSortedByEnergy.get(1) + snacksSortedByEnergy.get(2);
	}

	private static List<Integer> sortTotalEnergies(List<List<Integer>> snackEnergies) {
		return snackEnergies.stream()
				.map(snacks -> snacks.stream().mapToInt(Integer::intValue).sum())
				.sorted(Collections.reverseOrder())
				.toList();
	}
}
