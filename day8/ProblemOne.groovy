class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day8',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        def signals = []
        def outputs = []

        input.each {
            println it
            def parts = it.split('\\|')
            signals << parts[0]
            parts[1].split(' ').collect { it.trim() }.findAll { it }.each { outputs << it }
        }

        //println signals
        println outputs

        println outputs.findAll {
            it.size() in [2,4,3,7]
        }.size()


        /*
        def positions = input[0].split(',').collect { Integer.parseInt(it) }
        println positions

        def minFuel = Integer.MAX_VALUE
        def costs = [:]
        for (int i in positions.min() .. positions.max()) {
            //println "testing position ${i}"
            def fuel = positions.collect {p->
                calcFuel(p, i)
            }.sum()

            //println "fuel = ${fuel}"
            if (fuel < minFuel) {
                println "Low Fuel cost found = ${fuel} (position ${i})"
                minFuel = fuel
            }
        }
*/
    }

    static int calcFuel(int start, int end) {
        int move = (start - end).abs()
        int cumulative = (move * (move+1))/2
        cumulative
    }
}
