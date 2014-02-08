

def l = [ [1,2] , [3,4] , [5, 6]]

def product(l){

    def p = { l1, l2 ->
        l1.inject([]) { acc, i ->
            acc + l2.inject([]) { acc1, j ->
                if ( i instanceof List) { acc1 << i + j} else { acc1 << [i, j]}}}}

        l.inject() { acc, it -> p(acc, it) }
}

println  product(l)
println product([[1:"A", 2:"B"], [3:"C" ,  4:"D"] , [5:"E", 6:"F"]])
