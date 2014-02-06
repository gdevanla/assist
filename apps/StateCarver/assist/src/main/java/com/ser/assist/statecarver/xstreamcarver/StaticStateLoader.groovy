package com.ser.assist.statecarver.xstreamcarver

import com.ser.assist.statecarver.core.StateCarverConfiguration
import org.apache.commons.io.FilenameUtils

class StaticStateLoader {

    public static loadStaticState(int counter){

        String glob =  FilenameUtils.concat(StateCarverConfiguration.v().getTraceDestination(), "static:" + counter + "*")
        def command = ["sh" ,"-c" ,"ls " + glob]
        println command
        def files = command.execute().text.split("\n")

        files.each() {  fileName ->
            def obj = com.ser.assist.statecarver.xstreamcarver.XStreamStateCarver.loadState(fileName)
            def attr = getFieldAttributes(FilenameUtils.getBaseName(fileName))
            println "Loading attr = " + attr
            def f = Class.forName(attr[0]).getDeclaredField(attr[2])
            f.setAccessible(true);
            f.set(Class.forName(attr[0]), obj)
        }
    }


    /*Return contained class, type and field name for static data*/
    public static getFieldAttributes(String fileName){
        switch(fileName){
            case {fileName.startsWith("static")}:
                def parts = fileName.split(':')
                return [parts[2], parts[3], parts[4]]
            default:
                //not implemented yet
                return []
        }
    }

}
