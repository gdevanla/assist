package com.ser.assist.statecarver.core;

import com.sun.tools.internal.ws.processor.util.DirectoryUtil;
import org.apache.commons.io.FileUtils;
import soot.*;
import soot.JastAddJ.FileNamesPart;
import soot.options.Options;

import java.io.File;
import java.io.IOException;
import java.util.Map;

//TODO:
// Handle multiple return statements in code
// Handle return statements of all types(int, int[], Integer[], Integer)
// Save ref parameter? Save all parameters or the ones who may have defboxes associated with them.
// exception handling, what do I need to do here?
// Method calls inside loop, any special handling
// save state before exception throw statements

public class StateInstrumenter extends BodyTransformer {

    /*move to configuration*/
    //static final String appBaseFolder = "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/assist";
    //static final String sourceFolder = "/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/TestArtifacts/src/main/java";
    //static final String sootClassPath = sourceFolder + ":" + appBaseFolder + "/" + "target/classes";

    private static StateInstrumenter instance = new StateInstrumenter();
    private StateInstrumenter() {}

    boolean staticFieldInitialized = false;

    @Override
    protected void internalTransform(Body body, String s, Map map) {
      //new Research().reviewUseDefBoxes(body, s, map);

      if (!staticFieldInitialized){
          StaticStateOfApp.init();
          staticFieldInitialized = true;
       }
        try {
            new MethodInstrumenter().instrumentMethod(body, s, map);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static StateInstrumenter v() { return instance;}

    public static boolean run() throws IOException {
        PackManager.v().getPack("jtp").add(new Transform("jtp.myTransformer", StateInstrumenter.v()));

        //create required folders
        FileUtils.forceMkdir(new File(StateCarverConfiguration.v().getTraceDestination()));

        //Options.v().set_verbose(true);
        //Options.v().set_output_format(Options.output_format_J);
        Options.v().set_output_dir(StateCarverConfiguration.v().getSootOutputFolder());

        String sourceFolder = StateCarverConfiguration.v().getProcessDir();
        String sootClassPath = StateCarverConfiguration.v().getSootClassPath();

        System.out.println(sourceFolder);
        System.out.println(sootClassPath);

        //move this whole thing to mvn, can that be done?
        String[] sootArguments = new String[]{"-process-dir", sourceFolder, "-cp", sootClassPath};

        soot.Main.main(sootArguments);
        return true;
    }

    public static void main(String[] args) throws IOException {
       run();

    }
}
