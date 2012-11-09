package com.dm.experiment.domain

import com.dm.experiment.choosing.GoalDifferenceComparator;
import com.dm.experiment.filereading.FootballResultsReader;

import spock.lang.Specification;

class GoalDifferenceChooserSpec extends Specification {
	def smallestGoalDifferenceChooser = new SmallestGoalDifferenceChooser()
	FootballResultsReader reader = Mock()
	GoalDifferenceComparator comparator = Mock()
	
	def setup() {
		smallestGoalDifferenceChooser.reader = reader
		smallestGoalDifferenceChooser.comparator = comparator
	}
	def "check that data is read in and then the smallest difference entry returned"() {
		given:
			reader.readStream('stream') >> ['one','two','three']
			comparator.chooseSmallestGoalDifference(['one','two','three']) >> 'two'
				
		when:
			def result = smallestGoalDifferenceChooser.findSmallestGoalDifference('stream')

		then:
			result == 'two'
	}
	
	def "check the smallest difference entry different returns different"() {
		given:
			reader.readStream('stream') >> ['one','two','three']
			comparator.chooseSmallestGoalDifference(['one','two','three']) >> 'three'
				
		when:
			def result = smallestGoalDifferenceChooser.findSmallestGoalDifference('stream')

		then:
			result == 'three'
	}
	
	def "check the smallest difference chosen from the read file different"() {
		given:
			reader.readStream('stream') >> ['a','b','c']
			comparator.chooseSmallestGoalDifference(['a','b','c']) >> 'three'
				
		when:
			def result = smallestGoalDifferenceChooser.findSmallestGoalDifference('stream')

		then:
			result == 'three'
	}
}