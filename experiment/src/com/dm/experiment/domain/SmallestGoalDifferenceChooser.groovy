package com.dm.experiment.domain

import com.dm.experiment.choosing.GoalDifferenceComparator;
import com.dm.experiment.filereading.FootballResultsReader;

class SmallestGoalDifferenceChooser {
	FootballResultsReader reader
	GoalDifferenceComparator comparator
	
	def findSmallestGoalDifference(file) {
		def list = reader.readStream(file);
		return comparator.chooseSmallestGoalDifference(list)
	}
}
