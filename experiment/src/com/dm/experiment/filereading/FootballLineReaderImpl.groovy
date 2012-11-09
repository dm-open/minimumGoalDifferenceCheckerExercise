package com.dm.experiment.filereading

import com.dm.experiment.domain.FootballResult;

import groovy.transform.WithReadLock;

class FootballLineReaderImpl implements FootballLineReader {

	public def readLine(line) {
		def trimmedLine = line.trim()
		
		if(trimmedLine.startsWith("Team") || trimmedLine.startsWith("-")) {
			return null
		}
		
		def resultStringArray = trimmedLine.split(" ")
		def removedZeroLengthElements = removeZeroLengthElements(resultStringArray)
		
		resultFromElements(removedZeroLengthElements)
	}
	
	private def removeZeroLengthElements(array) {
		def withoutZeroLengthElements = []
		
		for(def element : array) {
			if(element != '') {
				withoutZeroLengthElements << element
			}
		}
		
		withoutZeroLengthElements
	}
	
	private def resultFromElements(array) {
		FootballResult result = new FootballResult()
		result.team = array[1]
		result.goalsFor = array[6].toInteger()
		result.goalsAgainst = array[8].toInteger()
		result
	}
}
