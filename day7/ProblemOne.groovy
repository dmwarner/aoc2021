class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day7',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        def positions = input[0].split(',').collect { Integer.parseInt(it) }
        println positions

        println positions.max()
        println positions.min()
        println positions.sum()

        def minFuel = Integer.MAX_VALUE
        def costs = [:]
        for (int i in positions.min() .. positions.max()) {
            //println "testing position ${i}"
            def fuel = positions.collect {p->
                //println "\t${(p-i).abs()}"
                (p-i).abs()
            }.sum()

            //println "fuel = ${fuel}"
            if (fuel < minFuel) {
                println "Low Fuel cost found = ${fuel}"
                minFuel = fuel
            }


        }


    }

}
