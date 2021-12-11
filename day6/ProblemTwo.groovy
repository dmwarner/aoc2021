import groovy.transform.EqualsAndHashCode
import java.math.*


class ProblemOne {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day6',
            'input.txt').collect {it} // as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        println input
        def population = input[0].split(',').collect { Integer.parseInt(it.trim())  }
        println population
        def nextPop = []

        Map<Integer, BigInteger> cq = initQueue()

        //Load starting conditions
        population.each {
            cq[it] = cq[it].add(new BigInteger('1'))
        }

        println cq

        int maxDays = 256
        for (int day in 1 .. maxDays) {
//            println day
            Map<Integer, Long> next = initQueue()
            next[0] = cq[1]
            next[1] = cq[2]
            next[2] = cq[3]
            next[3] = cq[4]
            next[4] = cq[5]
            next[5] = cq[6]
            next[6] = cq[7].add(cq[0])
            next[7] = cq[8]
            next[8] = cq[0]

            cq = next
        println cq
        BigInteger total= cq[0].add(cq[1].add(cq[2].add(cq[3].add(cq[4].add(cq[5].add(cq[6].add(cq[7].add(cq[8]))))))))

        //println cq
        println "${day}: total = ${total}"
        }

        println cq
        def total = cq[0] + cq[1] + cq[2] + cq[3] + cq[4] + cq[5] + cq[6] + cq[7] + cq[8]
        //println cq
        println "total = ${total}"

        for (int day in 1 .. 18) {
  //          println "day ${day}: ${population.size()} "
            //println population
            nextPop = []
            population.each {
                switch (it) {
                    case 0:
                        nextPop << 6 << 8
                        break
                    default:
                        nextPop << (it - 1)
                }
            }

            population = nextPop

        }

        println "brute force: ${population.size()}"

    }

    private static Map<Integer, Long> initQueue() {
        [
            0:new BigInteger('0'),
            1:new BigInteger('0'),
            2:new BigInteger('0'),
            3:new BigInteger('0'),
            4:new BigInteger('0'),
            5:new BigInteger('0'),
            6:new BigInteger('0'),
            7:new BigInteger('0'),
            8:new BigInteger('0')
        ]
    }

}

class VentMap {

    Map<Point, Integer> hotspots = [:]

    void addLine(Line line) {
        line.points().each {
            //println "adding line point ${it}"

            if (hotspots.containsKey(it)) {
                //println "increase danger for ${it}"
                hotspots[it]++
            } else {
                hotspots.put(it, 1)
            }
            //println hotspots
        }
    }

    int dangerIndex(int threshhold) {
        hotspots.findAll { k,v -> v >= threshhold }.size()
    }

}

class Hotspot {

}

class Line {
    Point start
    Point end
    List<Point> points() {
        List<Point> ret = []
        if (this.isHorzOrVert()) {
            println "${start} ${end}"
            for (int i in start.x .. end.x) {
                for (int j in  start.y .. end.y) {
                    ret << new Point([x:i, y:j])
                }
            }
        } else {
            def slope = (start.y - end.y) / (start.x - end.x)
            println "slope = ${slope} for ${start} ${end}"
            ret << start
            def step = 0
            int offset_y = start.x - start.y
            if (start.x > end.x) {
                for (int i in (start.x - 1) .. (end.x + 1)) {
                    step++
                    Point p = new Point([x:i, y:(start.y - (slope * step))])
                    println "adding ${p} (i=${i}) (offset=${offset_y})"
                    ret << p
                }
            } else {
                for (int i in (start.x + 1) .. (end.x - 1)) {
                    step++
                    Point p = new Point([x:i, y:(start.y + (slope * step))])
                    println "adding ${p} (i=${i}) (offset=${offset_y})"
                    ret << p
                }
            }
            ret << end
        }
        println ret
        println ''
        ret
    }

    boolean isHorzOrVert() {
        if ((start.x == end.x) || (start.y == end.y)) {
            return true
        }
        false
    }
}

@EqualsAndHashCode
class Point {
    int x
    int y

    //@Override
    //public boolean equals(Point p) {
    //    ((x==p.x) && (y==p.y))
    //}
    String toString() {
        "(${x}, ${y})"
    }
}

class Points {
    Map<Point, Integer> grid = [:]

    void add(Point) {

    }
}
