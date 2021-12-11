class ProblemOne {

    static void main(String[] args) {
        println 'hello'

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day2',
            'input.txt').collect {it} //as int[]
        input = input[0..10]
        println "input: ${input.size()}"

        def h = []
        def v = []

        input.each {

            println it.split(' ')

            def instruction = it.split(' ')
            switch (instruction[0]) {
            case 'forward':
                def val = instruction[1] as int
                h << val
                break
            case 'up':
                def val = instruction[1] as int
                v << (val * (-1))
                break
            case 'down':
                def val = instruction[1] as int
                v << val
                break
            }
        }

        println "about H: size=${h.size()} sum=${h.sum()}"
        println "about V: size=${v.size()} sum=${v.sum()}"
        println "answer = ${h.sum() * v.sum()}"
    }

}
