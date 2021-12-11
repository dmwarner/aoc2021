import groovy.transform.EqualsAndHashCode


class ProblemTwo {

    static void main(String[] args) {

        def input = new File(
            '/Users/dwarner1/dev/github/dmwarner/aoc2021/day5',
            'input.txt').collect {it} //as int[]
        //input = input[0..10]
        println "input: size:${input.size()}"

        VentMap vm = new VentMap()
        input.each {

            def coords = it.split('->')
            Point start = new Point(
                [
                    x: coords[0].split(',')[0] as int,
                    y: coords[0].split(',')[1] as int
                ]
            )

            Point end = new Point(
                [
                    x: coords[1].split(',')[0] as int,
                    y: coords[1].split(',')[1] as int
                ]
            )

            Line line = new Line(
                [
                    start: start,
                    end: end
                ]
            )

            vm.addLine(line)
        }
        println "Answer is: ${vm.dangerIndex(2)}"
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
