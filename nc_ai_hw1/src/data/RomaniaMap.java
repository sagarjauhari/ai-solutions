package data;

/**
 * Weighted graph of Romanian road system.
 * @author Sagar Jauhari <sjauhar@ncsu.edu>
 * 
 */
public class RomaniaMap {
	public String[][] roads = { { "oradea", "zerind", "71" },
			{ "zerind", "arad", "75" }, { "arad", "timisoara", "118" },
			{ "timisoara", "lugoj", "111" }, { "lugoj", "mehadia", "70" },
			{ "dobreta", "mehadia", "75" }, { "oradea", "sibiu", "151" },
			{ "arad", "sibiu", "140" }, { "dobreta", "craiova", "120" },
			{ "sibiu", "rimnicu_vilcea", "80" }, { "sibiu", "fagaras", "99" },
			{ "rimnicu_vilcea", "craiova", "146" },
			{ "pitesti", "craiova", "138" },
			{ "rimnicu_vilcea", "pitesti", "97" },
			{ "bucharest", "pitesti", "101" },
			{ "bucharest", "fagaras", "211" },
			{ "bucharest", "giurgiu", "90" },
			{ "bucharest", "urziceni", "85" }, { "vaslui", "urziceni", "142" },
			{ "hirsova", "urziceni", "98" }, { "hirsova", "eforie", "86" },
			{ "vaslui", "iasi", "92" }, { "neamt", "iasi", "87" } };

/******** Old prolog snippets ************
twoWayRoad(City1, City2, Distance):-
road(City1, City2, Distance).
twoWayRoad(City1, City2, Distance):-
road(City2, City1, Distance).

move(City1, City2):- twoWayRoad(City1, City2, _).

move(City1, City2, Distance):-
  twoWayRoad(City1, City2, Distance).
*******************************************/

//==============================================================================
//  
//   Decimal latitude and longitude of Romanian cities.
//   Useful in computing D-2 (Pythagorian) distance in the heuristic.
//
//   0.00001 degrees is about 1 m precision, ridiculous overkill for a city-to-city
//   heuristic.
//
//   city(name, latitude north, longitude east)
//   
//==============================================================================
	public String[][] cities = { { "arad", "46.16667", "21.3" },
			{ "bucharest", "44.41667", "26.1" },
			{ "craiova", "44.33333", "23.81667" },
			{ "dobreta", "44.5", "23.95" },
			{ "eforie", "44.06667", "28.63333" },
			{ "fagaras", "45.84472", "24.97417" },
			{ "giurgiu", "43.90083", "25.97389" },
			{ "hirsova", "46.68333", "27.53333" },
			{ "iasi", "47.16222", "27.58889" },
			{ "lugoj", "45.68611", "21.90056" },
			{ "mehadia", "44.90083", "22.35028" },
			{ "neamt", "46.9275", "26.37083" },
			{ "oradea", "47.07222", "21.92111" },
			{ "pitesti", "44.86056", "24.86778" },
			{ "rimnicu_vilcea", "45.10472", "24.37556" },
			{ "sibiu", "45.79583", "24.15222" },
			{ "timisoara", "45.75972", "21.23" },
			{ "urziceni", "44.71806", "26.64528" },
			{ "vaslui", "46.63833", "27.72917" },
			{ "zerind", "46.63333", "21.66667" } };
}
