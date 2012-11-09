package com.dm.experiment.filereading

import javax.management.BetweenQueryExp;

import com.dm.experiment.filereading.FootballLineReader;
import com.dm.experiment.filereading.FootballResultsReaderImpl;

import spock.lang.Specification;

class FootballResultsReaderSpec extends Specification {
	def reader = new FootballResultsReaderImpl()
	FootballLineReader lineReader = Mock()
	
	def setup() {
		reader.lineReader = lineReader
	} 
	
	def "check that before and including pre are not delegated"(){
		given:
			def content = 'before\n' + betweenPre('someline')
			def stream = new ByteArrayInputStream(content.getBytes());
			lineReader.readLine('before') >> 'notwanted'
			lineReader.readLine('<pre>') >> 'notwanted'
			lineReader.readLine('someline') >> 'result'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['result']
	}
	
	def "check that after and including close pre are not delegated"(){
		given:
			def content = betweenPre('someline') + '\nafter'
			def stream = new ByteArrayInputStream(content.getBytes());
			lineReader.readLine('</pre>') >> 'notwanted'
			lineReader.readLine('after') >> 'notwanted'
			lineReader.readLine('someline') >> 'result'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['result']
	}
	
	def "check that data read creates result object from valid line between pre"() {
		given:
			def stream = new ByteArrayInputStream(betweenPre('someline').getBytes());
			lineReader.readLine('someline') >> 'result'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['result']
	}
	
	def "check that data read creates differening result object from valid line between pre"() {
		given:
			def stream = new ByteArrayInputStream(betweenPre('someline').getBytes());
			lineReader.readLine('someline') >> 'other'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['other']
	}
	
	def "check that data read creates result object from different valid line between pre"() {
		given:
			def stream = new ByteArrayInputStream(betweenPre('someotherline').getBytes());
			lineReader.readLine('someotherline') >> 'result'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['result']
	}
	
	def "check that data read creates result objects from different valid lines between pre"() {
		given:
			def stream = new ByteArrayInputStream(betweenPre('line\nsomeotherline').getBytes());
			lineReader.readLine('line') >> 'result1'
			lineReader.readLine('someotherline') >> 'result2'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['result1', 'result2']
	}
	
	def "check that data read creates result objects from different invalid lines between pre"() {
		given:
			def stream = new ByteArrayInputStream(betweenPre('line\nsomeotherline').getBytes());
			lineReader.readLine('line') >> 'result1'
		
		when:
			def result = reader.readStream(stream)
	
		then:
			result == ['result1']
	}
	
	private def betweenPre(string) {
		"<pre>\n${string}\n</pre>"
	}
}
