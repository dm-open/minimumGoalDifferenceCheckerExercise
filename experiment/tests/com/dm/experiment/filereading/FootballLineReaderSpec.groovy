package com.dm.experiment.filereading

import com.dm.experiment.filereading.FootballLineReaderImpl;

import spock.lang.Specification;

class FootballLineReaderSpec extends Specification {
	def reader = new FootballLineReaderImpl()
	
	def "check that starts with Team returns null"() {
		given:
			def line = '  Team  '
		
		when:
			def result = reader.readLine(line)
	
		then:
			result == null
	}
	
	def "check that starts with - returns null"() {
		given:
			def line = '  ----  '
		
		when:
			def result = reader.readLine(line)
	
		then:
			result == null
	}
	
	def "check that valid data returns populated FootballResult object"() {
		given:
			def line = '   12. Middlesbrough   38    12   9  17    35  -  47    45'
		
		when:
			def result = reader.readLine(line)
	
		then:
			result.team == 'Middlesbrough'
			result.goalsFor == 35
			result.goalsAgainst == 47
	}
	
	def "check that valid data returns populated FootballResult object from different data"() {
		given:
			def line = '   10. Blackburn       38    12  10  16    55  -  51    46'
		
		when:
			def result = reader.readLine(line)
	
		then:
			result.team == 'Blackburn'
			result.goalsFor == 55
			result.goalsAgainst == 51
	}

}
