package com.dm.experiment.domain

import com.dm.experiment.choosing.GoalDifferenceComparatorImpl;
import com.dm.experiment.filereading.FootballLineReaderImpl;
import com.dm.experiment.filereading.FootballResultsReaderImpl;

import spock.lang.Specification;

class Checker extends Specification {
	
	def "run"() {
		
		def reader = new FootballResultsReaderImpl()
		def lineReader = new FootballLineReaderImpl()
		def comparator = new GoalDifferenceComparatorImpl()
		SmallestGoalDifferenceChooser chooser = new SmallestGoalDifferenceChooser()
		
		reader.lineReader = lineReader
		chooser.reader = reader
		chooser.comparator = comparator
		
		given:
			def content = 'http://pragdave.pragprog.com/data/football.dat'.toURL().text
				
		when:
			def result = chooser.findSmallestGoalDifference(new ByteArrayInputStream(content.bytes));

		then:
			result.size() == 1
			result[0].team == 'Aston_Villa'
			result[0].goalsFor == 46
			result[0].goalsAgainst == 47
	}

	
}
