package leatcode

import spock.lang.Specification

class Sum15Test extends Specification {

    def sum3 = new Sum15()

    def "empty"() {
        given:
        def empty = Collections.emptyList()
        expect:
        empty == sum3.threeSum([] as int[])
        empty == sum3.threeSum([0,0] as int[])
    }

    def "three zeroes"() {
        expect:
        [[0,0,0]] == sum3.threeSum([0,0,0] as int[])
        [[0,0,0]] == sum3.threeSum([0,0,0,0] as int[])
    }

    def "with zeroes"() {
        expect:
        [[-3,0,3],
         [-2, -1, 3],
         [-2,0,2]] == sum3.threeSum([-2,0,3,-1,2,0,-3] as int[])
    }

    def "many different"() {
        expect:
        [
            [-2, -1, 3],
            [-2,0,2]
        ] == sum3.threeSum([-2,0,6,-1,2,0,3] as int[])

        [
            [-1, -1, 2],
            [-1, 0, 1]
        ] == sum3.threeSum([-1, 0, 1, 2, -1, -4] as int[])
        [
            [-15, 0, 15],
            [-15, 5, 10]
        ] == sum3.threeSum([5,10,-15,0,15] as int[])
        [
            [-95, -5, 100],
            [-20, -10, 30],
            [-20, 0, 20],
            [-20, 10, 10],
            [-10, 0, 10],
            [-7, -7, 14],
            [-5, 0, 5],
            [-5, 1, 4],
            [-2, -2, 4],
            [-2, 1, 1],
            [0,0,0]
        ] == sum3.threeSum([-95,-20,-20,-20,-10,-7,-7,-5,-2,-2,0,0,0,0,1,1,1,1,4,5,10,10,14,20,30,100] as int[])

    }

    def "leet code error"() {
        expect:
        [
                [-5,1,4],
                [-5,2,3],
                [-3,-1,4],
                [-3,0,3],
                [-3,1,2],
                [-2,-2,4],
                [-2,-1,3],
                [-2,0,2],
                [-2,1,1],
                [-1,-1,2],
                [-1,0,1]
        ] == sum3.threeSum([-5,-3,-3,-2,-2,-2,-1,-1,
                            0,
                            1,1,1,2,3,3,4,4
        ] as int[])
    }
}
