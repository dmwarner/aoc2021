class ProblemTwo {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day2',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: ${input.size()}"


        def i = 0
        def h = []
        def v = []
        def aim = 0

        input.each {
            i++
            println it.split(' ')

            def instruction = it.split(' ')
            def val = instruction[1] as int
            switch (instruction[0]) {
            case 'forward':
                h << val
                v << (val * aim)
                break
            case 'up':
                aim -= val
                break
            case 'down':
                aim += val
                break
            }
            //println "aim is ${aim}"
        }
        println "about H: size=${h.size()} sum=${h.sum()}"
        println "about V: size=${v.size()} sum=${v.sum()}"
        println "answer = ${h.sum() * v.sum()}"
    }

}
