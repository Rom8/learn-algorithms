package structures.priorityqueue.parallel

import spock.lang.Specification

class MainTest extends Specification {
    def "Calculate"() {
        given:
        def parallel

        when:
        parallel = new Main(2, [1,2,3,4,5] as long[])

        then:
        parallel.calculate() == '0 0\n1 0\n0 1\n1 2\n0 4\n'

        when:
        parallel = new Main(4, [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1] as long[])

        then:
        parallel.calculate() ==
'''0 0
1 0
2 0
3 0
0 1
1 1
2 1
3 1
0 2
1 2
2 2
3 2
0 3
1 3
2 3
3 3
0 4
1 4
2 4
3 4
'''


        when:
        parallel = new Main(2, [0,0,1,0,0,0,2,1,2,3,0,0,0,2,1] as long[])

        then:
        parallel.calculate() ==
'''0 0
0 0
0 0
1 0
1 0
1 0
1 0
0 1
0 2
1 2
0 4
0 4
0 4
0 4
1 5
'''
    }
}
