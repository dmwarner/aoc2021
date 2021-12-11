class ProblemOne {

    static void main(String[] args) {
        println 'hello'

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day1',
            'input.txt').collect {it} as int[]
        println "input: ${input.size()}"

        def lastVal
        def increaseCount = 0
        input.each {
            if (lastVal > 0) {
                if (it > lastVal) {
                    increaseCount++
                }
            }
            lastVal = it
        }
        println "increaseCount = ${increaseCount}"
    }

}
