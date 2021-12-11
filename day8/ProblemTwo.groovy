class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day8',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        Integer runningTotal = 0
        input.each {
            def signals = []
            def outputs = []
            def key
            //println it
            def parts = it.split('\\|')
            parts[0].split(' ').collect { it.trim() }.findAll { it }.each { signals << it }

            key = decodeEntry(signals)

            println parts[1].split(' ').collect { it.trim() }.findAll { it }.each { outputs << it }
            println "+++"
            def digits = ''
            outputs.each {
                //println it
                def found = false
                for (int i in 0 .. 9) {
                    def count = countMatches(it, key[i])
                    if ((it.size() == key[i].size()) && (count == it.size())) {
                        //println "output ${i}"
                        digits += i
                        return
                    }
                }
            }

            println "digits ${digits}"
            runningTotal += Integer.parseInt(digits)
        }

        //println signals
        //println outputs
        //println key
        println "runningTotal ${runningTotal}"
        println "---"
    }

    static def decodeEntry(def signals) {

        def key = [0:'', 1:'', 2:'', 3: '', 4:'', 5:'', 6:'', 7:'', 8:'', 9:'']

        key[1] = signals.find { it.size() == 2 }
        key[4] = signals.find { it.size() == 4 }
        key[7] = signals.find { it.size() == 3 }
        key[8] = signals.find { it.size() == 7 }

        signals.removeAll([key[1], key[4], key[7], key[8]])

        signals.each {
            //println "decoding ${it}"
            if (
                    it.size() == 6
                    && containAll(it, [ key[1], key[4], key[7] ])
                    && (!containAll(it, [ key[8] ] ))
                ) {
                //println "found a 9! (${it})"
                key[9] = it
            } else if (
                    it.size() == 6
                    && containAll(it, [ key[1], key[7] ])
                    && (!containAll(it, [ key[4] ]))
                ) {
                //println "found a 0! (${it})"
                key[0] = it
            } else if (
                    it.size() == 6
                    && (!containAll(it, [ key[1] ]))
                    && (!containAll(it, [ key[4] ]))
                    && (!containAll(it, [ key[7] ]))
                    && (!containAll(it, [ key[8] ]))
                ) {
                //println "found a 6!"
                key[6] = it
            } else if (
                    it.size() == 5
                ) {
                if (containAll(it, [ key[1], key[7] ])) {
                    //println "found a 3! (${it})"
                    key[3] = it
                } else if (countMatches(it, key[4]) == 2) {
                    //println "found a 2! (${it})"
                    key[2] = it
                } else {
                    //println "found a 5! (${it})"
                    key[5] = it
                }
            } else {
                //println "nothing decoded for ${it}"
            }

        }

        println "key is ${key}"
        println "----------------"
        key
    }

    static int countMatches(String val, String match) {
        int count = 0
        match.each {
            if (val.indexOf(it) != -1) {
                count++
            }
        }
        count
    }

    static boolean containAll(def it, def segments) {
        //println "containsAll for ${it} ${segments})"
        boolean found = true
        int i = 0

        while (found && i < segments.size()) {
        //for (int i in 0 .. segments.size() -1) {
            segments[i].each {c ->
                //println "... ${c} ... ${it.indexOf(c)}"
                if (it.indexOf(c) == -1) {
                    //println "missing ${c}"
                    found = false
                }
            }
            i++
        }
        //println "returning ${found}"
        found
    }

    static int calcFuel(int start, int end) {
        int move = (start - end).abs()
        int cumulative = (move * (move+1))/2
        cumulative
    }
}
