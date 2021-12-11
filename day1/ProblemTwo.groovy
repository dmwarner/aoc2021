class ProblemTwo {

    static void main(String[] args) {
        println 'hello'

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day1',
            'input.txt').collect {it} as int[]
        println "input size: ${input.size()}"

        def windowSums = []

        def lastVal
        def increaseCount = 0

        for (int i = 0;i<input.size(); i++) {
        //for (int i = 0; i<10; i++) {
            //println input[i]
            def v1 = readInput(input, i-2)
            def v2 = readInput(input, i-1)
            def v3 = readInput(input, i)
//            println "${v1}, ${v2}, ${v3}"
            println "window sum = ${sumWindow([v1,v2,v3])} for ${v1} ${v2} ${v3}"
            def windowSum = sumWindow([v1, v2, v3])
            if (windowSum > -1) {
                windowSums << windowSum
            }
        }

        println windowSums.size()
        windowSums.each {
            if (lastVal > 0) {
                if (it > lastVal) {
                    increaseCount++
                }
            }
            lastVal = it
        }

        //println windowSums
        println "increaseCount = ${increaseCount}"
        //OFF BY +1
    }

    static int sumWindow(def window) {
        if (window[0] > 0 && window[1] > 0 && window[2] > 0) {
            return window.sum()
        } else {
            return -1
        }
    }


    static int readInput(def input, int position) {
        try {
            if (position > 0) {
                return input[position]
            } else {
                return -1
            }
        } catch(all) {
            println 'bad position'
            return -1
        }
    }

}
