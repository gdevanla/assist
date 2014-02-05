package com.ser.testgenerator.testartifacts

println "Running Test1"
def s1 = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState("simpleclass.xml")
println s1["s"]
println s1["x"]
def x = s1.invokeMethod("sayHello", null)


