package ohtuesimerkki;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {

	Reader readerStub = () -> {
		ArrayList<Player> players = new ArrayList<Player>();

		players.add(new Player("Semenko", "EDM", 4, 12));
		players.add(new Player("Lemieux", "PIT", 45, 54));
		players.add(new Player("Kurri", "EDM", 37, 53));
		players.add(new Player("Yzerman", "DET", 42, 56));
		players.add(new Player("Gretzky", "EDM", 35, 89));

		return players;
	};

	Statistics stats;

	@Before
	public void setUp() {
		// luodaan Statistics-olio joka käyttää "stubia"
		stats = new Statistics(readerStub);
	}

	@Test
	public void testSearchFound() {
		assertNotNull(stats.search("Semenko"));
	}

	@Test
	public void testSearchNotFound() {
		assertNull(stats.search("Semenka"));
	}

	@Test
	public void testTeamFound() {
		List<Player> team = stats.team("EDM");
		assertEquals("Semenko", team.get(0).getName());
		assertEquals(37, team.get(1).getGoals());
		assertEquals(89, team.get(2).getAssists());
	}

	@Test
	public void testTeamNotFound() {
		assertTrue(stats.team("FOO").size() == 0);
	}

	@Test
	public void testTopScorers() {
		assertEquals(124, stats.topScorers(1).get(0).getPoints());
	}
}