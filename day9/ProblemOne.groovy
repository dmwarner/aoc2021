class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day9',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"
        println input

        def maxX = input.size() -1
        def maxY = input[0].size() - 1

        def matrix = [:]
        input.eachWithIndex{line, x->
            println line
            line.eachWithIndex {val, y->
                matrix[x,y] = val
            }
        }

        println matrix
        def dangerLevel = 0
        for (int x in 0 .. maxX) {
            for (int y in 0 .. maxY) {
                println "checking ${x}, ${y} with value ${matrix[x,y]}"
                if (isLowPoint(x, y, matrix, maxX, maxY)) {
                    println "found low point with danger level ${(matrix[x,y] as int) + 1}"
                    dangerLevel += (matrix[x,y] as int) + 1
                }
            }
        }

        println "Danger Level = ${dangerLevel}"
    }

    static boolean isLowPoint(def x, def y, def matrix, def mX, def mY) {
        boolean ret = true

        def n = (y-1 > -1? matrix[x,y-1]: 99)
        def s = (y+1 <= mY? matrix[x,y+1]: 99)
        def e = (x+1 <= mX? matrix[x+1, y]: 99)
        def w = (x-1 > -1? matrix[x-1, y]: 99)

        println "   ${n} ${s} ${e} ${w}"

        matrix[x, y] < n && matrix[x,y] < s && matrix[x,y] < e && matrix[x,y] < w

    }
}
