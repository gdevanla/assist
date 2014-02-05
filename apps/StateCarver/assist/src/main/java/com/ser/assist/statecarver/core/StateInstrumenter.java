package com.ser.assist.statecarver.core;

import org.apache.commons.io.FileUtils;
import soot.*;
import soot.options.Options;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//TODO:
// Handle multiple return statements in code
// Handle return statements of all types(int, int[], Integer[], Integer)
// Save ref parameter? Save all parameters or the ones who may have defboxes associated with them.
// exception handling, what do I need to do here?
// Method calls inside loop, any special handling
// save state before exception throw statements

public class StateInstrumenter extends BodyTransformer {

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
            e.printStackTrace();
        }
    }

    public static StateInstrumenter v() { return instance;}

    public static void run(int outputFormat /* class or jimple*/) throws  IOException {
        //Default soot settings -move to some central class
        G.reset();
        //Options.v().set_verbose(true);
        Options.v().set_no_bodies_for_excluded(true);
        ArrayList<String> exclude_list = new ArrayList<String>();
        exclude_list.add("java.");
        Options.v().set_exclude(exclude_list);
        Options.v().set_whole_program(true);
        Options.v().set_output_format(outputFormat);
        Options.v().set_keep_line_number(true);
        Options.v().setPhaseOption("jb", "use-original-names");

        PackManager.v().getPack("jtp").add(new Transform("jtp.myTransformer", StateInstrumenter.v()));

        //create required folders
        //FileUtils.forceMkdir(new File(StateCarverConfiguration.v().getTraceDestination()));

        //Options.v().set_output_dir(StateCarverConfiguration.v().getSootOutputFolder());
        Options.v().set_allow_phantom_refs(true);

        String sourceFolder = StateCarverConfiguration.v().getProcessDir();
        String sootClassPath = StateCarverConfiguration.v().getSootClassPath();

        System.out.println(sourceFolder);
        System.out.println(sootClassPath);

        //move this whole thing to mvn, can that be done?
        String[] sootArguments = new String[]{"-process-dir", sourceFolder, "-cp", sootClassPath};

        soot.Main.main(sootArguments);

    }

    public static boolean run() throws IOException {

        run(Options.output_format_J);
        run(Options.output_format_c);
        return true;
    }

    public static void main(String[] args) throws IOException {
       run();
    }
}
