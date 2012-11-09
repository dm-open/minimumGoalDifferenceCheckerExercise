package com.dm.experiment.choosing

class GoalDifferenceComparatorImpl implements GoalDifferenceComparator {

	public def chooseSmallestGoalDifference(list) {
		def teamsForDifferences = list.groupBy{ Math.abs(it.goalsFor - it.goalsAgainst) }
		def goalDifferences = teamsForDifferences.keySet() as List
		def minGoalDifference = goalDifferences.sort()[0]
		return teamsForDifferences[minGoalDifference];
	}
}
