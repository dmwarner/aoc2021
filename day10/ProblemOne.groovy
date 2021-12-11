class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day10',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        def points = []
        input.each{
            println it

            def expClose = []
            def valid = true
            it.each{
                if (valid) {
                    //println "expClose list is: ${expClose}"
                    if (it in ['(','[','{','<']) {
                        //println "push $it"
                        expClose << expectClose(it)
                    } else if (it in [')', ']', '}', '>']) {
                        //println "pop $it"
                        if (it == expClose.last()) {
                            expClose.removeLast()
                        } else {
                            println "syntax error! ${expClose}"
                            println it
                            points.add(getErrorPoints(it))
                            println points
                            valid = false
                        }
                    }
                }
            }
            println "------"
        }
        println points.sum()
    }

    static def getErrorPoints(String s) {
        switch (s) {
            case ')': return 3
            case ']': return 57
            case '}': return 1197
            case '>': return 25137
        }
    }
    static def expectClose(String s) {
        switch (s) {
            case '(': return ')'
            case '[': return ']'
            case '{': return '}'
            case '<': return '>'
        }
    }

}



/*
        int b = 12
        String gamma = ''
        String epsilon = ''

        for (int i = 0; i < b; i++) {
            def bits = input.collect { Integer.parseInt(it.substring(i, i+1)) }
            gamma += mostCommon(bits)
            epsilon += leastCommon(bits)
        }

        println gamma
        println epsilon

        println "Answer: ${Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)}"

    }

    static private String mostCommon(def bits) {
        if (bits.sum() > (bits.size() / 2)) {
            return '1'
        }
        return '0'
    }

    static private String leastCommon(def bits) {
        if (mostCommon(bits) == '1') {
            return '0'
        }
        return '1'
    }
*/

