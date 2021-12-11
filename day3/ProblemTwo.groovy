class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day3',
            'sample1.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"
        println input

        int b = 5
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

}
