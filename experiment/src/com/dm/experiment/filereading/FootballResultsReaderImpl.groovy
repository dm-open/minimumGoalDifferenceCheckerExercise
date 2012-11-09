package com.dm.experiment.filereading

class FootballResultsReaderImpl implements FootballResultsReader {
	FootballLineReader lineReader
	
	def readStream(stream) {
		def results = [];
		
		def lines = getLinesBetweenPre(stream)
		lines.each { line ->
			def footballResultForLine = lineReader.readLine(line)
			if(footballResultForLine) {
				results << footballResultForLine
			}
		}
		
		results
	}
	
	private def getLinesBetweenPre(stream) {
		def lines = []
		def inPre = false
		stream.eachLine { line ->
			if(line == '</pre>') {
				inPre = false
			}
			if(inPre) {
				lines << line
			}
			if(line == '<pre>') {
				inPre = true
			}
		
		}
		lines
	}
}
