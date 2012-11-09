package com.dm.experiment.choosing

import spock.lang.Specification

import com.dm.experiment.domain.FootballResult

class GoalDifferenceComparatorSpec extends Specification {
	def comparator = new GoalDifferenceComparatorImpl()
	
	def "return with smallest goal difference - both positive diff, one is smallest"() {
		given:
			FootballResult first = new FootballResult(team:'first',goalsFor:5, goalsAgainst: 4)
			FootballResult second = new FootballResult(team:'second',goalsFor:15, goalsAgainst: 7)
				
		when:
			def result = comparator.chooseSmallestGoalDifference([first, second])

		then:
			result.size() == 1
			result[0].team == 'first'
	}
	
	def "return with smallest goal difference - both positive diff, other is smallest"() {
		given:
			FootballResult first = new FootballResult(team:'first',goalsFor:200, goalsAgainst: 4)
			FootballResult second = new FootballResult(team:'second',goalsFor:15, goalsAgainst: 7)
				
		when:
			def result = comparator.chooseSmallestGoalDifference([first, second])

		then:
			result.size() == 1
			result[0].team == 'second'
	}
	
	def "return with smallest goal difference - smallest diff is negative"() {
		given:
			FootballResult first = new FootballResult(team:'first', goalsFor:10, goalsAgainst: 4)
			FootballResult second = new FootballResult(team:'second', goalsFor:5, goalsAgainst: 10)
				
		when:
			def result = comparator.chooseSmallestGoalDifference([first, second])

		then:
			result.size() == 1
			result[0].team == 'second'
	}
	
	def "return with smallest goal difference - more than one equal"() {
		given:
			FootballResult first = new FootballResult(team:'first',goalsFor:10, goalsAgainst: 20)
			FootballResult second = new FootballResult(team:'second',goalsFor:5, goalsAgainst: 10)
			FootballResult third = new FootballResult(team:'third',goalsFor:4, goalsAgainst: 9)
				
		when:
			def result = comparator.chooseSmallestGoalDifference([first, second, third])

		then:
			result.size() == 2
			result[0].team == 'second'
			result[1].team == 'third'
	}
}
